package com.elden.ring.mod.manager.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
@AllArgsConstructor
public class Version {
    private final int major;
    private final int minor;
    private final int patch;

    private static final Pattern VERSION_PATTERN = Pattern.compile("^(\\d+)\\.(\\d+)\\.(\\d+)$");

    public static Version from(String versionString) {
        Matcher matcher = VERSION_PATTERN.matcher(versionString);
        if (matcher.matches()) {
            int major = Integer.parseInt(matcher.group(1));
            int minor = Integer.parseInt(matcher.group(2));
            int patch = Integer.parseInt(matcher.group(3));
            return new Version(major, minor, patch);
        } else {
            throw new IllegalArgumentException("Invalid version string format: " + versionString);
        }
    }

    @Override
    public String toString() {
        return major + "." + minor + "." + patch;
    }
}