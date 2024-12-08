package dev.vernonlim.cw2024game.elements.strategies;

import dev.vernonlim.cw2024game.Main;
import dev.vernonlim.cw2024game.elements.actors.FighterPlane;
import dev.vernonlim.cw2024game.elements.actors.ProjectileCode;

public class BlueEnemyFiring extends PlaneFiring implements Firing {
    protected FighterPlane plane;

    public BlueEnemyFiring(FighterPlane plane) {
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
    public ProjectileCode getProjectileCode() {
        if (plane.getY() > Main.SCREEN_HEIGHT / 2.0f) {
            return ProjectileCode.ENEMY_ROUND_DOWN;
        } else {
            return ProjectileCode.ENEMY_ROUND_UP;
        }
    }
}
