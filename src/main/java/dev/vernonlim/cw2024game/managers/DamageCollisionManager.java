package dev.vernonlim.cw2024game.managers;

import dev.vernonlim.cw2024game.elements.actors.ActiveActorDestructible;
import dev.vernonlim.cw2024game.elements.actors.Projectile;

import java.util.List;

public class DamageCollisionManager implements CollisionManager {
    public DamageCollisionManager() {

    }

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
