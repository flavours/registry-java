package com.divio.flavours.registryjava.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class QuerySuccess {
    @JsonProperty
    @NotNull
    @Valid
    private AddonSpec result;

    public QuerySuccess(@NotNull @Valid final AddonSpec result) {
        this.result = result;
    }

    public AddonSpec getResult() {
        return result;
    }
}
