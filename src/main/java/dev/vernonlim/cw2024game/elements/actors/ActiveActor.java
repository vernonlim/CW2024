package dev.vernonlim.cw2024game.elements.actors;

import dev.vernonlim.cw2024game.elements.ImageElement;
import javafx.scene.image.*;
import javafx.scene.layout.Pane;

public abstract class ActiveActor extends ImageElement {
    private static final String IMAGE_LOCATION = "/images/";

    public ActiveActor(Pane root, String imageName, int imageHeight) {
        super(root, IMAGE_LOCATION + imageName);

        view.setFitHeight(imageHeight);
        view.setPreserveRatio(true);
    }

    public abstract void updatePosition(double deltaTime);

    public abstract void updateActor(double deltaTime, double currentTime);
}
