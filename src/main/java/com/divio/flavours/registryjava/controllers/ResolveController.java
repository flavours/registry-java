package com.divio.flavours.registryjava.controllers;

import com.divio.flavours.registryjava.models.AddonSpec;
import com.divio.flavours.registryjava.models.MavenIdentifier;
import com.divio.flavours.registryjava.models.QuerySuccess;
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
                this::handleQueryFailure,
                mavenIdentifier -> handleQuerySuccess(query, mavenIdentifier)
        );
    }

    private ResponseEntity handleQueryFailure(final Map<String, String> errors) {
        return ResponseEntity.badRequest().body(errors);
    }

    private ResponseEntity handleQuerySuccess(final String query, final MavenIdentifier mavenResource) {
        var identifier = mavenResource.toFlavourIdentifier();
        var base64String = Base64.getUrlEncoder().withoutPadding().encodeToString(identifier.getBytes());

        return ResponseEntity.ok(new QuerySuccess(query, new AddonSpec(base64String, identifier)));
    }
}
