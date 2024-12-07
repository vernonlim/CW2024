package dev.vernonlim.cw2024game.elements.strategies;

import dev.vernonlim.cw2024game.Vector;

public class LinearMovement extends UpdatableStrategy implements Movement {
    private final Vector movement;

    public LinearMovement(Vector movement) {
        this.movement = movement.copy();
        movement.normalize();
    }

    @Override
    public Vector getNextMovement() {
        return movement.copy();
    }
}
