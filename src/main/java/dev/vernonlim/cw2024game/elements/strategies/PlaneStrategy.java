package dev.vernonlim.cw2024game.elements.strategies;

import dev.vernonlim.cw2024game.elements.ProjectileCode;
import dev.vernonlim.cw2024game.elements.Vector;

public interface PlaneStrategy {
    void updateStrategyState(double deltaTime, double currentTime);
    Vector getNextMovement();
    boolean shouldFire();
    ProjectileCode getProjectileCode();
}
