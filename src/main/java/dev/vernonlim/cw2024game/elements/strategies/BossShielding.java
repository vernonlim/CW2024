package dev.vernonlim.cw2024game.elements.strategies;

public class BossShielding extends UpdatableStrategy implements Shielding {
    private static final double BOSS_SHIELD_PROBABILITY = 0.002;
    private static final double MAX_TIME_WITH_SHIELD = 500 * 50.0f;
    private double lastShieldActivationAttempt;
    private double timeWithShieldActivated;
    private boolean isShielded;

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
