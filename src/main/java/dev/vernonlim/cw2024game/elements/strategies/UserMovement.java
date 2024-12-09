package dev.vernonlim.cw2024game.elements.strategies;

import dev.vernonlim.cw2024game.Vector;
import dev.vernonlim.cw2024game.managers.InputManager;

/**
 * An abstract class implement shared behaviour for User Movement strategies.
 */
public abstract class UserMovement extends UpdatableStrategy implements Movement {
    /**
     * The InputManager handling user input.
     */
    protected final InputManager input;

    /**
     * Indicates whether the user is holding down the Down key.
     */
    protected boolean down;

    /**
     * Indicates whether the user is holding down the Up key.
     */
    protected boolean up;

    /**
     * Indicates whether the user is holding down the Left key.
     */
    protected boolean left;

    /**
     * Indicates whether the user is holding down the Right key.
     */
    protected boolean right;

    /**
     * Indicates whether the user is holding down the Focus key.
     */
    protected boolean focus;

    /**
     * The previous vertical movement multiplier.
     */
    protected int lastVerticalMult;

    /**
     * The previous horizontal movement multiplier.
     */
    protected int lastHorizontalMult;

    /**
     * The current overall movement multiplier.
     */
    protected double movementMultiplier;

    /**
     * Constructs a User Movement strategy from the given params.
     *
     * @param input the InputManager used to handle user input
     */
    public UserMovement(InputManager input) {
        this.input = input;

        this.lastVerticalMult = 0;
        this.lastHorizontalMult = 0;
        this.movementMultiplier = 1.0f;
    }

    @Override
    public void updateStrategyState(double deltaTime, double currentTime) {
        super.updateStrategyState(deltaTime, currentTime);

        down = input.isDownPressed();
        up = input.isUpPressed();
        left = input.isLeftPressed();
        right = input.isRightPressed();
        focus = input.isFocusPressed();

        handleFocus();
    }

    /**
     * Modifies the strategy fields depending on whether Focus is being held.
     */
    protected abstract void handleFocus();

    @Override
    public Vector getNextMovement() {
        double verticalMult;
        double horizontalMult;

        // null cancelling movement
        if (down && up) {
            verticalMult = -lastVerticalMult;
        } else if (up) {
            lastVerticalMult = -1;
            verticalMult = -1;
        } else if (down) {
            lastVerticalMult = 1;
            verticalMult = 1;
        } else {
            verticalMult = 0;
        }

        if (left && right) {
            horizontalMult = -lastHorizontalMult;
        } else if (left) {
            lastHorizontalMult = -1;
            horizontalMult = -1;
        } else if (right) {
            lastHorizontalMult = 1;
            horizontalMult = 1;
        } else {
            horizontalMult = 0;
        }

        if (horizontalMult == 0 && verticalMult == 0) {
            return new Vector(0, 0);
        }

        Vector movement = new Vector(horizontalMult, verticalMult);
        movement.normalize();
        movement.scaleBy(movementMultiplier);

        return movement;
    }
}
