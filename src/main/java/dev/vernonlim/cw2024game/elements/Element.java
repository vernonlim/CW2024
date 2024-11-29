package dev.vernonlim.cw2024game.elements;

import dev.vernonlim.cw2024game.Controller;
import javafx.geometry.Bounds;
import javafx.scene.layout.Pane;
import javafx.scene.image.*;
import javafx.scene.Node;

public abstract class Element {
    protected Pane root;
    protected Node node;

    public Element(Pane root) {
        this.root = root;
    }

    public Element(Pane root, Node node) {
        this.root = root;
        this.node = node;
    }

    public void show() {
        if (!root.getChildren().contains(node)) {
            root.getChildren().add(node);
        }
    }

    public void hide() {
        root.getChildren().remove(node);
    }

    public Bounds getBoundsInParent() {
        return node.getBoundsInParent();
    }

    public double getTranslateX() {
        return node.getTranslateX();
    }
}
