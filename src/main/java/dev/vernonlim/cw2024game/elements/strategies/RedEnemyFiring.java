package dev.vernonlim.cw2024game.elements.strategies;

import dev.vernonlim.cw2024game.elements.actors.ProjectileCode;

public class RedEnemyFiring extends PlaneFiring implements Firing {
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