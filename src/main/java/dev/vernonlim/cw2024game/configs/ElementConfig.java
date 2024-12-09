package dev.vernonlim.cw2024game.configs;

import dev.vernonlim.cw2024game.Vector;
import javafx.scene.layout.Pane;

/**
 * A data class used for streamlining Element creation
 */
public class ElementConfig {
    /**
     * The root Pane on which the Element is based.
     */
    private final Pane root;

    /**
     * The position of the Element.
     */
    private final Vector position;

    /**
     * Whether the position of the Element should be set.
     */
    private boolean shouldSetPosition;

    /**
     * Constructs an ElementConfig with the given params.
     *
     * @param root the root Pane on which the Element is based
     */
    public ElementConfig(Pane root) {
        this.root = root;
        this.position = new Vector(0, 0);
        this.shouldSetPosition = false;
    }

    /**
     * Gets the root Pane.
     *
     * @return the root Pane
     */
    public Pane getRoot() {
        return root;
    }

    /**
     * Gets the position as a Vector.
     *
     * @return the position as a Vector
     */
    public Vector getPosition() {
        return position.copy();
    }

    /**
     * Sets the position.
     *
     * @param x the x-coordinate to set
     * @param y the y-coordinate to set
     */
    public void setPosition(double x, double y) {
        shouldSetPosition = true;
        position.x = x;
        position.y = y;
    }

    /**
     * Indicates if the position should be set.
     *
     * @return true if the position should be set, false otherwise
     */
    public boolean shouldSetPosition() {
        return shouldSetPosition;
    }

    /**
     * Sets whether the position should be set.
     *
     * @param shouldSetPosition true if the position should be set, false otherwise
     */
    public void setShouldSetPosition(boolean shouldSetPosition) {
        this.shouldSetPosition = shouldSetPosition;
    }
}
