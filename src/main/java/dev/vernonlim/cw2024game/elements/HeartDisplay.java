package dev.vernonlim.cw2024game.elements;

import dev.vernonlim.cw2024game.Vector;
import dev.vernonlim.cw2024game.configs.OverlayConfig;
import dev.vernonlim.cw2024game.factories.interfaces.OverlayFactory;
import javafx.scene.layout.HBox;

import java.util.ArrayList;

/**
 * An element displaying the user's health with a sequence of Hearts.
 * <p>
 * This class is modified.
 * Original Code: <a href="https://github.com/kooitt/CW2024/blob/master/src/main/java/com/example/demo/HeartDisplay.java">GitHub</a>
 */
public class HeartDisplay extends ContainerElement {
    /**
     * The OverlayFactory for this element used to create the Hearts.
     */
    private final OverlayFactory heartFactory;

    /**
     * The list of Hearts which gets modified as the element gets updated.
     */
    private final ArrayList<Element> hearts = new ArrayList<>();

    /**
     * The position of the HeartDisplay.
     */
    private final Vector position;

    /**
     * Constructs a HeartDisplay from the given params.
     *
     * @param overlayConfig   the configuration object containing the necessary data to construct the HeartDisplay
     * @param heartsToDisplay the number of hearts to initially display
     */
    public HeartDisplay(OverlayConfig overlayConfig, int heartsToDisplay) {
        super(overlayConfig);

        this.position = overlayConfig.getPosition();

        initializeContainer();

        this.heartFactory = overlayConfig.getOverlayFactory().withNewRoot(container);

        initializeHearts(heartsToDisplay);
    }

    /**
     * Initializes the container storing the hearts.
     */
    private void initializeContainer() {
        container = new HBox();
        node = container;
        container.setLayoutX(position.x);
        container.setLayoutY(position.y);
    }

    /**
     * Initializes the Hearts inside of the container.
     *
     * @param heartCount the number of hearts to add
     */
    private void initializeHearts(int heartCount) {
        for (int i = 0; i < heartCount; i++) {
            Element heart = heartFactory.createHeart();

            heart.show();

            hearts.add(heart);
        }
    }

    /**
     * Gets the current number of Hearts.
     *
     * @return the current number of Hearts
     */
    public int getHeartCount() {
        return hearts.size();
    }

    /**
     * Removes a heart from the container.
     */
    public void removeHeart() {
        hearts.removeLast().hide();
    }
}
