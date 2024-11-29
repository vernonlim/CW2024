package dev.vernonlim.cw2024game;

import java.lang.reflect.Constructor;
import java.net.URL;
import java.util.Map;

import static java.util.Map.entry;

import dev.vernonlim.cw2024game.assets.AssetLoader;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import dev.vernonlim.cw2024game.screens.*;

public class Controller {
    private final Stage stage;
    private final AssetLoader loader;

    public Controller(Stage stage, AssetLoader loader) {
        this.stage = stage;
        this.loader = loader;
    }

    public void launchGame() {
        stage.show();
        goToLevel("LEVEL_ONE");
    }

    public void goToLevel(String levelName) {
        Screen screen = null;
        switch (levelName) {
            case "LEVEL_ONE":
                screen = new LevelOne(stage, this, loader);
                break;
            case "LEVEL_TWO":
                screen = new LevelTwo(stage, this, loader);
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
}
