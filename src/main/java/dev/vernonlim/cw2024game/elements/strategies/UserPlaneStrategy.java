package dev.vernonlim.cw2024game.elements.strategies;

import dev.vernonlim.cw2024game.elements.actors.ProjectileCode;
import dev.vernonlim.cw2024game.elements.Vector;
import dev.vernonlim.cw2024game.managers.InputManager;

public abstract class UserPlaneStrategy extends PlaneStrategyImpl implements PlaneStrategy {
    protected InputManager input;

    protected boolean down;
    protected boolean up;
    protected boolean left;
    protected boolean right;
    protected boolean focus;
    protected boolean fire;

    protected double baseFireRate;
    protected double fireRate;
    protected int lastVerticalMult;
    protected int lastHorizontalMult;

    protected ProjectileCode lastProjectile;

    protected double movementMultiplier;

    public UserPlaneStrategy(InputManager input) {
        this.input = input;
        this.baseFireRate = 10.0f;
        this.fireRate = baseFireRate;

        this.lastVerticalMult = 0;
        this.lastHorizontalMult = 0;
        this.movementMultiplier = 1.0f;

        lastProjectile = ProjectileCode.USER_ROUND;
    }

    @Override
    public void updateStrategyState(double deltaTime, double currentTime) {
        super.updateStrategyState(deltaTime, currentTime);

        down = input.isDownPressed();
        up = input.isUpPressed();
        left = input.isLeftPressed();
        right = input.isRightPressed();
        focus = input.isFocusPressed();
        fire = input.isFirePressed();

        handleFocus();
    }

    protected abstract void handleFocus();

    @Override
    public Vector getNextMovement() {
        double verticalMult = 0;
        double horizontalMult = 0;

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

    @Override
    protected boolean willAttemptFire() {
        return fire;
    }

    @Override
    protected boolean canFire() {
        return (currentTime - lastFireTime) > (1000.0f / fireRate);
    }
}
