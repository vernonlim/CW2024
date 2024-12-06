package dev.vernonlim.cw2024game.elements;

import dev.vernonlim.cw2024game.elements.configs.ElementConfig;
import javafx.scene.layout.Pane;

public abstract class ContainerElement extends Element {
    protected Pane container;

    public ContainerElement(ElementConfig config) {
        super(config);
    }
}
