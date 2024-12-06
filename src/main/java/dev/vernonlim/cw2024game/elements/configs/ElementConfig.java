package dev.vernonlim.cw2024game.elements.configs;

import dev.vernonlim.cw2024game.Vector;
import javafx.scene.layout.Pane;

public class ElementConfig {
    private final Pane root;
    private final Vector position;
    private boolean shouldSetPosition;

    public ElementConfig(Pane root) {
        this.root = root;

        this.position = new Vector(0, 0);

        this.shouldSetPosition = false;
    }

    public Pane getRoot() {
        return root;
    }

    public Vector getPosition() {
        return position.copy();
    }

    public void setPosition(double x, double y) {
        shouldSetPosition = true;

        position.x = x;
        position.y = y;
    }

    public boolean shouldSetPosition() {
        return shouldSetPosition;
    }
}
