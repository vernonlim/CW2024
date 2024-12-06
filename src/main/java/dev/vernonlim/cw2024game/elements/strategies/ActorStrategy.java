package dev.vernonlim.cw2024game.elements.strategies;

import dev.vernonlim.cw2024game.Vector;

public interface ActorStrategy {
    void updateStrategyState(double deltaTime, double currentTime);
    Vector getNextMovement();
}
