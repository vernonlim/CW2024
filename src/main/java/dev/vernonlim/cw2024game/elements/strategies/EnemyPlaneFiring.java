package dev.vernonlim.cw2024game.elements.strategies;

import dev.vernonlim.cw2024game.elements.actors.ProjectileCode;

/**
 * An Enemy Plane Firing strategy.
 * <p>
 * This class contains sections from the original project.
 * Original Code: <a href="https://github.com/kooitt/CW2024/blob/master/src/main/java/com/example/demo/EnemyPlane.java">GitHub</a>
 */
public class EnemyPlaneFiring extends PlaneFiring implements Firing {
    /**
     * The fire rate in terms of probability per 50.0ms cycle.
     */
    private static final double FIRE_RATE = 0.01;

    /**
     * Constructs an Enemy Plane Firing strategy.
     */
    public EnemyPlaneFiring() {
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
    public ProjectileCode getProjectileCode() {
        return ProjectileCode.ENEMY;
    }
}
