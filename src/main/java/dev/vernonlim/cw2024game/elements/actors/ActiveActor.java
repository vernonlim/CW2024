package dev.vernonlim.cw2024game.elements.actors;

import dev.vernonlim.cw2024game.elements.ImageElement;
import javafx.scene.image.*;
import javafx.scene.layout.Pane;

public abstract class ActiveActor extends ImageElement {
    private static final String IMAGE_LOCATION = "/images/";

    public ActiveActor(Pane root, String imageName, int imageHeight, double initialXPos, double initialYPos) {
        super(root, IMAGE_LOCATION + imageName);

        view.setLayoutX(initialXPos);
        view.setLayoutY(initialYPos);
        view.setFitHeight(imageHeight);
        view.setPreserveRatio(true);
    }

    public abstract void updatePosition(double deltaTime);

    public abstract void updateActor(double deltaTime, double currentTime);

    protected void moveHorizontally(double horizontalMove) {
        view.setTranslateX(view.getTranslateX() + horizontalMove);
    }

    protected void moveVertically(double verticalMove) {
        view.setTranslateY(view.getTranslateY() + verticalMove);
    }
}
