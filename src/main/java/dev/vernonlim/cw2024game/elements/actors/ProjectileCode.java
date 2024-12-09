package dev.vernonlim.cw2024game.elements.actors;

/**
 * An enum representing the types of projectiles present in the game.
 */
public enum ProjectileCode {
    /**
     * The projectile fired by the User Plane.
     */
    USER,

    /**
     * A round projectile suited for moving in diagonal directions.
     */
    USER_ROUND,

    /**
     * A round projectile suited for moving in diagonal directions, green in colour.
     */
    USER_ROUND_GREEN,

    /**
     * A round projectile suited for moving in diagonal directions, moving diagonally up.
     */
    USER_ROUND_UP,

    /**
     * A round projectile suited for moving in diagonal directions, moving diagonally down.
     */
    USER_ROUND_DOWN,

    /**
     * The projectile fired by Enemy Planes.
     */
    ENEMY,

    /**
     * A round projectile fired by Enemy Planes, moving diagonally down.
     */
    ENEMY_ROUND_DOWN,

    /**
     * A round projectile fired by Enemy Planes, moving diagonally up.
     */
    ENEMY_ROUND_UP,

    /**
     * The projectile fired by Bosses.
     */
    BOSS,
}
