package dev.vernonlim.cw2024game.controller;

import java.lang.reflect.Constructor;
import java.net.URL;
import java.util.Map;

import static java.util.Map.entry;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import dev.vernonlim.cw2024game.levels.*;

public class Controller {
    private final int screenWidth;
    private final int screenHeight;

    private static final Map<String, Class<? extends LevelParent>> levels = Map.ofEntries(
            entry("LEVEL_ONE", LevelOne.class),
            entry("LEVEL_TWO", LevelTwo.class)
    );

    private final Stage stage;

    public Controller(Stage stage, int screenWidth, int screenHeight) {
        this.stage = stage;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
    }

    public void launchGame() {
        stage.show();
        goToLevel("LEVEL_ONE");
    }

    public void goToLevel(String levelName) {
        try {
            Class<? extends LevelParent> level = levels.get(levelName);
            Constructor<?> constructor = level.getConstructor(Controller.class, double.class, double.class);
            LevelParent myLevel = (LevelParent) constructor.newInstance(this, screenHeight, screenWidth);
            Scene scene = myLevel.initializeScene();
            stage.setScene(scene);
            myLevel.startGame();
        } catch (Exception e) {
            triggerAlertAndExit("Failed to load Level: " + levelName
                    + "\nAdditional information: " + e.getMessage());
        }
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
