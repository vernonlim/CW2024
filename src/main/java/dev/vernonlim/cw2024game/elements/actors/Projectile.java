package dev.vernonlim.cw2024game.elements.actors;

import dev.vernonlim.cw2024game.Main;
import dev.vernonlim.cw2024game.Vector;
import dev.vernonlim.cw2024game.configs.ProjectileConfig;

/**
 * An ActiveActorDestructible that deals a certain amount of damage on collision and
 * can be fired by either an enemy or an ally
 * <p>
 * This class is modified.
 * Original Code: <a href="https://github.com/kooitt/CW2024/blob/master/src/main/java/com/example/demo/Projectile.java">GitHub</a>
 */
public class Projectile extends ActiveActorDestructible {
    /**
     * The amount of damage the Projectile does on collision.
     */
    protected final int damage;

    /**
     * Indicates whether the Projectile was fired by an ally.
     */
    protected final boolean isAllyProjectile;

    /**
     * Constructs a Projectile from the given params.
     *
     * @param config the configuration object containing the necessary data to construct the Projectile
     */
    public Projectile(ProjectileConfig config) {
        super(config);

        this.damage = config.getDamage();
        this.isAllyProjectile = config.isAllyProjectile();

        if (isAllyProjectile) {
            moveHorizontally(getHalfWidth());
        } else {
            moveHorizontally(-getHalfWidth());
        }
    }

    @Override
    public void takeDamage(int damage) {
        this.destroy();
    }

    @Override
    public void updateActor(double deltaTime, double currentTime) {
        super.updateActor(deltaTime, currentTime);

        Vector position = getPosition();

        if (position.isOutside(0, Main.SCREEN_WIDTH, 0, Main.SCREEN_HEIGHT)) {
            destroy();
        }
    }

    /**
     * Gets the damage dealt by the Projectile.
     *
     * @return the damage dealt by the Projectile
     */
    public int getDamage() {
        return damage;
    }

    /**
     * Indicates whether the Projectile was fired by an Ally.
     *
     * @return true if the Projectile was fired by an Ally, false otherwise
     */
    public boolean isAllyProjectile() {
        return isAllyProjectile;
    }
}
