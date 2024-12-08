package dev.vernonlim.cw2024game.elements;

import dev.vernonlim.cw2024game.configs.ElementConfig;
import javafx.scene.layout.Pane;

/**
 * An abstract class representing an Element, but with a Pane container as its Node.
 */
public abstract class ContainerElement extends Element {
    protected Pane container;

    /**
     * Constructs a ContainerElement.
     *
     * @param config the configuration object containing the necessary data to construct the Element
     */
    public ContainerElement(ElementConfig config) {
        super(config);
    }
}
