package dev.vernonlim.cw2024game.elements.strategies;

import dev.vernonlim.cw2024game.elements.actors.ProjectileCode;

/**
 * A Firing strategy for Boss enemies.
 * <p>
 * This class contains sections from the original project.
 * Original Code: <a href="https://github.com/kooitt/CW2024/blob/master/src/main/java/com/example/demo/Boss.java">GitHub</a>
 */
public class BossFiring extends PlaneFiring implements Firing {
    /**
     * The fire rate of the Boss in terms of chance per 50.0ms cycle.
     */
    private static final double BOSS_FIRE_RATE = .04;

    /**
     * Constructs a Boss Firing strategy.
     */
    public BossFiring() {
    }

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
