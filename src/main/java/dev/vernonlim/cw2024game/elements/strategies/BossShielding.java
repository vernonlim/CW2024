package dev.vernonlim.cw2024game.elements.strategies;

/**
 * A Shielding strategy for Boss enemies.
 * <p>
 * This class contains sections from the original project.
 * Original Code: <a href="https://github.com/kooitt/CW2024/blob/master/src/main/java/com/example/demo/Boss.java">GitHub</a>
 */
public class BossShielding extends UpdatableStrategy implements Shielding {
    /**
     * The probability the Boss should shield in terms of probability per 50ms cycle.
     */
    private static final double BOSS_SHIELD_PROBABILITY = 0.002;

    /**
     * The amount of time the Boss should spend shielded.
     */
    private static final double MAX_TIME_WITH_SHIELD = 500 * 50.0f;

    /**
     * The last time a shield activation was attempted.
     */
    private double lastShieldActivationAttempt;

    /**
     * The time spent with the shield activated.
     */
    private double timeWithShieldActivated;

    /**
     * Indicates whether the Boss should be shielding.
     */
    private boolean isShielded;

    /**
     * Constructs a Boss Shielding strategy.
     */
    public BossShielding() {
        timeWithShieldActivated = 0;
        lastShieldActivationAttempt = -99999;

        isShielded = false;
    }

    @Override
    public void updateStrategyState(double deltaTime, double currentTime) {
        super.updateStrategyState(deltaTime, currentTime);

        if (isShielded) {
            timeWithShieldActivated += deltaTime;
            lastShieldActivationAttempt = currentTime;
        } else {
            if (currentTime - lastShieldActivationAttempt > 50.0f) {
                lastShieldActivationAttempt = currentTime;

                if (Math.random() < BOSS_SHIELD_PROBABILITY) {
                    isShielded = true;
                }
            }
        }

        if (timeWithShieldActivated > MAX_TIME_WITH_SHIELD) {
            isShielded = false;
            timeWithShieldActivated = 0;
        }
    }

    @Override
    public boolean isShielded() {
        return isShielded;
    }
}
