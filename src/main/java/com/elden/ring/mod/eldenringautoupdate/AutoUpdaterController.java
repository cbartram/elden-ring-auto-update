package com.elden.ring.mod.eldenringautoupdate;

import com.elden.ring.mod.eldenringautoupdate.model.Controller;
import com.elden.ring.mod.eldenringautoupdate.model.InstallData;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import lombok.extern.log4j.Log4j2;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.Map;
import java.util.stream.Collectors;


@Log4j2
public class AutoUpdaterController implements Controller {

    @FXML
    private CheckBox makeAlias;

    @FXML
    private ChoiceBox<String> coopModSelector;

    @FXML
    private Label installLocationLabel;

    @FXML
    private Label errorLabel;

    private InstallData data;
    private ObjectMapper mapper = new ObjectMapper();
    Map<String, String> modVersions;

    @FXML
    protected void handleMakeAlias(ActionEvent event) {
        this.data.setMakeAlias(makeAlias.isSelected());
    }

    @FXML
    protected void handleInstallButtonClick(ActionEvent event) {
        String erscSettingsIniFile = this.data.getSteamInstallLocation().getAbsolutePath() + File.separator + "Game" + File.separator + "SeamlessCoop" + File.separator + "ersc_settings.ini";
        String backupFile = this.data.getSteamInstallLocation().getAbsolutePath() + File.separator + "Game" + File.separator + "SeamlessCoop" + File.separator + "ersc_settings.ini.backup";
        String extractPath = this.data.getSteamInstallLocation().getAbsolutePath() + File.separator + "Game";
        String sourceZip = InstallData.DOWNLOADS_FOLDER + File.separator + modVersions.get(coopModSelector.getValue());

        // Make a backup of the original settings
        FileOperations.copyFile(erscSettingsIniFile, backupFile);

        // Unpack the zip file
        try {
            log.info("Attempting to extract " + sourceZip);
            ZipUnpacker.unzip(sourceZip, extractPath);
        } catch (IOException e) {
            log.error("IOException while trying to unpack zip file for version: {}", sourceZip, e);
            errorLabel.setText("Failed to unpack zip file for version: " + sourceZip);
            e.printStackTrace();
            return;
        }

        // Move the backup file back to the original
        try {
            FileOperations.moveFile(backupFile, erscSettingsIniFile);
        } catch(IOException e) {
            log.error("IOException while trying to move backup file for to original: {}", backupFile);
        }

        if(this.data.isMakeAlias()) {
            String aliasDir= this.data.getSteamInstallLocation().getAbsolutePath() + File.separator + "Game" + File.separator;
            FileOperations.copyFile(aliasDir + "ersc_launcher.exe", aliasDir + "Elden Ring Coop.exe");
        }

        System.out.println("All file operations completed successfully.");
        errorLabel.setStyle("-fx-text-fill: green;");
        errorLabel.setText("ERSC Mod version " + coopModSelector.getValue() + " was successfully installed.");
    }

    @FXML
    protected void handleSelectInstallLocation(ActionEvent event) {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        DirectoryChooser selector = new DirectoryChooser();
        selector.setTitle("Choose Elden Ring Install Location");
        File selectedDir = selector.showDialog(stage);

        if (selectedDir != null) {
            installLocationLabel.setText(selectedDir.getAbsolutePath());
            this.data.setSteamInstallLocation(selectedDir);
        }
    }

    @Override
    public void onInitialize() {
        this.data = new InstallData();

        // Load Config
        if(Files.exists(InstallData.CONFIG_FILE.toPath())) {
            try {
                this.data = mapper.readValue(InstallData.CONFIG_FILE, InstallData.class);
                log.info("Loaded config from: {}", InstallData.CONFIG_FILE.toPath());

                // Update the labels with information from the config
                installLocationLabel.setText(data.getSteamInstallLocation().getPath());
                makeAlias.setSelected(data.isMakeAlias());

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            log.info("Config file not found. It will be created when the application closes.");
        }

        // Populate the select field with all located .zip files for Elden Ring Coop
        modVersions = EldenRingVersionParser.parseVersions(InstallData.DOWNLOADS_FOLDER.getPath());
        ObservableList<String> versionList = FXCollections.observableArrayList(modVersions.keySet()
                .stream()
                .sorted()
                .map(Object::toString)
                .collect(Collectors.toList())
        );

        if(versionList.isEmpty()) {
            errorLabel.setText("No Elden Ring Coop zip files found in " + InstallData.DOWNLOADS_FOLDER.toPath());
            return;
        }

        if (versionList.size() == 1) {
            coopModSelector.setValue(versionList.get(0));
        } else {
            coopModSelector.setItems(versionList);
        }
    }

    @Override
    public void onExit() {
        log.info("Exiting Application and saving configuration file to: {}", InstallData.CONFIG_FILE.toPath());
        try {
            String jsonString = mapper.writeValueAsString(data);
            try(PrintWriter out = new PrintWriter(InstallData.CONFIG_FILE)) {
                out.println(jsonString);
            }
        } catch (IOException e) {
            log.error("Error saving configuration file: {}", e.getMessage());
            e.printStackTrace();
        }
    }

}