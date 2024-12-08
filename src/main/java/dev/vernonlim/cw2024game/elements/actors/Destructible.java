package dev.vernonlim.cw2024game.elements.actors;

/**
 * An interface for Actors that can take damage and be destroyed.
 */
public interface Destructible {
    /**
     * Cause the Actor to take damage according to the given param.
     *
     * @param damage the damage to be taken
     */
    void takeDamage(int damage);

    /**
     * Destroy the Actor.
     */
    void destroy();
}
