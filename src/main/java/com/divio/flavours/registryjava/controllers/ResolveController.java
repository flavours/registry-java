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
    public ResponseEntity postResolve(@RequestParam("query") String query) {
        var result = MavenIdentifier.parse(query);

        return result.handle(
                this::handleQueryFailure,
                this::handleQuerySuccess
        );
    }

    private ResponseEntity handleQueryFailure(Map<String, String> errors) {
        return ResponseEntity.badRequest().body(errors);
    }

    private ResponseEntity handleQuerySuccess(MavenIdentifier mavenResource) {
        var identifier = mavenResource.toFlavourIdentifier();
        var base64String = Base64.getUrlEncoder().withoutPadding().encodeToString(identifier.getBytes());

        return ResponseEntity.ok(new QuerySuccess(new AddonSpec(base64String, identifier)));
    }
}
