package com.divio.flavours.registryjava.controllers;

import com.divio.flavours.registryjava.model.AddonSpec;
import com.divio.flavours.registryjava.model.MavenIdentifier;
import com.divio.flavours.registryjava.model.QuerySuccess;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Base64;
import java.util.Map;

@Controller
public class ResolveController {
    @PostMapping(path = "/addonversions/resolve/")
    public ResponseEntity postResolve(@RequestParam("query") final String query) {
        var result = MavenIdentifier.parse(query);

        return result.handle(
                this::handleSuccess,
                mavenIdentifier -> handleFailure(query, mavenIdentifier)
        );
    }

    private ResponseEntity handleSuccess(final Map<String, String> errors) {
        return ResponseEntity.badRequest().body(errors);
    }

    private ResponseEntity handleFailure(final String query, final MavenIdentifier mavenResource) {
        var identifier = mavenResource.toFlavourIdentifier();
        var base64String = Base64.getUrlEncoder().withoutPadding().encodeToString(identifier.getBytes());

        return ResponseEntity.ok(new QuerySuccess(query, new AddonSpec(base64String, identifier)));
    }
}
