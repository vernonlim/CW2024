package dev.vernonlim.cw2024game.factories.interfaces;

import dev.vernonlim.cw2024game.elements.actors.*;

public interface ActorFactory {
    UserPlane createUserPlane(int initialHealth);
    FighterPlane createEnemyPlane(double initialXPos, double initialYPos);
    FighterPlane createBoss();
}