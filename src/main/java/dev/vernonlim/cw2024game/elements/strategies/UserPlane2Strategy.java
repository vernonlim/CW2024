package dev.vernonlim.cw2024game.elements.strategies;

import dev.vernonlim.cw2024game.elements.ProjectileCode;
import dev.vernonlim.cw2024game.elements.actors.ActiveActor;
import dev.vernonlim.cw2024game.managers.InputManager;

public class UserPlane2Strategy extends UserPlaneStrategy implements PlaneStrategy {
    public UserPlane2Strategy(ActiveActor actor, InputManager input) {
        super(actor, input);
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
