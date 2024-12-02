package dev.vernonlim.cw2024game;

import dev.vernonlim.cw2024game.assets.AssetLoader;
import dev.vernonlim.cw2024game.factories.ScreenFactoryImpl;
import dev.vernonlim.cw2024game.factories.interfaces.ScreenFactory;
import dev.vernonlim.cw2024game.managers.KeybindStore;
import dev.vernonlim.cw2024game.screens.LevelParent;
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
    private final ScreenFactory screenFactory;

    public Controller(Stage stage, AssetLoader loader, KeybindStore keybinds) {
        this.stage = stage;
        this.loader = loader;
        this.keybinds = keybinds;
        this.screenFactory = new ScreenFactoryImpl(stage, this, loader, keybinds);
    }

    public static void triggerAlertAndExit(String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setContentText(message);
        alert.showAndWait().ifPresent(response -> Platform.exit());
    }

    public void launchGame() {
        stage.show();
        goToLevel(ScreenList.ONE);
    }

    public void goToLevel(ScreenList level) {
        Screen screen = screenFactory.createScreen(level);

        // Only null if the system if exiting
        Scene scene = screen.getScene();
        stage.setScene(scene);
        screen.start();
    }
}
