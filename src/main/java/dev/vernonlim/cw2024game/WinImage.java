package dev.vernonlim.cw2024game;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class WinImage extends ImageView {

    private static final String IMAGE_NAME = "/dev/vernonlim/cw2024game/images/youwin.png";
    private static final int HEIGHT = 500;
    private static final int WIDTH = 600;

    public WinImage(double xPosition, double yPosition) {
        this.setImage(new Image(getClass().getResource(IMAGE_NAME).toExternalForm()));
        this.setVisible(false);
        this.setFitHeight(HEIGHT);
        this.setFitWidth(WIDTH);
        this.setLayoutX(xPosition);
        this.setLayoutY(yPosition);
    }

    public void showWinImage() {
        this.setVisible(true);
    }

}
