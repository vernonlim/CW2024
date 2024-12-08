package dev.vernonlim.cw2024game.elements;

import dev.vernonlim.cw2024game.Main;
import dev.vernonlim.cw2024game.Vector;
import dev.vernonlim.cw2024game.configs.ElementConfig;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

public abstract class Element {
    public Pane root;
    public Node node;

    public Element(ElementConfig config) {
        this.root = config.getRoot();
    }

    public void show() {
        if (!root.getChildren().contains(node)) {
            root.getChildren().add(node);
        }
    }

    public void hide() {
        root.getChildren().remove(node);
    }

    public void setPosition(double x, double y) {
        setX(x);
        setY(y);
    }

    public Vector getPosition() {
        return new Vector(getX(), getY());
    }

    public void setPosition(Vector vector) {
        setPosition(vector.x, vector.y);
    }

    public Bounds getBoundsInParent() {
        return node.getBoundsInParent();
    }

    public double getX() {
        return node.getBoundsInParent().getCenterX();
    }

    public void setX(double xPos) {
        node.setLayoutX(xPos - getWidth() / 2);
    }

    public double getY() {
        return node.getBoundsInParent().getCenterY();
    }

    public void setY(double yPos) {
        node.setLayoutY(yPos - getHeight() / 2);
    }

    public double getWidth() {
        return node.getBoundsInParent().getWidth();
    }

    public double getHeight() {
        return node.getBoundsInParent().getHeight();
    }

    public double getHalfWidth() {
        return getWidth() / 2;
    }

    public double getHalfHeight() {
        return getHeight() / 2;
    }

    public void moveHorizontally(double horizontalMove) {
        setX(getX() + horizontalMove);
    }

    public void moveVertically(double verticalMove) {
        setY(getY() + verticalMove);
    }

    public void move(Vector vector) {
        move(vector.x, vector.y);
    }

    public void move(double x, double y) {
        moveHorizontally(x);
        moveVertically(y);
    }

    public void setXFromLeft(double xOffset) {
        setX(xOffset + getHalfWidth());
    }

    public void setXFromRight(double xOffset) {
        setX(Main.SCREEN_WIDTH - (getHalfWidth() + xOffset));
    }

    public void ensureInBounds() {
        double upperBound = getHalfHeight();
        double lowerBound = Main.SCREEN_HEIGHT - getHalfHeight();
        double leftBound = getHalfWidth();
        double rightBound = Main.SCREEN_WIDTH - getHalfWidth();

        Vector position = getPosition();
        position.ensureInBounds(leftBound, rightBound, upperBound, lowerBound);

        setPosition(position);
    }
}
