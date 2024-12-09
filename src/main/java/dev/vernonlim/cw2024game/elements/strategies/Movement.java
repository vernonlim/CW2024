package dev.vernonlim.cw2024game.elements.strategies;

import dev.vernonlim.cw2024game.Vector;

/**
 * An interface for determining the next move of an Actor.
 */
public interface Movement extends Updatable {
    /**
     * Gets the next movement of the Actor.
     *
     * @return a Vector representing the next movement of the Actor
     */
    Vector getNextMovement();
}
