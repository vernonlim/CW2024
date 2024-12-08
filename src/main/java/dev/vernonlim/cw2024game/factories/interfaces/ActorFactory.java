package dev.vernonlim.cw2024game.factories.interfaces;

import dev.vernonlim.cw2024game.elements.actors.*;

/**
 * An interface defining a Factory for creating Actors.
 */
public interface ActorFactory {
    /**
     * Creates a User Plane from the given UserPlaneCode.
     *
     * @param code the UserPlaneCode determining the type of user plane
     * @return the UserPlane
     */
    UserPlane createUserPlane(UserPlaneCode code);

    /**
     * Creates an Enemy from the given EnemyCode and its initial position.
     *
     * @param enemyCode the EnemyCode determining the type of enemy
     * @param initialXPos the initial x position
     * @param initialYPos the initial y position
     * @return the EnemyPlane
     */
    FighterPlane createEnemy(EnemyCode enemyCode, double initialXPos, double initialYPos);
}
