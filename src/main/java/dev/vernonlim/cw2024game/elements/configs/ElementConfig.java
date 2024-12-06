package dev.vernonlim.cw2024game.elements.configs;

import dev.vernonlim.cw2024game.elements.Vector;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class ElementConfig {
    private Pane root;
    private Vector position;
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

    public void setPosition(Vector position) {
        setPosition(position.x, position.y);
    }

    public boolean shouldSetPosition() {
        return shouldSetPosition;
    }
}
