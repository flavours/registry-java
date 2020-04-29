package com.divio.flavours.registryjava.endpoint.addonversions.service;

import com.divio.flavours.registryjava.endpoint.addonversions.model.AddonVersion;
import com.divio.flavours.registryjava.endpoint.addonversions.model.QuerySuccess;
import com.divio.flavours.registryjava.util.Result;

import java.util.Optional;

public interface AddonVersionsService {
    Result<String, Optional<AddonVersion>> resolveAddonDetails(final String identifier);

    Result<String, Optional<QuerySuccess>> resolveMavenArtifact(final String mavenIdentifier);
}
