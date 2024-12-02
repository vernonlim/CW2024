package dev.vernonlim.cw2024game.elements.strategies;

import dev.vernonlim.cw2024game.elements.ProjectileCode;
import dev.vernonlim.cw2024game.elements.Vector;
import dev.vernonlim.cw2024game.elements.actors.ActiveActor;

public class EnemyPlaneStrategy extends PlaneStrategyImpl implements PlaneStrategy {
    private static final double FIRE_RATE = 0.01;

    public EnemyPlaneStrategy() {
    }

    @Override
    protected boolean willAttemptFire() {
        return (currentTime - lastFireAttempt) > 50.0f;
    }

    @Override
    protected boolean canFire() {
        return Math.random() < FIRE_RATE;
    }

    @Override
    public Vector getNextMovement() {
        return new Vector(-1, 0);
    }

    @Override
    public ProjectileCode getProjectileCode() {
        return ProjectileCode.ENEMY;
    }
}
