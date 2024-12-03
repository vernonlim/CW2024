package dev.vernonlim.cw2024game.elements.strategies;

import dev.vernonlim.cw2024game.elements.Vector;

public class LinearProjectileStrategy implements ActorStrategy {
    public Vector movement;

    public LinearProjectileStrategy(Vector movement) {
        this.movement = movement.copy();
        movement.normalize();
    }

    @Override
    public void updateStrategyState(double deltaTime, double currentTime) {};

    @Override
    public Vector getNextMovement() {
        return movement.copy();
    }
}
