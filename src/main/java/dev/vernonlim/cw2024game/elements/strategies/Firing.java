package dev.vernonlim.cw2024game.elements.strategies;

import dev.vernonlim.cw2024game.elements.actors.ProjectileCode;

/**
 * An interface for determining when and what an Actor fires.
 */
public interface Firing extends Updatable {
    /**
     * Indicates if the Actor should fire.
     *
     * @return true when the Actor should fire, false otherwise
     */
    boolean shouldFire();

    /**
     * If the Actor were to fire, indicates what the Actor should fire.
     *
     * @return the ProjectileCode the Actor should fire
     */
    ProjectileCode getProjectileCode();
}
