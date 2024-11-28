package dev.vernonlim.cw2024game;

import dev.vernonlim.cw2024game.controller.Controller;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    private static final int SCREEN_WIDTH = 1280;
    private static final int SCREEN_HEIGHT = 720;
    private static final String TITLE = "Sky Battle";

    @Override
    public void start(Stage stage) {
        stage.setTitle(TITLE);
        stage.setResizable(true);
        stage.setHeight(SCREEN_HEIGHT);
        stage.setWidth(SCREEN_WIDTH);
        Controller myController = new Controller(stage, SCREEN_WIDTH, SCREEN_HEIGHT);
        myController.launchGame();
    }

    public static void main(String[] args) {
        launch();
    }
}