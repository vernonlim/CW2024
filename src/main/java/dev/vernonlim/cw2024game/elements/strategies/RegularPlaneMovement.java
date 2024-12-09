package dev.vernonlim.cw2024game.elements.strategies;

import dev.vernonlim.cw2024game.managers.InputManager;

/**
 * A Regular User Plane Movement strategy
 */
public class RegularPlaneMovement extends UserMovement {
    /**
     * Constructs a Regular (User) Plane Movement strategy from the given params.
     *
     * @param input the InputManager used to handle user input
     */
    public RegularPlaneMovement(InputManager input) {
        super(input);
    }

    @Override
    protected void handleFocus() {
        if (focus) {
            movementMultiplier = 0.6f;
        } else {
            movementMultiplier = 1.0f;
        }
    }
}
