package dev.vernonlim.cw2024game.elements;

import javafx.scene.layout.Pane;

public class WinImage extends ImageElement {
    private static final String IMAGE_PATH = "/images/youwin.png";
    private static final int HEIGHT = 500;
    private static final int WIDTH = 600;

    public WinImage(Pane root, double xPosition, double yPosition) {
        super(root, IMAGE_PATH);

        this.view.setFitHeight(HEIGHT);
        this.view.setFitWidth(WIDTH);
        this.view.setLayoutX(xPosition);
        this.view.setLayoutY(yPosition);
    }
}
