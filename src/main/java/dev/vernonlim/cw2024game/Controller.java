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
    private static Controller instance;
    private static Stage stage;
    private final ScreenFactory screenFactory;

    private Controller(Stage stage, AssetLoader loader, KeybindStore keybinds) {
        Controller.stage = stage;
        this.screenFactory = new ScreenFactoryImpl(loader, keybinds);
    }

    public static Controller getController() {
        return instance;
    }

    public static Stage getStage() {
        return stage;
    }

    public static Controller getController(Stage stage, AssetLoader loader, KeybindStore keybinds) {
        if (instance == null) {
            instance = new Controller(stage, loader, keybinds);
        }

        return instance;
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

        // Only null if the system is exiting
        Scene scene = screen.getScene();
        stage.setScene(scene);
        screen.start();
    }
}
