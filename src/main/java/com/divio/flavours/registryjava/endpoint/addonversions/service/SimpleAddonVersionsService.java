package com.divio.flavours.registryjava.endpoint.addonversions.service;

import com.divio.flavours.registryjava.ServerConfig;
import com.divio.flavours.registryjava.endpoint.addonversions.model.AddonVersion;
import com.divio.flavours.registryjava.endpoint.addonversions.model.AddonSpec;
import com.divio.flavours.registryjava.endpoint.addonversions.model.QuerySuccess;
import com.divio.flavours.registryjava.endpoint.stacks.controller.StacksController;
import com.divio.flavours.registryjava.model.AddonConfig;
import com.divio.flavours.registryjava.model.Install;
import com.divio.flavours.registryjava.model.MavenIdentifier;
import com.divio.flavours.registryjava.model.Meta;
import com.divio.flavours.registryjava.parser.YamlParser;
import com.divio.flavours.registryjava.util.Result;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class SimpleAddonVersionsService implements AddonVersionsService {
    private static final Base64.Encoder BASE64_ENCODER = Base64.getUrlEncoder().withoutPadding();
    private final ServerConfig serverConfig;

    public SimpleAddonVersionsService(final ServerConfig serverConfig) {
        this.serverConfig = serverConfig;
    }

    @Override
    public Result<String, Optional<AddonVersion>> resolveAddonDetails(String identifier) {
        return Result.ofTry(() -> new String(Base64.getUrlDecoder().decode(identifier)))
                .mapFailure(Throwable::getMessage)
                .flatMap(MavenIdentifier::parse)
                .map(mavenIdentifier -> Optional.of(addonVersionFromIdentifier(identifier, mavenIdentifier)));
    }

    @Override
    public Result<String, Optional<QuerySuccess>> resolveMavenArtifact(String query) {
        return MavenIdentifier.parse(query)
                .map(mavenIdentifier -> {
                    var identifierBytes = mavenIdentifier.toFlavourIdentifier().getBytes();
                    var base64Id = BASE64_ENCODER.encodeToString(identifierBytes);
                    var addonSpec = new AddonSpec(base64Id, query);
                    return Optional.of(new QuerySuccess(query, addonSpec));
                });
    }

    protected AddonVersion addonVersionFromIdentifier(String identifier, MavenIdentifier mavenIdentifier) {
        var addonConfigYamlParser = new YamlParser<>(AddonConfig.class);
        var addonConfig = new AddonConfig("0.1",
                new Install(mavenIdentifier.toGrailsIdentifier()),
                new Meta(mavenIdentifier.toFlavourName(), mavenIdentifier.getVersion())
        );

        var stackUrl = serverConfig.urlWithPath(String.format("/stacks/%s/", StacksController.DEFAULT_STACK.getId()));

        return new AddonVersion(
                identifier,
                serverConfig.urlWithPath(String.format("/addon/%s/", identifier)),
                mavenIdentifier.getVersion(),
                addonConfigYamlParser.writeToString(addonConfig),
                List.of(stackUrl),
                List.of(stackUrl)
        );
    }
}
