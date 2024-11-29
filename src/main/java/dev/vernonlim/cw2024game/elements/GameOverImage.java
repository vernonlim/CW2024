package dev.vernonlim.cw2024game.elements;

import dev.vernonlim.cw2024game.assets.AssetLoader;
import javafx.scene.layout.Pane;

public class GameOverImage extends ImageElement {
    private static final String IMAGE_NAME = "gameover";

    public GameOverImage(Pane root, AssetLoader loader, double xPosition, double yPosition) {
        super(root, loader, IMAGE_NAME);

        view.setLayoutX(xPosition);
        view.setLayoutY(yPosition);
    }
}
