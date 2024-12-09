package dev.vernonlim.cw2024game.overlays;

import dev.vernonlim.cw2024game.Main;
import dev.vernonlim.cw2024game.configs.ElementConfig;
import dev.vernonlim.cw2024game.elements.ContainerElement;
import javafx.scene.layout.Pane;

/**
 * A parent class for elements that render above the main content of the game - i.e Overlays.
 */
public abstract class FloatingOverlay extends ContainerElement {
    /**
     * Constructs a Floating Overlay
     *
     * @param config the configuration object containing the necessary data to construct the Overlay
     */
    public FloatingOverlay(ElementConfig config) {
        super(config);

        this.container = new Pane();
        this.node = container;

        container.setMaxHeight(Main.SCREEN_HEIGHT);
        container.setMaxWidth(Main.SCREEN_WIDTH);
    }
}
