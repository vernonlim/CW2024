package dev.vernonlim.cw2024game.elements;

import javafx.scene.layout.Pane;

public abstract class PaneElement extends Element {
    protected Pane container;

    public PaneElement(Pane root) {
        super(root);
    }
}
