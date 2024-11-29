package dev.vernonlim.cw2024game.elements.actors;

import dev.vernonlim.cw2024game.Controller;
import dev.vernonlim.cw2024game.elements.ImageElement;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class ShieldImage extends ImageElement {
    private static final String IMAGE_NAME = "/images/shield.png";
    private static final int SHIELD_SIZE = 68;

    public ShieldImage(Pane root, double xPosition, double yPosition) {
        super(root, IMAGE_NAME);

        view.setLayoutX(xPosition);
        view.setLayoutY(yPosition);
        view.setFitHeight(SHIELD_SIZE);
        view.setFitWidth(SHIELD_SIZE);
    }
}
