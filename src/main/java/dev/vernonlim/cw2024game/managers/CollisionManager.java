package dev.vernonlim.cw2024game.managers;

import dev.vernonlim.cw2024game.elements.actors.ActiveActorDestructible;

import java.util.List;

/**
 * An interface defining the behaviour of a CollisionManager
 */
public interface CollisionManager {
    /**
     * Handles collisions between two lists of Actors.
     *
     * @param list1 the first list of Actors
     * @param list2 the second list of Actors
     */
    void handleCollisions(List<ActiveActorDestructible> list1, List<ActiveActorDestructible> list2);
}
