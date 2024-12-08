package dev.vernonlim.cw2024game.elements.strategies;

import dev.vernonlim.cw2024game.elements.actors.ProjectileCode;

public class BossFiring extends PlaneFiring implements Firing {
    private static final double BOSS_FIRE_RATE = .04;

    @Override
    protected boolean willAttemptFire() {
        return (currentTime - lastFireAttempt) > 50.0f;
    }

    @Override
    protected boolean canFire() {
        return Math.random() < BOSS_FIRE_RATE;
    }

    @Override
    public ProjectileCode getProjectileCode() {
        return ProjectileCode.BOSS;
    }
}
