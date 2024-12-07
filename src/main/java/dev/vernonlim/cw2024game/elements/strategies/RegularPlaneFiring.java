package dev.vernonlim.cw2024game.elements.strategies;

import dev.vernonlim.cw2024game.elements.actors.ProjectileCode;
import dev.vernonlim.cw2024game.managers.InputManager;

public class RegularPlaneFiring extends UserFiring implements Firing {
    public RegularPlaneFiring(InputManager input) {
        super(input);
    }

    @Override
    protected void handleFocus() {
        if (focus) {
            fireRate = baseFireRate * 2.0f;
        } else {
            fireRate = baseFireRate;
        }
    }

    @Override
    public ProjectileCode getProjectileCode() {
        if (lastProjectile == ProjectileCode.USER_ROUND) {
            lastProjectile = ProjectileCode.USER;
            return ProjectileCode.USER;
        } else {
            lastProjectile = ProjectileCode.USER_ROUND;
            return ProjectileCode.USER_ROUND;
        }
    }
}
