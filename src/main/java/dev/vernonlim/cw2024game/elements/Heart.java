package dev.vernonlim.cw2024game.elements;

import dev.vernonlim.cw2024game.assets.AssetLoader;
import javafx.scene.layout.Pane;

public class Heart extends ImageElement {
    private static final String HEART_IMAGE_NAME = "heart";
    private static final int HEART_HEIGHT = 50;

    public Heart(Pane root, AssetLoader loader) {
        super(root, loader, HEART_IMAGE_NAME);

        view.setFitHeight(HEART_HEIGHT);
        view.setPreserveRatio(true);
    }
}
