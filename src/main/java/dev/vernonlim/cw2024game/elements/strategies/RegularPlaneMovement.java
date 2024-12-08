package dev.vernonlim.cw2024game.elements.strategies;

import dev.vernonlim.cw2024game.managers.InputManager;

public class RegularPlaneMovement extends UserMovement {
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