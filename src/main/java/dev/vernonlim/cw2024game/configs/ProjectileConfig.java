package dev.vernonlim.cw2024game.configs;

import javafx.scene.layout.Pane;

/**
 * A data class used for streamlining Projectile creation
 */
public class ProjectileConfig extends ActiveActorDestructibleConfig {
    /**
     * The damage the Projectile deals.
     */
    private int damage;

    /**
     * Indicates if the Projectile was fired by an Ally.
     */
    private boolean isAllyProjectile;

    /**
     * Constructs a ProjectileConfig with the given params.
     *
     * @param root the root Pane on which the Projectile is based
     */
    public ProjectileConfig(Pane root) {
        super(root);

        this.damage = 1;
        this.isAllyProjectile = false;
    }

    /**
     * Gets the damage.
     *
     * @return the damage
     */
    public int getDamage() {
        return damage;
    }

    /**
     * Sets the damage.
     *
     * @param damage the damage to set
     */
    public void setDamage(int damage) {
        this.damage = damage;
    }

    /**
     * Checks if it is a user projectile.
     *
     * @return true if it is a user projectile, false otherwise
     */
    public boolean isAllyProjectile() {
        return isAllyProjectile;
    }

    /**
     * Sets if it is a user projectile.
     *
     * @param allyProjectile true if it is a user projectile, false otherwise
     */
    public void setAllyProjectile(boolean allyProjectile) {
        this.isAllyProjectile = allyProjectile;
    }
}
