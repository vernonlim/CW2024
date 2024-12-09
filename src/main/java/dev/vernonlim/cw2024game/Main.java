package dev.vernonlim.cw2024game;

import dev.vernonlim.cw2024game.assets.AssetLoader;
import dev.vernonlim.cw2024game.assets.UpFrontAssetLoader;
import dev.vernonlim.cw2024game.managers.KeybindStore;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Main class for CW2024Game.
 * <p>
 * This class serves as the entry point for the game.
 * <p>
 * It sets up the stage, initializes the Asset Loader, fetches user keybinds, sets up the game controller and launches the game.
 * <p>
 * This class is modified. Original Code: <a href="https://github.com/kooitt/CW2024/blob/master/src/main/java/com/example/demo/controller/Main.java">GitHub</a>
 */
public class Main extends Application {
    /**
     * The width of the game screen.
     * <p>
     * The default value present is what the game is designed for, but other values are taken into account.
     */
    public static final int SCREEN_WIDTH = 1300;

    /**
     * The height of the game screen.
     * <p>
     * The default value present is what the game is designed for, but other values are taken into account.
     */
    public static final int SCREEN_HEIGHT = 750;
    /**
     * The target frame rate of the game.
     * <p>
     * The game uses a real-time based approach to simulation and so this value can be set to anything.
     */
    public static final int FRAME_RATE = 60;
    /**
     * The game's window title.
     */
    private static final String TITLE = "Sky Battle";
    /**
     * The path to the keybinds configuration file.
     * <p>
     * This file is a .json file present in the resources folder that contains mappings between KeyCodes and Actions.
     */
    private static final String KEYBINDS_PATH = "/keybinds.json";

    /**
     * Constructs an instance of the Main class, serving as the entry point for the game.
     */
    public Main() {
    }

    /**
     * The main method which serves as the entry point for the game.
     * <p>
     * Nothing is done with the command line arguments.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch();
    }

    /**
     * The start method is called after the game is launched.
     * <p>
     * It initializes the stage with the settings in the class, sets up the Asset Loader, loads the keybinds from the configuration file, sets up the Controller and launches the game.
     *
     * @param stage the primary stage for the game
     */
    @Override
    public void start(Stage stage) {
        stage.setTitle(TITLE);
        stage.setResizable(true);
        stage.setHeight(SCREEN_HEIGHT);
        stage.setWidth(SCREEN_WIDTH);

        AssetLoader loader = new UpFrontAssetLoader();

        KeybindStore keybinds = new KeybindStore(KEYBINDS_PATH);

        Controller myController = Controller.getController(stage, loader, keybinds);
        myController.launchGame();
    }
}