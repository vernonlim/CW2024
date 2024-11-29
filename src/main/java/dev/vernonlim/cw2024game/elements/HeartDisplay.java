package dev.vernonlim.cw2024game.elements;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class HeartDisplay extends PaneElement {
    private final ArrayList<Heart> hearts = new ArrayList<Heart>();
    private final double containerXPosition;
    private final double containerYPosition;

    public HeartDisplay(Pane root, double xPosition, double yPosition, int heartsToDisplay) {
        super(root);

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
            Heart heart = new Heart(container);
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
