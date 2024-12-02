package dev.vernonlim.cw2024game.factories.interfaces;

import dev.vernonlim.cw2024game.elements.actors.Projectile;

public interface ProjectileFactory {
    Projectile createUserProjectile(double initialXPos, double initialYPos);
    Projectile createEnemyProjectile(double initialXPos, double initialYPos);
    Projectile createBossProjectile(double initialXPos, double initialYPos);
}
