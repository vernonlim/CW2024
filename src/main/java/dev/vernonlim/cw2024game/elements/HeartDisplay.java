package dev.vernonlim.cw2024game.elements;

import dev.vernonlim.cw2024game.factories.interfaces.OverlayFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class HeartDisplay extends PaneElement {
    private final OverlayFactory heartFactory;
    private final ArrayList<Element> hearts = new ArrayList<>();
    private final double containerXPosition;
    private final double containerYPosition;

    public HeartDisplay(OverlayFactory overlayFactory, Pane root, double xPosition, double yPosition, int heartsToDisplay) {
        super(root);

        initializeContainer();

        this.heartFactory = overlayFactory.withNewRoot(container);

        containerXPosition = xPosition;
        containerYPosition = yPosition;

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
            Element heart = heartFactory.createHeart();

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
