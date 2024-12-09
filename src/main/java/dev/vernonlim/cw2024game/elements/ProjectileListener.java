package dev.vernonlim.cw2024game.elements;

import dev.vernonlim.cw2024game.elements.actors.Projectile;

/**
 * An interface representing a Handler for when a projectile is created and fired.
 */
public interface ProjectileListener {
    /**
     * What to run when a Projectile is to be fired.
     *
     * @param projectile the Projectile to be fired
     */
    void onFire(Projectile projectile);
}
