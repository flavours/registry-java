package com.divio.flavours.registryjava.endpoint.addonversions.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public class AddonVersion {
    @JsonProperty
    @NotNull
    private String id;

    @JsonProperty
    @NotNull
    private String addon;

    @JsonProperty
    @NotNull
    private String identifier;

    @JsonProperty
    @NotNull
    private String yaml;

    @JsonProperty
    @NotEmpty
    private List<String> stacks;

    @JsonProperty
    @NotEmpty
    private List<String> platforms;

    public AddonVersion(final String id, final String addon, final String identifier, final String yaml, final List<String> stacks, final List<String> platforms) {
        this.id = id;
        this.addon = addon;
        this.identifier = identifier;
        this.yaml = yaml;
        this.stacks = stacks;
        this.platforms = platforms;
    }

    @Override
    public String toString() {
        return "DetailSuccess{" +
                "id='" + id + '\'' +
                ", addon='" + addon + '\'' +
                ", identifier='" + identifier + '\'' +
                ", yaml='" + yaml + '\'' +
                ", stacks=" + stacks +
                ", platforms=" + platforms +
                '}';
    }
}
