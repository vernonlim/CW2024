package dev.vernonlim.cw2024game.elements;

import javafx.scene.layout.Pane;

public class GameOverImage extends ImageElement {
    private static final String IMAGE_NAME = "/images/gameover.png";

    public GameOverImage(Pane root, double xPosition, double yPosition) {
        super(root, IMAGE_NAME);

        view.setLayoutX(xPosition);
        view.setLayoutY(yPosition);
    }
}
