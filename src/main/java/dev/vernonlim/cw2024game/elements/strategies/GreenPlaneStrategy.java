package dev.vernonlim.cw2024game.elements.strategies;

import dev.vernonlim.cw2024game.elements.ProjectileCode;
import dev.vernonlim.cw2024game.managers.InputManager;

public class GreenPlaneStrategy extends UserPlaneStrategy implements PlaneStrategy {
    public GreenPlaneStrategy(InputManager input) {
        super(input);
    }

    @Override
    protected void handleFocus() {
        if (focus) {
            movementMultiplier = 0.9f;
        } else {
            movementMultiplier = 1.0f;
        }
    }

    @Override
    public ProjectileCode getProjectileCode() {
        if (focus) {
            return ProjectileCode.USER_ROUND;
        }

        return ProjectileCode.USER_ROUND_GREEN;
    }
}
