package dev.vernonlim.cw2024game.elements;

import dev.vernonlim.cw2024game.assets.AssetLoader;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class HeartDisplay extends PaneElement {
    protected final AssetLoader loader;
    private final ArrayList<Heart> hearts = new ArrayList<Heart>();
    private final double containerXPosition;
    private final double containerYPosition;

    public HeartDisplay(Pane root, AssetLoader loader, double xPosition, double yPosition, int heartsToDisplay) {
        super(root);
        this.loader = loader;

        containerXPosition = xPosition;
        containerYPosition = yPosition;
        initializeContainer();
        initializeHearts(heartsToDisplay);
    }

    private void initializeContainer() {
        container = new HBox();
        node = container;
        container.setLayoutX(containerXPosition);
        container.setLayoutY(containerYPosition);
    }

    private void initializeHearts(int heartCount) {
        for (int i = 0; i < heartCount; i++) {
            Heart heart = new Heart(container, loader);
            heart.show();

            hearts.add(heart);
        }
    }

    public int getHeartCount() {
        return hearts.size();
    }

    public void removeHeart() {
        hearts.removeLast().hide();
    }
}
