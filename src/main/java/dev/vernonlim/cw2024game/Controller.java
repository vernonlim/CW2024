package dev.vernonlim.cw2024game;

import java.lang.reflect.Constructor;
import java.net.URL;
import java.util.Map;

import static java.util.Map.entry;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import dev.vernonlim.cw2024game.screens.*;

public class Controller {
    private final Stage stage;

    public Controller(Stage stage) {
        this.stage = stage;
    }

    public void launchGame() {
        stage.show();
        goToLevel("LEVEL_ONE");
    }

    public void goToLevel(String levelName) {
        Screen screen = null;
        switch (levelName) {
            case "LEVEL_ONE":
                screen = new LevelOne(this);
                break;
            case "LEVEL_TWO":
                screen = new LevelTwo(this);
                break;
            default:
                triggerAlertAndExit("Couldn't load Level: " + levelName);
        }

        // Only null if the system if exiting
        Scene scene = screen.getScene();
        stage.setScene(scene);
        screen.start();
    }

    public static void triggerAlertAndExit(String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setContentText(message);
        alert.showAndWait().ifPresent(response -> Platform.exit());
    }

    public static String fetchResourcePath(String path) {
        if (path == null) {
            triggerAlertAndExit("Tried to find an image with no name");
        }

        // if triggerAlertAndExit() works properly, path will not
        // be null here because the program has exited
        URL resourcePath = Controller.class.getResource(path);
        if (resourcePath == null) {
            triggerAlertAndExit("Tried to load image which doesn't exist: " + path);
        }

        // similar here
        return resourcePath.toExternalForm();
    }
}
