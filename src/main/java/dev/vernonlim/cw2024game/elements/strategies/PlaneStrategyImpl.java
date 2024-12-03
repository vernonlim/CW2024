package dev.vernonlim.cw2024game.elements.strategies;

import dev.vernonlim.cw2024game.elements.actors.ActiveActor;

public abstract class PlaneStrategyImpl extends StrategyParent implements PlaneStrategy {
    protected double lastFireAttempt;
    protected double lastFireTime;

    public PlaneStrategyImpl() {
        this.lastFireAttempt = 0;
        this.lastFireTime = 0;
    }

    @Override
    public boolean shouldFire() {
        if (willAttemptFire()) {
            return tryFire();
        }

        return false;
    }

    protected abstract boolean willAttemptFire();

    protected boolean tryFire() {
        lastFireAttempt = currentTime;

        if (canFire()) {
            lastFireTime = currentTime;

            return true;
        }

        return false;
    }

    protected abstract boolean canFire();
}
