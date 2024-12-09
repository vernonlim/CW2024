package dev.vernonlim.cw2024game.managers;

import dev.vernonlim.cw2024game.elements.actors.ActiveActorDestructible;
import dev.vernonlim.cw2024game.elements.actors.Projectile;

import java.util.List;

/**
 * Handles collisions between two lists of Actors by damaging both of them by 1.
 * If the first is a projectile, the second actor takes the projectile's damage instead of 1.
 */
public class DamageCollisionManager implements CollisionManager {
    /**
     * Constructs a DamageCollisionManager.
     */
    public DamageCollisionManager() {
    }

    @Override
    public void handleCollisions(List<ActiveActorDestructible> list1, List<ActiveActorDestructible> list2) {
        for (ActiveActorDestructible actor : list1) {
            for (ActiveActorDestructible otherActor : list2) {
                if (actor.getCollisionBounds().intersects(otherActor.getCollisionBounds())) {
                    actor.takeDamage(1);

                    if (actor instanceof Projectile) {
                        otherActor.takeDamage(((Projectile) actor).getDamage());
                    } else {
                        otherActor.takeDamage(1);
                    }
                }
            }
        }
    }
}
