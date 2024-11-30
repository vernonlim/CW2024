package dev.vernonlim.cw2024game.managers;

import dev.vernonlim.cw2024game.elements.actors.ActiveActorDestructible;

import java.util.List;

public interface CollisionManager {
    void handleCollisions(List<ActiveActorDestructible> list1, List<ActiveActorDestructible> list2);
}
