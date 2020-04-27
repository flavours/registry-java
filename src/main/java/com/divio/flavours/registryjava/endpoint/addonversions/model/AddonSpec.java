package com.divio.flavours.registryjava.endpoint.addonversions.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

public class AddonSpec {
    @JsonProperty
    @NotBlank
    private String id;

    @JsonProperty
    @NotBlank
    private String identifier;

    public AddonSpec(@NotBlank final String id, @NotBlank final String identifier) {
        this.id = id;
        this.identifier = identifier;
    }
}
