package dev.vernonlim.cw2024game.elements.strategies;

/**
 * An abstract class implementing shared behaviour for the firing strategies of Planes.
 */
public abstract class PlaneFiring extends UpdatableStrategy implements Firing {
    /**
     * The last time a fire was attempted.
     */
    protected double lastFireAttempt;
    /**
     * The last time a projectile was actually fired.
     */
    protected double lastFireTime;

    /**
     * Constructs a PlaneFiring Strategy.
     */
    public PlaneFiring() {
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

    /**
     * Indicates whether the Plane will attempt to fire.
     *
     * @return true if the Plane will attempt to fire, false otherwise
     */
    protected abstract boolean willAttemptFire();

    /**
     * If the Plane will attempt to fire, attempts to fire a projectile.
     *
     * @return true if the Plane successfully fires, false otherwise
     */
    protected boolean tryFire() {
        lastFireAttempt = currentTime;

        if (canFire()) {
            lastFireTime = currentTime;

            return true;
        }

        return false;
    }

    /**
     * If the Plane can fire during this attempt.
     *
     * @return true if the Plane can fire, false otherwise
     */
    protected abstract boolean canFire();
}
