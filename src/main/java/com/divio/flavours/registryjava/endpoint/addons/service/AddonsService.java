package com.divio.flavours.registryjava.endpoint.addons.service;

import com.divio.flavours.registryjava.endpoint.addons.model.Addon;

import java.util.Optional;

public interface AddonsService {
    Optional<Addon> resolveAddon(final String id);
}
