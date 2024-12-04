package dev.vernonlim.cw2024game.elements.strategies;

import dev.vernonlim.cw2024game.elements.actors.ProjectileCode;

public interface PlaneStrategy extends ActorStrategy {
    boolean shouldFire();
    ProjectileCode getProjectileCode();
}
