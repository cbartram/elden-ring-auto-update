package com.elden.ring.mod.eldenringautoupdate.model;

import lombok.Data;

import java.io.File;

@Data
public class InstallData {
    private File steamInstallLocation;
    private File coopModFile;
    private boolean makeAlias = false;
    public final static File DOWNLOADS_FOLDER = new File(System.getProperty("user.home") + File.separator + "Downloads");
    public final static File CONFIG_FILE = new File(System.getProperty("user.home") + File.separator + ".er-coop-conf.json");
}
