package dev.vernonlim.cw2024game.actors;

import dev.vernonlim.cw2024game.controller.Controller;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ShieldImage extends ImageView {
    private static final String IMAGE_NAME = "/images/shield.png";
    private static final int SHIELD_SIZE = 68;

    public ShieldImage(double xPosition, double yPosition) {
        this.setLayoutX(xPosition);
        this.setLayoutY(yPosition);
        this.setImage(new Image(Controller.fetchResourcePath(IMAGE_NAME)));
        this.setVisible(false);
        this.setFitHeight(SHIELD_SIZE);
        this.setFitWidth(SHIELD_SIZE);
    }

    public void showShield() {
        this.setVisible(true);
    }

    public void hideShield() {
        this.setVisible(false);
    }
}
