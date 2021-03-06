package com.divio.flavours.registryjava.model;

import com.divio.flavours.registryjava.util.Result;

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
    public static Result<String, MavenIdentifier> parse(final String input) {
        var matcher = pattern.matcher(input);

        if (matcher.matches()) {
            var groupId = matcher.group(1);
            var artifactId = matcher.group(2);
            var version = matcher.group(3);

            return Result.success(new MavenIdentifier(groupId, artifactId, version));
        } else {
            var errors = String.format("Input '%s' does not match pattern '%s'.", input, pattern.toString());
            return Result.failure(errors);
        }
    }

    public String getArtifact() {
        return artifact;
    }

    public String getGroup() {
        return group;
    }

    public String getVersion() {
        return version;
    }

    public String toFlavourIdentifier() {
        return String.format("java/%s/%s:%s", group, artifact, version);
    }

    public String toFlavourName() {
        return String.format("java/%s/%s", group, artifact);
    }

    public String toGrailsIdentifier() {
        return String.format("%s:%s:%s", group, artifact, version);
    }

    @Override
    public String toString() {
        return toFlavourIdentifier();
    }
}
