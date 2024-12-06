package dev.vernonlim.cw2024game.elements.strategies;

import dev.vernonlim.cw2024game.Main;
import dev.vernonlim.cw2024game.Vector;
import dev.vernonlim.cw2024game.elements.actors.FighterPlane;
import dev.vernonlim.cw2024game.elements.actors.ProjectileCode;

public class BlueStrategy extends PlaneStrategyImpl implements PlaneStrategy {
    protected FighterPlane plane;

    public BlueStrategy(FighterPlane plane) {
        this.plane = plane;
    }

    @Override
    protected boolean willAttemptFire() {
        return (currentTime - lastFireAttempt) > 500.0f;
    }

    @Override
    protected boolean canFire() {
        return true;
    }

    @Override
    public Vector getNextMovement() {
        return new Vector(-1, 0);
    }

    @Override
    public ProjectileCode getProjectileCode() {
        if (plane.getY() > Main.SCREEN_HEIGHT / 2.0f) {
            return ProjectileCode.ENEMY_ROUND_DOWN;
        } else {
            return ProjectileCode.ENEMY_ROUND_UP;
        }
    }
}
