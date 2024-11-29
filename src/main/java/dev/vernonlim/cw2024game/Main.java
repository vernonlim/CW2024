package dev.vernonlim.cw2024game;

import dev.vernonlim.cw2024game.assets.AssetLoader;
import dev.vernonlim.cw2024game.assets.CachedAssetLoader;
import dev.vernonlim.cw2024game.assets.UpFrontAssetLoader;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Main extends Application {
    public static final int SCREEN_WIDTH = 1300;
    public static final int SCREEN_HEIGHT = 750;
    private static final String TITLE = "Sky Battle";

    @Override
    public void start(Stage stage) {
        stage.setTitle(TITLE);
        stage.setResizable(true);
        stage.setHeight(SCREEN_HEIGHT);
        stage.setWidth(SCREEN_WIDTH);

//        AssetLoader loader = new UpFrontAssetLoader();
        AssetLoader loader = new CachedAssetLoader();

        Controller myController = new Controller(stage, loader);
        myController.launchGame();
    }

    public static void main(String[] args) {
        launch();
    }
}