package com.divio.flavours.registryjava.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/*
{
    "id": "3f4beadb-9093-4e51-8c36-498c6194988d",
    "addon": "https://addons.flavours.dev/addons/2b16df75-577c-4ff4-a8be-299e36657997/",
    "identifier": "v0.1.0",
    "yaml": "spec: 0.1\ninstall:\n  require-only: false\n  package: robstar/laravel-flavour-addon-demo:v0.1.0\n  post-install:\n  - php artisan vendor:publish --provider=\"Robstar\\LaravelFlavourAddonDemo\\ServiceProvider\"\nmeta:\n  name: composer/robstar/laravel-flavour-addon-demo\n  version: v0.1.0\nconfig:\n  background:\n    label: \"Background color\"\n    required: False\n    type: \"scalar/string\"\n    default: \"#fff\"\n    variable: WELCOME_PAGE_BACKGROUND_COLOR\n",
    "stacks": [
        "https://addons.flavours.dev/stacks/6d873b75-12fa-45c5-ae2b-bde2ac664117/"
    ],
    "platforms": [
        "https://addons.flavours.dev/stacks/6d873b75-12fa-45c5-ae2b-bde2ac664117/"
    ]
}
 */
public class DetailSuccess {
    public static final String DEFAULT_STACK = "fam-gradle";

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

    public DetailSuccess(final String id, final String addon, final String identifier, final String yaml, final List<String> stacks, final List<String> platforms) {
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
