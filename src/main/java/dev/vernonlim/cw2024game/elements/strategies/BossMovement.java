package dev.vernonlim.cw2024game.elements.strategies;

import dev.vernonlim.cw2024game.Vector;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The Movement strategy for Boss enemies.
 * <p>
 * This class contains sections from the original project.
 * Original Code: <a href="https://github.com/kooitt/CW2024/blob/master/src/main/java/com/example/demo/Boss.java">GitHub</a>
 */
public class BossMovement extends UpdatableStrategy implements Movement {
    /**
     * The number of moves per cycle.
     */
    private static final int MOVE_FREQUENCY_PER_CYCLE = 5;

    /**
     * The maximum amount of time the Boss spends moving in the same direction.
     */
    private static final double MAX_TIME_WITH_SAME_MOVE = 10 * 50.0f;

    /**
     * The list of movement directions.
     */
    private final List<Double> movePattern;

    /**
     * The time spent moving in the same direction.
     */
    private double timeMovingInSameDirection;

    /**
     * The index of the current move within the movePattern list.
     */
    private int indexOfCurrentMove;

    /**
     * Constructs a Boss Movement strategy.
     */
    public BossMovement() {
        movePattern = new ArrayList<>();
        timeMovingInSameDirection = 0;
        indexOfCurrentMove = 0;

        initializeMovePattern();
    }

    /**
     * Initializes the movement pattern list with random movement directions.
     */
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
