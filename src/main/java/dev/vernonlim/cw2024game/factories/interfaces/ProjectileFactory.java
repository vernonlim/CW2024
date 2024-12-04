package dev.vernonlim.cw2024game.factories.interfaces;

import dev.vernonlim.cw2024game.elements.actors.ProjectileCode;
import dev.vernonlim.cw2024game.elements.actors.Projectile;

public interface ProjectileFactory {
    Projectile createProjectile(ProjectileCode code, double initialXPos, double initialYPos);
}
