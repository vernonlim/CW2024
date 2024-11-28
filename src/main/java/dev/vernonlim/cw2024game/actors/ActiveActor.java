package dev.vernonlim.cw2024game.actors;

import javafx.scene.image.*;

public abstract class ActiveActor extends ImageView {
    private static final String IMAGE_LOCATION = "/images/";

    public ActiveActor(String imageName, int imageHeight, double initialXPos, double initialYPos) {
        //this.setImage(new Image(IMAGE_LOCATION + imageName));
        this.setImage(new Image(getClass().getResource(IMAGE_LOCATION + imageName).toExternalForm()));
        this.setLayoutX(initialXPos);
        this.setLayoutY(initialYPos);
        this.setFitHeight(imageHeight);
        this.setPreserveRatio(true);
    }

    public abstract void updatePosition(double deltaTime);

    public abstract void updateActor(double deltaTime, double currentTime);

    protected void moveHorizontally(double horizontalMove) {
        this.setTranslateX(getTranslateX() + horizontalMove);
    }

    protected void moveVertically(double verticalMove) {
        this.setTranslateY(getTranslateY() + verticalMove);
    }
}
