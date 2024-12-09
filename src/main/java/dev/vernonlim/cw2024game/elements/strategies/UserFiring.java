package dev.vernonlim.cw2024game.elements.strategies;

import dev.vernonlim.cw2024game.elements.actors.ProjectileCode;
import dev.vernonlim.cw2024game.managers.InputManager;

/**
 * An abstract class implementing shared behaviour among all User Firing strategies.
 */
public abstract class UserFiring extends PlaneFiring implements Firing {
    /**
     * The InputManager handling user input.
     */
    protected final InputManager input;

    /**
     * Indicates whether the user is holding down the Focus key.
     */
    protected boolean focus;

    /**
     * Indicates whether the user is holding down the Fire key.
     */
    protected boolean fire;

    /**
     * The base fire rate, in terms of milliseconds between shots.
     */
    protected final double baseFireRate;

    /**
     * The fire rate after Focus adjustments.
     */
    protected double fireRate;

    /**
     * The most recent projectile fired.
     */
    protected ProjectileCode lastProjectile;

    /**
     * Constructs a User Firing strategy from the given params.
     *
     * @param input the InputManager used to handle user input
     */
    public UserFiring(InputManager input) {
        this.input = input;
        this.baseFireRate = 10.0f;
        this.fireRate = baseFireRate;

        lastProjectile = ProjectileCode.USER_ROUND;
    }

    @Override
    public void updateStrategyState(double deltaTime, double currentTime) {
        super.updateStrategyState(deltaTime, currentTime);

        focus = input.isFocusPressed();
        fire = input.isFirePressed();

        handleFocus();
    }

    /**
     * Modifies the strategy fields depending on whether Focus is being held.
     */
    protected abstract void handleFocus();

    @Override
    protected boolean willAttemptFire() {
        return fire;
    }

    @Override
    protected boolean canFire() {
        return (currentTime - lastFireTime) > (1000.0f / fireRate);
    }
}
