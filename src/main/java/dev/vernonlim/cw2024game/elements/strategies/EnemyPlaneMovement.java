package dev.vernonlim.cw2024game.elements.strategies;

import dev.vernonlim.cw2024game.Vector;

/**
 * An Enemy Plane Movement strategy.
 */
public class EnemyPlaneMovement extends UpdatableStrategy implements Movement {
    /**
     * Constructs an Enemy Plane Movement strategy.
     */
    public EnemyPlaneMovement() {
    }

    @Override
    public Vector getNextMovement() {
        return new Vector(-1, 0);
    }
}
