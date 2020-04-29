package com.divio.flavours.registryjava.endpoint.addons.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

public class Addon {
    @JsonProperty
    @NotBlank
    private String id;

    @JsonProperty
    @NotBlank
    private String namespace;

    @JsonProperty
    @NotBlank
    private String identifier;

    @JsonProperty
    @NotBlank
    private String description;

    @JsonProperty("addonversions")
    @NotEmpty
    private List<String> addonVersions;

    public Addon(@NotBlank String id,
                 @NotBlank String namespace,
                 @NotBlank String identifier,
                 @NotBlank String description,
                 @NotEmpty List<String> addonVersions) {
        this.id = id;
        this.namespace = namespace;
        this.identifier = identifier;
        this.description = description;
        this.addonVersions = addonVersions;
    }

}
