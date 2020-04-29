package com.divio.flavours.registryjava.endpoint.addons.service;

import com.divio.flavours.registryjava.ServerConfig;
import com.divio.flavours.registryjava.endpoint.addons.model.Addon;
import com.divio.flavours.registryjava.endpoint.namespaces.controller.NamespacesController;
import com.divio.flavours.registryjava.model.MavenIdentifier;
import com.divio.flavours.registryjava.util.Result;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class SimpleAddonsService implements AddonsService {
    private final ServerConfig serverConfig;

    public SimpleAddonsService(ServerConfig serverConfig) {
        this.serverConfig = serverConfig;
    }

    @Override
    public Optional<Addon> resolveAddon(String id) {
        return Result.ofTry(() -> new String(Base64.getUrlDecoder().decode(id)))
                .mapFailure(ignored -> "")
                .flatMap(MavenIdentifier::parse)
                .handle(ignored -> Optional.empty(),
                        mavenIdentifier -> {
                            var addonIdentifier = String.format("%s/%s", mavenIdentifier.getGroup(), mavenIdentifier.getArtifact());
                            var description = String.format("<h2>%s</h2>", mavenIdentifier.getArtifact());
                            var addonVersionsUrl = serverConfig.urlWithPath(String.format("/addonversions/%s/", id));
                            var namespacesUrl = serverConfig.urlWithPath(String.format("/namespaces/%s/", NamespacesController.STATIC_NAMESPACE.getId()));
                            var addon = new Addon(id, namespacesUrl, addonIdentifier, description, List.of(addonVersionsUrl));
                            return Optional.of(addon);
                        });
    }
}
