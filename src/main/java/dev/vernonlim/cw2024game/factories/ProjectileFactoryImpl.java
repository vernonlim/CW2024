package dev.vernonlim.cw2024game.factories;

import dev.vernonlim.cw2024game.Vector;
import dev.vernonlim.cw2024game.assets.AssetLoader;
import dev.vernonlim.cw2024game.configs.ProjectileConfig;
import dev.vernonlim.cw2024game.elements.actors.Projectile;
import dev.vernonlim.cw2024game.elements.actors.ProjectileCode;
import dev.vernonlim.cw2024game.elements.strategies.LinearMovement;
import dev.vernonlim.cw2024game.factories.interfaces.ProjectileFactory;
import javafx.scene.layout.Pane;

/**
 * The default implementation of ProjectileFactory for CW2024Game.
 * <p>
 * Some of the logic within this class comes from the original project. The sources come from many different places.
 * Original Project: <a href="https://github.com/kooitt/CW2024">GitHub</a>
 */
public class ProjectileFactoryImpl extends Factory implements ProjectileFactory {
    /**
     * Constructs a ProjectileFactory from the given params.
     *
     * @param root   the root Pane projectiles will be based on
     * @param loader the AssetLoader handling loading of assets
     */
    public ProjectileFactoryImpl(Pane root, AssetLoader loader) {
        super(root, loader);
    }

    @Override
    public Projectile createProjectile(ProjectileCode code, double x, double y) {
        return switch (code) {
            case ProjectileCode.USER -> createUserProjectile(
                    "userfire", 10, new Vector(1, 0), x, y);
            case ProjectileCode.USER_ROUND -> createUserProjectile(
                    "circlebullet", 5, new Vector(1, 0), x, y);
            case ProjectileCode.USER_ROUND_GREEN -> createUserProjectile(
                    "circlebulletgreen", 10, new Vector(1, 0), x, y);
            case ProjectileCode.USER_ROUND_UP -> createUserProjectile(
                    "circlebullet", 5, new Vector(1, 0.3), x, y);
            case ProjectileCode.USER_ROUND_DOWN -> createUserProjectile(
                    "circlebullet", 5, new Vector(1, -0.3), x, y);
            case ProjectileCode.ENEMY -> createEnemyProjectile(
                    "enemyFire", 1, new Vector(-1, 0), x, y);
            case ProjectileCode.ENEMY_ROUND_DOWN -> createEnemyProjectile(
                    "circlebulletblue", 1, new Vector(-1, -0.3), x, y);
            case ProjectileCode.ENEMY_ROUND_UP -> createEnemyProjectile(
                    "circlebulletblue", 1, new Vector(-1, 0.3), x, y);
            case ProjectileCode.BOSS -> createBossProjectile(1, x, y);
        };
    }

    /**
     * Creates a generic projectile from the given params.
     *
     * @param imageName       the name of the image as defined in the AssetLoader
     * @param fitHeight       the height of the projectile
     * @param speed           the speed of the projectile
     * @param damage          the damage of the projectile
     * @param velocity        the velocity of the projectile, a normalized vector pointing in a direction
     * @param travellingRight whether the projectile is travelling right
     * @param x               the starting x position
     * @param y               the starting y position
     * @return the created projectile
     */
    protected Projectile createGenericProjectile(String imageName, double fitHeight, double speed, int damage, Vector velocity, boolean travellingRight, double x, double y) {
        ProjectileConfig config = new ProjectileConfig(root);
        config.setSpeed(speed);
        config.setDamage(damage);
        config.setPosition(x, y);
        config.setImage(loadImage(imageName));
        config.setFitHeight(fitHeight);
        config.setAllyProjectile(travellingRight);
        config.setAlwaysInBounds(false);
        config.setMovement(new LinearMovement(velocity));

        return new Projectile(config);
    }

    /**
     * Creates a User projectile from the given params.
     *
     * @param imageName the name of the image as defined in the AssetLoader
     * @param damage    the damage of the projectile
     * @param velocity  the velocity of the projectile, a normalized vector pointing in a direction
     * @param x         the starting x position
     * @param y         the starting y position
     * @return the created projectile
     */
    private Projectile createUserProjectile(String imageName, int damage, Vector velocity, double x, double y) {
        return createGenericProjectile(
                imageName, 12, 100, damage, velocity, true, x, y);
    }

    /**
     * Creates an Enemy projectile from the given params.
     *
     * @param imageName the name of the image as defined in the AssetLoader
     * @param damage    the damage of the projectile
     * @param velocity  the velocity of the projectile, a normalized vector pointing in a direction
     * @param x         the starting x position
     * @param y         the starting y position
     * @return the created projectile
     */
    private Projectile createEnemyProjectile(String imageName, @SuppressWarnings("SameParameterValue") int damage, Vector velocity, double x, double y) {
        return createGenericProjectile(
                imageName, 34, 10, damage, velocity, false, x, y);
    }

    /**
     * Creates a Boss projectile from the given params.
     *
     * @param damage the damage of the projectile
     * @param x      the starting x position
     * @param y      the starting y position
     * @return the created projectile
     */
    private Projectile createBossProjectile(@SuppressWarnings("SameParameterValue") int damage, double x, double y) {
        return createGenericProjectile(
                "fireball", 75, 15, damage, new Vector(-1, 0), false, x, y);
    }
}
