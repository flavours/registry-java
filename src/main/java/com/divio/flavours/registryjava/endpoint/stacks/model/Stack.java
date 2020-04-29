package com.divio.flavours.registryjava.endpoint.stacks.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Stack {
    @JsonProperty
    private String id;

    @JsonProperty
    private String identifier;

    public Stack(final String id, final String identifier) {
        this.id = id;
        this.identifier = identifier;
    }

    public String getId() {
        return id;
    }

    public String getIdentifier() {
        return identifier;
    }
}
