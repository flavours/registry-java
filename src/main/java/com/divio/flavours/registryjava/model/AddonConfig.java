package com.divio.flavours.registryjava.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class AddonConfig {
    @JsonProperty("spec")
    @NotBlank
    private String specValue;

    @JsonProperty("install")
    @NotNull
    @Valid
    private Install installValue;

    @JsonProperty("meta")
    @NotNull
    @Valid
    private Meta metaValue;

    private AddonConfig() {
    }

    public AddonConfig(final String specValue, final Install installValue, final Meta metaValue) {
        this.specValue = specValue;
        this.installValue = installValue;
        this.metaValue = metaValue;
    }

    public Install getInstall() {
        return installValue;
    }

    public Meta getMeta() {
        return metaValue;
    }

    public String getSpec() {
        return specValue;
    }
}
