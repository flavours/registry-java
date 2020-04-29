package com.divio.flavours.registryjava.endpoint.addons.controller;

import com.divio.flavours.registryjava.endpoint.addons.model.Addon;
import com.divio.flavours.registryjava.endpoint.addons.service.AddonsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import java.util.Optional;

@RestController
public class AddonsController {
    private final AddonsService addonsService;

    public AddonsController(AddonsService addonsService) {
        this.addonsService = addonsService;
    }

    @GetMapping(path = "/addons/{base64id}/")
    Optional<Addon> addons(@NotBlank @PathVariable("base64id") final String base64Id) {
        return addonsService.resolveAddon(base64Id);
    }
}
