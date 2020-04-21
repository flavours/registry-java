package com.divio.flavours.registryjava.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class QuerySuccess {
    @JsonProperty
    @NotNull
    @Valid
    private AddonSpec result;

    @JsonProperty
    @NotBlank
    private String query;

    public QuerySuccess(@NotBlank final String query, @NotNull @Valid final AddonSpec result) {
        this.result = result;
        this.query = query;
    }

    public AddonSpec getResult() {
        return result;
    }

    public String getQuery() {
        return query;
    }
}
