package com.divio.flavours.registryjava.models;

import com.divio.flavours.registryjava.Result;

import java.util.Map;
import java.util.regex.Pattern;

public class MavenIdentifier {
    private static final Pattern pattern = Pattern.compile("java/([^:]+)/([^:]+):([^:]+)");

    private final String group;
    private final String artifact;
    private final String version;

    public MavenIdentifier(final String group, final String artifact, final String version) {
        this.group = group;
        this.artifact = artifact;
        this.version = version;
    }

    /**
     * Deserializes an input with format java/<group>/<artifact>:<version> into a Result<String, MavenIdentifier>
     *
     * @param input
     * @return
     */
    public static Result<Map<String, String>, MavenIdentifier> parse(final String input) {
        var matcher = pattern.matcher(input);

        if (matcher.matches()) {
            var groupId = matcher.group(1);
            var artifactId = matcher.group(2);
            var version = matcher.group(3);

            return Result.success(new MavenIdentifier(groupId, artifactId, version));
        } else {
            var errors = Map.of("query", String.format("Input '%s' does not match pattern '%s'.", input, pattern.toString()));
            return Result.failure(errors);
        }
    }

    public String toFlavourIdentifier() {
        return String.format("java/%s/%s:%s", group, artifact, version);
    }

    @Override
    public String toString() {
        return toFlavourIdentifier();
    }
}
