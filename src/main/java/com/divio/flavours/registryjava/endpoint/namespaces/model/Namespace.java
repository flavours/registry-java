package com.divio.flavours.registryjava.endpoint.namespaces.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Namespace {
    @JsonProperty
    private String id;

    @JsonProperty
    private String identifier;

    @JsonProperty
    private List<String> addons;

    public Namespace(final String id, final String identifier, final List<String> addons) {
        this.id = id;
        this.identifier = identifier;
        this.addons = addons;
    }

    public String getId() {
        return id;
    }

    public String getIdentifier() {
        return identifier;
    }

    public List<String> getAddons() {
        return addons;
    }
}
