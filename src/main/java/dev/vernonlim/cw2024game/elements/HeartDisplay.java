package dev.vernonlim.cw2024game.elements;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class HeartDisplay extends PaneElement {
    private static final String HEART_IMAGE_NAME = "/images/heart.png";
    private static final int HEART_HEIGHT = 50;
    private final double containerXPosition;
    private final double containerYPosition;
    private final int numberOfHeartsToDisplay;

    public HeartDisplay(Pane root, double xPosition, double yPosition, int heartsToDisplay) {
        super(root);

        this.containerXPosition = xPosition;
        this.containerYPosition = yPosition;
        this.numberOfHeartsToDisplay = heartsToDisplay;
        initializeContainer();
        initializeHearts();
    }

    private void initializeContainer() {
        this.container = new HBox();
        this.node = container;
        this.container.setLayoutX(containerXPosition);
        this.container.setLayoutY(containerYPosition);
    }

    private void initializeHearts() {
        for (int i = 0; i < numberOfHeartsToDisplay; i++) {
            ImageView heart = new ImageView(new Image(getClass().getResource(HEART_IMAGE_NAME).toExternalForm()));

            heart.setFitHeight(HEART_HEIGHT);
            heart.setPreserveRatio(true);
            this.container.getChildren().add(heart);
        }
    }

    public int getHeartCount() {
        return container.getChildren().size();
    }

    public void removeHeart() {
        if (!this.container.getChildren().isEmpty())
            this.container.getChildren().removeFirst();
    }
}
