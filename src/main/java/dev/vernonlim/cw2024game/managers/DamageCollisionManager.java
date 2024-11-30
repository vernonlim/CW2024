package dev.vernonlim.cw2024game.managers;

import dev.vernonlim.cw2024game.elements.actors.ActiveActorDestructible;

import java.util.List;

public class DamageCollisionManager implements CollisionManager {
    public DamageCollisionManager() {

    }

    public void handleCollisions(List<ActiveActorDestructible> list1, List<ActiveActorDestructible> list2) {
        for (ActiveActorDestructible actor : list2) {
            for (ActiveActorDestructible otherActor : list1) {
                if (actor.getCollisionBounds().intersects(otherActor.getCollisionBounds())) {
                    actor.takeDamage();
                    otherActor.takeDamage();
                }
            }
        }
    }
}
