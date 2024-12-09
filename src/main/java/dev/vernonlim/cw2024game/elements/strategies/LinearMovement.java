package dev.vernonlim.cw2024game.elements.strategies;

import dev.vernonlim.cw2024game.Vector;

/**
 * A Linear Movement strategy.
 */
public class LinearMovement extends UpdatableStrategy implements Movement {
    /**
     * The one movement this strategy will repeat.
     */
    private final Vector movement;

    /**
     * Constructs a Linear Movement strategy from the given params.
     *
     * @param movement the one movement this strategy will repeat
     */
    public LinearMovement(Vector movement) {
        this.movement = movement.copy();
        movement.normalize();
    }

    @Override
    public Vector getNextMovement() {
        return movement.copy();
    }
}
