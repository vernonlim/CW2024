package dev.vernonlim.cw2024game.elements.strategies;

import dev.vernonlim.cw2024game.elements.actors.ProjectileCode;
import dev.vernonlim.cw2024game.managers.InputManager;

/**
 * A Green User Plane Firing strategy.
 */
public class GreenPlaneFiring extends UserFiring implements Firing {
    /**
     * Constructs a Green (user) Plane Firing strategy from the given params.
     *
     * @param input the InputManager used to handle user input
     */
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
