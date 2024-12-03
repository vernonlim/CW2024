package dev.vernonlim.cw2024game.elements.strategies;

import dev.vernonlim.cw2024game.elements.ProjectileCode;
import dev.vernonlim.cw2024game.elements.Vector;
import dev.vernonlim.cw2024game.elements.actors.ActiveActor;
import dev.vernonlim.cw2024game.elements.actors.Boss;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BossStrategyImpl extends PlaneStrategyImpl implements BossStrategy {
    private static final double BOSS_FIRE_RATE = .04;
    private static final double BOSS_SHIELD_PROBABILITY = 0.002;
    private static final int MOVE_FREQUENCY_PER_CYCLE = 5;
    private static final double MAX_TIME_WITH_SAME_MOVE = 10 * 50.0f;
    private static final double MAX_TIME_WITH_SHIELD = 500 * 50.0f;
    private final List<Double> movePattern;
    private double timeMovingInSameDirection;
    private int indexOfCurrentMove;
    private double lastShieldActivationAttempt;
    private double timeWithShieldActivated;
    private boolean isShielded;

    public BossStrategyImpl(ActiveActor actor) {
        super(actor);

        movePattern = new ArrayList<>();
        timeMovingInSameDirection = 0;
        indexOfCurrentMove = 0;
        timeWithShieldActivated = 0;
        lastShieldActivationAttempt = -99999;

        isShielded = false;

        initializeMovePattern();
    }

    private void initializeMovePattern() {
        for (int i = 0; i < MOVE_FREQUENCY_PER_CYCLE; i++) {
            movePattern.add(1.0);
            movePattern.add(-1.0);
            movePattern.add(0.0);
        }
        Collections.shuffle(movePattern);
    }

    @Override
    public void updateStrategyState(double deltaTime, double currentTime) {
        super.updateStrategyState(deltaTime, currentTime);

        timeMovingInSameDirection += deltaTime;

        if (timeMovingInSameDirection >= MAX_TIME_WITH_SAME_MOVE) {
            Collections.shuffle(movePattern);
            timeMovingInSameDirection = 0;
            indexOfCurrentMove++;
        }

        if (indexOfCurrentMove == movePattern.size()) {
            indexOfCurrentMove = 0;
        }

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
    public Vector getNextMovement() {
        return new Vector(0, movePattern.get(indexOfCurrentMove));
    }

    @Override
    public boolean isShielded() {
        return isShielded;
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
