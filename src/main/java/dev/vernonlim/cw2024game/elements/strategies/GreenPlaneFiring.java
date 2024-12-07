package dev.vernonlim.cw2024game.elements.strategies;

import dev.vernonlim.cw2024game.elements.actors.ProjectileCode;
import dev.vernonlim.cw2024game.managers.InputManager;

public class GreenPlaneFiring extends UserFiring implements Firing {
    public GreenPlaneFiring(InputManager input) {
        super(input);
    }

    @Override
    protected void handleFocus() {
    }

    @Override
    public ProjectileCode getProjectileCode() {
        if (focus) {
            return ProjectileCode.USER_ROUND;
        }

        return ProjectileCode.USER_ROUND_GREEN;
    }
}
