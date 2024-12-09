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

/**
 * Controller class for managing the game screens and transition between them.
 * <p>
 * This class follows the singleton pattern as only one instance is ever needed - reducing the number of command line arguments needed to be passed.
 */
public class Controller {
    /**
     * The singleton instance of the Controller.
     */
    private static Controller instance;
    /**
     * The single instance of the stage used to render the game.
     */
    private static Stage stage;
    /**
     * The factory used to construct instances of each screen.
     */
    private final ScreenFactory screenFactory;

    /**
     * Constructs a Controller instance from the given params.
     *
     * @param stage the stage the game is rendered on
     * @param loader the asset loader used
     * @param keybinds the keybinds used
     */
    private Controller(Stage stage, AssetLoader loader, KeybindStore keybinds) {
        Controller.stage = stage;
        this.screenFactory = new ScreenFactoryImpl(loader, keybinds);
    }

    /**
     * Gets the singleton controller instance.
     *
     * @return the singleton controller instance
     */
    public static Controller getController() {
        return instance;
    }

    /**
     * Gets the stage the game is rendered on.
     *
     * @return the stage the game is rendered on
     */
    public static Stage getStage() {
        return stage;
    }

    /**
     * If not already present, constructs the singleton instance of the Controller.
     *
     * @param stage the stage the game is rendered on
     * @param loader the asset loader used
     * @param keybinds the keybinds used
     * @return the instance constructed
     */
    public static Controller getController(Stage stage, AssetLoader loader, KeybindStore keybinds) {
        if (instance == null) {
            instance = new Controller(stage, loader, keybinds);
        }

        return instance;
    }

    /**
     * Triggers an alert popup that exits the game on close.
     *
     * @param message the error message to be displayed
     */
    public static void triggerAlertAndExit(String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setContentText(message);
        alert.showAndWait().ifPresent(response -> Platform.exit());
    }

    /**
     * Begins rendering the stage and loads the initial screen.
     */
    public void launchGame() {
        stage.show();
        goToScreen(ScreenCode.MAIN_MENU);
    }

    /**
     * Loads a screen with the default UserPlane.
     *
     * @param screenCode the code of the screen to load
     */
    public void goToScreen(ScreenCode screenCode) {
        goToScreen(screenCode, UserPlaneCode.REGULAR_PLANE);
    }

    /**
     * Loads a screen with a specific UserPlane.
     *
     * @param screenCode the code of the screen to load
     * @param userPlaneCode the code of the user's plane
     */
    public void goToScreen(ScreenCode screenCode, UserPlaneCode userPlaneCode) {
        Screen screen = screenFactory.createScreen(screenCode, userPlaneCode);

        // Only null if the system is exiting
        Scene scene = screen.getScene();
        stage.setScene(scene);
        screen.start();
    }
}
