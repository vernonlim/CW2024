package dev.vernonlim.cw2024game.elements;

import dev.vernonlim.cw2024game.assets.AssetLoader;
import javafx.scene.layout.Pane;

public class WinImage extends ImageElement {
    private static final String IMAGE_PATH = "youwin";
    private static final int HEIGHT = 500;
    private static final int WIDTH = 600;

    public WinImage(Pane root, AssetLoader loader, double xPosition, double yPosition) {
        super(root, loader, IMAGE_PATH);

        view.setFitHeight(HEIGHT);
        view.setFitWidth(WIDTH);
        view.setLayoutX(xPosition);
        view.setLayoutY(yPosition);
    }
}
