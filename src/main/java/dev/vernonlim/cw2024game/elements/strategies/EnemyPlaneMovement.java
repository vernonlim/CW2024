package dev.vernonlim.cw2024game.elements.strategies;

import dev.vernonlim.cw2024game.Vector;
import dev.vernonlim.cw2024game.elements.actors.ProjectileCode;

public class EnemyPlaneMovement extends UpdatableStrategy implements Movement {
    @Override
    public Vector getNextMovement() {
        return new Vector(-1, 0);
    }
}