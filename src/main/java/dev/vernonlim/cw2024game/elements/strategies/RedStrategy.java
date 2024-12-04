package dev.vernonlim.cw2024game.elements.strategies;

import dev.vernonlim.cw2024game.elements.Vector;
import dev.vernonlim.cw2024game.elements.actors.ProjectileCode;

public class RedStrategy extends PlaneStrategyImpl implements PlaneStrategy {
    @Override
    protected boolean willAttemptFire() {
        return false;
    }

    @Override
    protected boolean canFire() {
        return false;
    }

    @Override
    public ProjectileCode getProjectileCode() {
        return null;
    }

    @Override
    public Vector getNextMovement() {
        return new Vector(-1, 0);
    }
}
