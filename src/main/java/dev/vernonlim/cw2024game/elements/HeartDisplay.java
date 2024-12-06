package dev.vernonlim.cw2024game.elements;

import dev.vernonlim.cw2024game.Vector;
import dev.vernonlim.cw2024game.elements.configs.OverlayConfig;
import dev.vernonlim.cw2024game.factories.interfaces.OverlayFactory;
import javafx.scene.layout.HBox;

import java.util.ArrayList;

public class HeartDisplay extends ContainerElement {
    private final OverlayFactory heartFactory;
    private final ArrayList<Element> hearts = new ArrayList<>();
    private final Vector position;

    public HeartDisplay(OverlayConfig overlayConfig, int heartsToDisplay) {
        super(overlayConfig);

        this.position = overlayConfig.getPosition();

        initializeContainer();

        this.heartFactory = overlayConfig.getOverlayFactory().withNewRoot(container);

        initializeHearts(heartsToDisplay);
    }

    private void initializeContainer() {
        container = new HBox();
        node = container;
        container.setLayoutX(position.x);
        container.setLayoutY(position.y);
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
