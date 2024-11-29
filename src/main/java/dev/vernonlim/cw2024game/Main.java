package dev.vernonlim.cw2024game;

import javafx.application.Application;
import javafx.stage.Stage;

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
        Controller myController = new Controller(stage);
        myController.launchGame();
    }

    public static void main(String[] args) {
        launch();
    }
}