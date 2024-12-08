package dev.vernonlim.cw2024game.elements;

import dev.vernonlim.cw2024game.Main;
import dev.vernonlim.cw2024game.Vector;
import dev.vernonlim.cw2024game.configs.ElementConfig;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

/**
 * An abstract class representing a node with a root that it's a child of.
 */
public abstract class Element {
    /**
     * The root this element is based on.
     */
    public Pane root;

    /**
     * The node containing the element's contents.
     */
    public Node node;

    /**
     * Constructs an Element.
     *
     * @param config the configuration object containing the necessary data to construct the Element
     */
    public Element(ElementConfig config) {
        this.root = config.getRoot();
    }

    /**
     * Adds the element's node to its root.
     */
    public void show() {
        if (!root.getChildren().contains(node)) {
            root.getChildren().add(node);
        }
    }

    /**
     * Removes the element's node from its root.
     */
    public void hide() {
        root.getChildren().remove(node);
    }

    /**
     * Sets the element's position.
     *
     * @param x the x position
     * @param y the y position
     */
    public void setPosition(double x, double y) {
        setX(x);
        setY(y);
    }

    /**
     * Gets the element's position in the form of a Vector.
     *
     * @return a Vector containing the x and y positions of the element
     */
    public Vector getPosition() {
        return new Vector(getX(), getY());
    }

    /**
     * Sets the element's position with a Vector.
     *
     * @param vector the Vector containing the x and y positions the element should be moved to
     */
    public void setPosition(Vector vector) {
        setPosition(vector.x, vector.y);
    }

    /**
     * Gets the bounds of the element in its parent.
     *
     * @return the bounds of the element in its parent
     */
    public Bounds getBoundsInParent() {
        return node.getBoundsInParent();
    }

    /**
     * Gets the x position of the element.
     *
     * @return the x position of the element
     */
    public double getX() {
        return node.getBoundsInParent().getCenterX();
    }

    /**
     * Sets the x position of the element.
     *
     * @param xPos the x position the element should be moved to
     */
    public void setX(double xPos) {
        node.setLayoutX(xPos - getWidth() / 2);
    }

    /**
     * Gets the y position of the element.
     *
     * @return the y position of the element
     */
    public double getY() {
        return node.getBoundsInParent().getCenterY();
    }

    /**
     * Sets the y position of the element.
     *
     * @param yPos the y position the element should be moved to
     */
    public void setY(double yPos) {
        node.setLayoutY(yPos - getHeight() / 2);
    }

    /**
     * Gets the width of the element.
     *
     * @return the width of the element
     */
    public double getWidth() {
        return node.getBoundsInParent().getWidth();
    }

    /**
     * Gets the height of the element.
     *
     * @return the height of the element
     */
    public double getHeight() {
        return node.getBoundsInParent().getHeight();
    }

    /**
     * Gets half the width of the element.
     *
     * @return half the width of the element
     */
    public double getHalfWidth() {
        return getWidth() / 2;
    }

    /**
     * Gets half the height of the element.
     *
     * @return half the height of the element
     */
    public double getHalfHeight() {
        return getHeight() / 2;
    }

    /**
     * Moves the element horizontally by the parameter passed.
     *
     * @param horizontalMove the amount to move the element horizontally
     */
    public void moveHorizontally(double horizontalMove) {
        setX(getX() + horizontalMove);
    }

    /**
     * Moves the element vertically by the parameter passed.
     *
     * @param verticalMove the amount to move the element vertically
     */
    public void moveVertically(double verticalMove) {
        setY(getY() + verticalMove);
    }

    /**
     * Moves the element by an input Vector.
     *
     * @param vector the Vector the element will be moved by
     */
    public void move(Vector vector) {
        move(vector.x, vector.y);
    }

    /**
     * Moves the element by the given x, y distances.
     *
     * @param x the horizontal distance the element will move
     * @param y the vertical distance the element will move
     */
    public void move(double x, double y) {
        moveHorizontally(x);
        moveVertically(y);
    }

    /**
     * Sets the element's x position from the left.
     *
     * @param xOffset the amount of distance from the left the element will be moved to
     */
    public void setXFromLeft(double xOffset) {
        setX(xOffset + getHalfWidth());
    }

    /**
     * Sets the element's x position from the right.
     *
     * @param xOffset the amount of distance from the right the element will be moved to
     */
    public void setXFromRight(double xOffset) {
        setX(Main.SCREEN_WIDTH - (getHalfWidth() + xOffset));
    }

    /**
     * Ensure the element is in bounds by moving it to the bounds if it's outside.
     */
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
