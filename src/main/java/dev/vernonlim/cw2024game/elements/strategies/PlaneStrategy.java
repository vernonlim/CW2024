package dev.vernonlim.cw2024game.elements.strategies;

import dev.vernonlim.cw2024game.elements.ProjectileCode;
import dev.vernonlim.cw2024game.elements.Vector;

public interface PlaneStrategy extends ActorStrategy {
    boolean shouldFire();
    ProjectileCode getProjectileCode();
}
