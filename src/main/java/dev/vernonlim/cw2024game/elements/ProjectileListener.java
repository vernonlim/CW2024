package dev.vernonlim.cw2024game.elements;

import dev.vernonlim.cw2024game.elements.actors.Projectile;

/**
 * An interface representing a Handler for when a projectile is created and fired.
 */
public interface ProjectileListener {
    void onFire(Projectile projectile);
}
