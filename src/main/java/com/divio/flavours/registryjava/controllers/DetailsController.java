package com.divio.flavours.registryjava.controllers;

import com.divio.flavours.registryjava.Result;
import com.divio.flavours.registryjava.model.*;
import com.divio.flavours.registryjava.parser.YamlParser;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.validation.constraints.NotBlank;
import java.util.Base64;
import java.util.List;
import java.util.Map;

@Controller
public class DetailsController {
    @GetMapping(path = "/addonversions/{base64id}/")
    public ResponseEntity addonDetails(@NotBlank @PathVariable("base64id") final String base64Id) {
        return resolveBase64Id(base64Id).handle(
                this::handleFailure,
                id -> handleSuccess(base64Id, id)
        );
    }

    private Result<Map<String, String>, MavenIdentifier> resolveBase64Id(final String base64Id) {
        var data = Base64.getUrlDecoder().decode(base64Id);
        var text = new String(data);
        return MavenIdentifier.parse(text);
    }

    private ResponseEntity handleFailure(Map<String, String> errors) {
        return ResponseEntity.badRequest().body(errors);
    }

    private ResponseEntity handleSuccess(final String base64Id, final MavenIdentifier mavenIdentifier) {
        var groupWithArtifact = String.format("%s:%s", mavenIdentifier.getGroup(), mavenIdentifier.getArtifact());
        var yamlParser = new YamlParser<>(AddonConfig.class);
        var addonConfig = new AddonConfig("0.1", new Install(mavenIdentifier.toFlavourIdentifier()), new Meta(groupWithArtifact, mavenIdentifier.getVersion()));
        var yaml = yamlParser.writeToString(addonConfig);
        var details = new DetailSuccess(base64Id, mavenIdentifier.toFlavourIdentifier(), "yolo", yaml, List.of(DetailSuccess.DEFAULT_STACK), List.of(DetailSuccess.DEFAULT_STACK));
        return ResponseEntity.ok(details);
    }
}
