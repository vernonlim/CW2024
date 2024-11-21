package dev.vernonlim.cw2024game.controller;

import java.lang.reflect.Constructor;
import java.util.Map;

import static java.util.Map.entry;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import dev.vernonlim.cw2024game.levels.*;

public class Controller {
    private static final Map<String, Class<? extends LevelParent>> levels = Map.ofEntries(
            entry("LEVEL_ONE", LevelOne.class),
            entry("LEVEL_TWO", LevelTwo.class)
    );

    private final Stage stage;

    public Controller(Stage stage) {
        this.stage = stage;
    }

    public void launchGame() {
        stage.show();
        goToLevel("LEVEL_ONE");
    }

    public void goToLevel(String levelName) {
        try {
            Class<? extends LevelParent> level = levels.get(levelName);
            Constructor<?> constructor = level.getConstructor(Controller.class, double.class, double.class);
            LevelParent myLevel = (LevelParent) constructor.newInstance(this, stage.getHeight(), stage.getWidth());
            Scene scene = myLevel.initializeScene();
            stage.setScene(scene);
            myLevel.startGame();
        } catch (Exception e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText("Failed to load Level: " + levelName
                    + "\nAdditional information: " + e.getMessage());
            alert.showAndWait().ifPresent(response -> Platform.exit());
        }
    }
}
