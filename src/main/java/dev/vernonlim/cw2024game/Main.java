package dev.vernonlim.cw2024game;

import dev.vernonlim.cw2024game.assets.AssetLoader;
import dev.vernonlim.cw2024game.assets.CachedAssetLoader;
import dev.vernonlim.cw2024game.assets.UpFrontAssetLoader;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    public static final int SCREEN_WIDTH = 1300;
    public static final int SCREEN_HEIGHT = 750;
    private static final String TITLE = "Sky Battle";

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle(TITLE);
        stage.setResizable(true);
        stage.setHeight(SCREEN_HEIGHT);
        stage.setWidth(SCREEN_WIDTH);

//        AssetLoader loader = new UpFrontAssetLoader();
        AssetLoader loader = new UpFrontAssetLoader();

        Controller myController = new Controller(stage, loader);
        myController.launchGame();
    }
}