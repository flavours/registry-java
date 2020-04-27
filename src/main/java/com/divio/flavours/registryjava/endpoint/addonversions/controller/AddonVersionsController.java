package com.divio.flavours.registryjava.endpoint.addonversions.controller;

import com.divio.flavours.registryjava.endpoint.addonversions.service.AddonVersionsService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.NotBlank;

@Controller
public class AddonVersionsController {
    private final AddonVersionsService addonVersionsService;

    public AddonVersionsController(AddonVersionsService addonVersionsService) {
        this.addonVersionsService = addonVersionsService;
    }

    @GetMapping(path = "/addonversions/{base64id}/")
    public ResponseEntity<?> findAddonDetails(@NotBlank @PathVariable("base64id") final String base64Id) {
        return addonVersionsService.resolveAddonDetails(base64Id).handle(
                error -> ResponseEntity.badRequest().body(error),
                ResponseEntity::ok
        );
    }

    @PostMapping(path = "/addonversions/resolve/")
    public ResponseEntity<?> resolveQuery(@RequestParam("query") final String query) {
        return addonVersionsService.resolveMavenArtifact(query).handle(
                errorMessage -> ResponseEntity.badRequest().body(errorMessage),
                ResponseEntity::ok
        );
    }
}
