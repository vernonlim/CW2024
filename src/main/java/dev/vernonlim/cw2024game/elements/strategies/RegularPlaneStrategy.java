package dev.vernonlim.cw2024game.elements.strategies;

import dev.vernonlim.cw2024game.elements.ProjectileCode;
import dev.vernonlim.cw2024game.elements.Vector;
import dev.vernonlim.cw2024game.managers.InputManager;

public class RegularPlaneStrategy extends UserPlaneStrategy implements PlaneStrategy {
    public RegularPlaneStrategy(InputManager input) {
        super(input);
    }

    @Override
    protected void handleFocus() {
        if (focus) {
            movementMultiplier = 0.6f;
            fireRate = baseFireRate * 2.0f;
        } else {
            movementMultiplier = 1.0f;
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
