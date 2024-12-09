package dev.vernonlim.cw2024game.elements.strategies;

import dev.vernonlim.cw2024game.elements.actors.ProjectileCode;

/**
 * A Red Enemy Plane Firing strategy.
 */
public class RedEnemyFiring extends PlaneFiring implements Firing {
    /**
     * Constructs a Red Enemy Firing strategy.
     */
    public RedEnemyFiring() {
    }

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
}
