package dev.vernonlim.cw2024game.elements.actors;

/**
 * An interface for Actors that can take damage and be destroyed.
 * <p>
 * This class is from the original project.
 * Original Code: <a href="https://github.com/kooitt/CW2024/blob/master/src/main/java/com/example/demo/UserPlane.java">GitHub</a>
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
