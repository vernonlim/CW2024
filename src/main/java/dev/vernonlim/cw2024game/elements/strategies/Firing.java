package dev.vernonlim.cw2024game.elements.strategies;

import dev.vernonlim.cw2024game.elements.actors.ProjectileCode;

public interface Firing extends Updatable {
    boolean shouldFire();

    ProjectileCode getProjectileCode();
}
