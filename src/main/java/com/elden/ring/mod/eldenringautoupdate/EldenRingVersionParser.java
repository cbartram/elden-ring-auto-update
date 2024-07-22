package com.elden.ring.mod.eldenringautoupdate;

import com.elden.ring.mod.eldenringautoupdate.model.Version;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EldenRingVersionParser {

    private static final String FILE_PREFIX = "Seamless Co-op v";
    private static final Pattern VERSION_PATTERN = Pattern.compile("v(\\d+)\\.(\\d+)\\.(\\d+)");

    public static Map<String, String> parseVersions(String directoryPath) {
        File directory = new File(directoryPath);
        Map<String, String> versionMap = new HashMap<>();

        if (directory.exists() && directory.isDirectory()) {
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isFile() && file.getName().startsWith(FILE_PREFIX)) {
                        Version version = parseVersion(file.getName());
                        if (version != null) {
                            versionMap.put(version.toString(), file.getName());
                        }
                    }
                }
            }
        }

        return versionMap;
    }

    private static Version parseVersion(String fileName) {
        Matcher matcher = VERSION_PATTERN.matcher(fileName);
        if (matcher.find()) {
            int major = Integer.parseInt(matcher.group(1));
            int minor = Integer.parseInt(matcher.group(2));
            int patch = Integer.parseInt(matcher.group(3));
            return new Version(major, minor, patch);
        }
        return null;
    }
}
