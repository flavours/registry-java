package com.divio.flavours.registryjava;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@Configuration
public class ServerConfig {
    private final String domain;

    private final String scheme;

    private final Integer port;

    public ServerConfig(@Value("${host.scheme:{null}}") final String scheme,
                        @Value("${host.domain}") final String domain,
                        @Value("${host.port:{null}}") final Integer port) {
        this.scheme = scheme;
        this.domain = domain;
        this.port = port;
    }

    public String getScheme() {
        return scheme;
    }

    public String getDomain() {
        return domain;
    }

    public Optional<Integer> getPort() {
        return Optional.ofNullable(port);
    }

    public String urlWithPath(final String path) {
        var actualPort = port == null ? "" : ":" + port.toString();
        var actualScheme = scheme == null ? "https" : scheme;

        return String.format("%s://%s%s%s", actualScheme, domain, actualPort, path);
    }
}
