package dev.vernonlim.cw2024game.elements.actors;

import dev.vernonlim.cw2024game.assets.AssetLoader;
import dev.vernonlim.cw2024game.elements.ImageElement;
import javafx.scene.layout.Pane;

public class ShieldImage extends ImageElement {
    private static final String IMAGE_NAME = "shield";
    private static final int SHIELD_SIZE = 68;

    public ShieldImage(Pane root, AssetLoader loader) {
        super(root, loader, IMAGE_NAME);

        view.setFitHeight(SHIELD_SIZE);
        view.setFitWidth(SHIELD_SIZE);
    }

    public ShieldImage(Pane root, AssetLoader loader, double xPosition, double yPosition) {
        super(root, loader, IMAGE_NAME);

        view.setLayoutX(xPosition);
        view.setLayoutY(yPosition);
        view.setFitHeight(SHIELD_SIZE);
        view.setFitWidth(SHIELD_SIZE);
    }
}
