package dev.vernonlim.cw2024game.elements.strategies;

import dev.vernonlim.cw2024game.Vector;
import dev.vernonlim.cw2024game.elements.actors.ProjectileCode;
import dev.vernonlim.cw2024game.managers.InputManager;

public abstract class UserFiring extends PlaneFiring implements Firing {
    protected InputManager input;

    protected boolean focus;
    protected boolean fire;

    protected double baseFireRate;
    protected double fireRate;
    
    protected ProjectileCode lastProjectile;

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
