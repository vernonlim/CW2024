package dev.vernonlim.cw2024game;

import dev.vernonlim.cw2024game.assets.AssetLoader;
import dev.vernonlim.cw2024game.managers.KeybindStore;
import dev.vernonlim.cw2024game.screens.LevelOne;
import dev.vernonlim.cw2024game.screens.LevelTwo;
import dev.vernonlim.cw2024game.screens.Screen;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class Controller {
    private final Stage stage;
    private final AssetLoader loader;
    private final KeybindStore keybinds;

    public Controller(Stage stage, AssetLoader loader, KeybindStore keybinds) {
        this.stage = stage;
        this.loader = loader;
        this.keybinds = keybinds;
    }

    public static void triggerAlertAndExit(String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setContentText(message);
        alert.showAndWait().ifPresent(response -> Platform.exit());
    }

    public void launchGame() {
        stage.show();
        goToLevel(Level.ONE);
    }

    public void goToLevel(Level level) {
        Screen screen = null;
        switch (level) {
            case Level.ONE:
                screen = new LevelOne(stage, this, loader, keybinds);
                break;
            case Level.TWO:
                screen = new LevelTwo(stage, this, loader, keybinds);
                break;
            default:
                triggerAlertAndExit("Couldn't load Level: " + level);
        }

        // Only null if the system if exiting
        Scene scene = screen.getScene();
        stage.setScene(scene);
        screen.start();
    }
}
