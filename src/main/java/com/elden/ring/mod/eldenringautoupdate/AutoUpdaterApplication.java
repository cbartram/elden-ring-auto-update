package com.elden.ring.mod.eldenringautoupdate;

import com.elden.ring.mod.eldenringautoupdate.model.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;

@Log4j2
public class AutoUpdaterApplication extends Application {

    private Controller autoUpdaterController;

    @Override
    public void start(Stage stage) throws IOException {
        log.info("Starting application.");
        FXMLLoader loader = new FXMLLoader(AutoUpdaterApplication.class.getResource("auto-updater.fxml"));
        Scene scene = new Scene(loader.load(), 330, 270);
        stage.setTitle("Elden Ring Auto Updater");
        stage.setScene(scene);
        stage.show();

        AutoUpdaterController controller = loader.getController();
        autoUpdaterController = controller;
        controller.onInitialize();
    }

    @Override
    public void stop() {
      log.info("Stopping application.");
      autoUpdaterController.onExit();
    }

    public static void main(String[] args) {
        launch();
    }
}