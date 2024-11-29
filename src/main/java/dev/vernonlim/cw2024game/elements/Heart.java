package dev.vernonlim.cw2024game.elements;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Heart extends ImageElement {
    private static final String HEART_IMAGE_NAME = "/images/heart.png";
    private static final int HEART_HEIGHT = 50;

    public Heart(Pane root) {
        super(root, HEART_IMAGE_NAME);

        view.setFitHeight(HEART_HEIGHT);
        view.setPreserveRatio(true);
    }
}
