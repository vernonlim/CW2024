package dev.vernonlim.cw2024game.elements.strategies;

import dev.vernonlim.cw2024game.Vector;
import dev.vernonlim.cw2024game.elements.actors.ProjectileCode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BossMovement extends UpdatableStrategy implements Movement {
    private static final int MOVE_FREQUENCY_PER_CYCLE = 5;
    private static final double MAX_TIME_WITH_SAME_MOVE = 10 * 50.0f;
    private final List<Double> movePattern;
    private double timeMovingInSameDirection;
    private int indexOfCurrentMove;

    public BossMovement() {
        movePattern = new ArrayList<>();
        timeMovingInSameDirection = 0;
        indexOfCurrentMove = 0;

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
    }

    @Override
    public Vector getNextMovement() {
        return new Vector(0, movePattern.get(indexOfCurrentMove));
    }
}
