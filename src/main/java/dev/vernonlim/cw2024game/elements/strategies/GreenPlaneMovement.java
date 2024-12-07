package dev.vernonlim.cw2024game.elements.strategies;

import dev.vernonlim.cw2024game.managers.InputManager;

public class GreenPlaneMovement extends UserMovement {
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
