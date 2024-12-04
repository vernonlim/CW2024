package dev.vernonlim.cw2024game.factories.interfaces;

import dev.vernonlim.cw2024game.elements.actors.*;

public interface ActorFactory {
    UserPlane createUserPlane(UserPlaneCode code);
    FighterPlane createEnemy(EnemyCode enemyCode, double initialXPos, double initialYPos);
}
