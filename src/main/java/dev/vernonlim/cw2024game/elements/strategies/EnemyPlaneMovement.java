package dev.vernonlim.cw2024game.elements.strategies;

import dev.vernonlim.cw2024game.Vector;

/**
 * An Enemy Plane Movement strategy.
 * <p>
 * This class contains sections from the original project.
 * Original Code: <a href="https://github.com/kooitt/CW2024/blob/master/src/main/java/com/example/demo/EnemyPlane.java">GitHub</a>
 */
public class EnemyPlaneMovement extends UpdatableStrategy implements Movement {
    /**
     * Constructs an Enemy Plane Movement strategy.
     */
    public EnemyPlaneMovement() {
    }

    @Override
    public Vector getNextMovement() {
        return new Vector(-1, 0);
    }
}
