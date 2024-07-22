package com.elden.ring.mod.eldenringautoupdate;

import com.elden.ring.mod.eldenringautoupdate.model.Controller;
import com.elden.ring.mod.eldenringautoupdate.model.InstallData;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import lombok.extern.log4j.Log4j2;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;


@Log4j2
public class AutoUpdaterController implements Controller {

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

    private InstallData data;
    private ObjectMapper mapper = new ObjectMapper();

    List<String> eldenRingZipFiles = new ArrayList<>();

    // 1. Locate all elden ring mod zip files in downloads folder
        // 1.5 Locate elden ring game location for steam
    // 2. Let user select which mod they would like to install
    // 3. Copy the current settings.ini file from elden ring coop
    // 4. Unpack zip into elden ring coop game location
    // 5. Copy mod .exe to elden ring game location
    // 6. Optional - copy exe to another location as an alias
    // E:\SteamLibrary\steamapps\common\ELDEN RING

    @FXML
    public void exitApplication(ActionEvent event) {
        System.out.println("Exiting Application");
        Platform.exit();
    }

    @FXML
    protected void onSelectInstallButtonClick(ActionEvent event) {
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
                installLocationLabel.setText(this.data.getSteamInstallLocation().getPath());
                makeAlias.setSelected(this.data.isMakeAlias());

                if(this.data.getCoopModFile() != null) {
//                   coopModSelector.setSelectionModel();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            log.info("Config file not found. It will be created when the application closes.");
        }
    }

    @Override
    public void onExit() {
        log.info("Exiting Application and saving configuration file to: {}", InstallData.CONFIG_FILE.toPath());
        try {
            String jsonString = mapper.writeValueAsString(this.data);
            try(PrintWriter out = new PrintWriter(InstallData.CONFIG_FILE)) {
                out.println(jsonString);
            }
        } catch (IOException e) {
            log.error("Error saving configuration file: {}", e.getMessage());
            e.printStackTrace();
        }
    }

}