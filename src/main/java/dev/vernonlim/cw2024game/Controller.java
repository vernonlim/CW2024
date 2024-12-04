package dev.vernonlim.cw2024game;

import dev.vernonlim.cw2024game.assets.AssetLoader;
import dev.vernonlim.cw2024game.elements.actors.UserPlaneCode;
import dev.vernonlim.cw2024game.factories.ScreenFactoryImpl;
import dev.vernonlim.cw2024game.factories.interfaces.ScreenFactory;
import dev.vernonlim.cw2024game.managers.KeybindStore;
import dev.vernonlim.cw2024game.screens.Screen;
import dev.vernonlim.cw2024game.screens.ScreenCode;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class Controller {
    private final Stage stage;
    private final ScreenFactory screenFactory;

    public Controller(Stage stage, AssetLoader loader, KeybindStore keybinds) {
        this.stage = stage;
        this.screenFactory = new ScreenFactoryImpl(stage, this, loader, keybinds);
    }

    public static void triggerAlertAndExit(String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setContentText(message);
        alert.showAndWait().ifPresent(response -> Platform.exit());
    }

    public void launchGame() {
        stage.show();
        goToScreen(ScreenCode.MAIN_MENU);
    }

    public void goToScreen(ScreenCode screenCode) {
        goToScreen(screenCode, UserPlaneCode.REGULAR_PLANE);
    }

    public void goToScreen(ScreenCode screenCode, UserPlaneCode userPlaneCode) {
        Screen screen = screenFactory.createScreen(screenCode, userPlaneCode);

        // Only null if the system if exiting
        Scene scene = screen.getScene();
        stage.setScene(scene);
        screen.start();
    }
}
