package com.elden.ring.mod.eldenringautoupdate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class AutoUpdaterController {

    @FXML
    private CheckBox makeAlias;

    @FXML
    private ChoiceBox<String> coopModSelector;

    @FXML
    private Button installButton;

    @FXML
    private Label installLocationLabel;

    @FXML
    private Button selectInstallButton;

    List<String> eldenRingZipFiles = new ArrayList<>();

    @FXML
    protected void initialize(URL location) {
        // 1. Locate all elden ring mod zip files in downloads folder
            // 1.5 Locate elden ring game location for steam
        // 2. Let user select which mod they would like to install
        // 3. Copy the current settings.ini file from elden ring coop
        // 4. Unpack zip into elden ring coop game location
        // 5. Copy mod .exe to elden ring game location
        // 6. Optional - copy exe to another location as an alias
        String downloadsFolder = System.getProperty("user.home") + File.separator + "Downloads";
        String steamInstallLocation = System.getProperty("user.home");
    }

    @FXML
    protected void onSelectInstallButtonClick(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
    }

}