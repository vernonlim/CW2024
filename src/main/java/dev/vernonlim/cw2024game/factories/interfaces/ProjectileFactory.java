package dev.vernonlim.cw2024game.factories.interfaces;

import dev.vernonlim.cw2024game.elements.actors.Projectile;
import dev.vernonlim.cw2024game.elements.actors.ProjectileCode;

/**
 * An interface defining a Factory for creating Projectiles.
 */
public interface ProjectileFactory {
    /**
     * Creates a projectile from the given ProjectileCode and x, y positions.
     *
     * @param code        the ProjectileCode
     * @param initialXPos the x position
     * @param initialYPos the y position
     * @return the created Projectile
     */
    Projectile createProjectile(ProjectileCode code, double initialXPos, double initialYPos);
}
