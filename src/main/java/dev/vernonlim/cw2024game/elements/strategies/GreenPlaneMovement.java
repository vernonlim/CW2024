package dev.vernonlim.cw2024game.elements.strategies;

import dev.vernonlim.cw2024game.managers.InputManager;

/**
 * A Green User Plane Movement strategy
 */
public class GreenPlaneMovement extends UserMovement {
    /**
     * Constructs a Green (User) Plane Movement strategy from the given params.
     *
     * @param input the InputManager used to handle user input
     */
    public GreenPlaneMovement(InputManager input) {
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
}
