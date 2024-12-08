package dev.vernonlim.cw2024game.factories;

import dev.vernonlim.cw2024game.assets.AssetLoader;
import dev.vernonlim.cw2024game.Vector;
import dev.vernonlim.cw2024game.elements.actors.Projectile;
import dev.vernonlim.cw2024game.elements.actors.ProjectileCode;
import dev.vernonlim.cw2024game.elements.configs.ProjectileConfig;
import dev.vernonlim.cw2024game.elements.strategies.LinearMovement;
import dev.vernonlim.cw2024game.factories.interfaces.ProjectileFactory;
import javafx.scene.layout.Pane;

public class ProjectileFactoryImpl extends Factory implements ProjectileFactory {
    public ProjectileFactoryImpl(Pane root, AssetLoader loader) {
        super(root, loader);
    }

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

    protected Projectile createGenericProjectile(String imageName, double fitHeight, double speed, int damage, Vector velocity, boolean travellingRight, double x, double y) {
        ProjectileConfig config = new ProjectileConfig(root);
        config.setSpeed(speed);
        config.setDamage(damage);
        config.setPosition(x, y);
        config.setImage(loadImage(imageName));
        config.setFitHeight(fitHeight);
        config.setUserProjectile(travellingRight);
        config.setAlwaysInBounds(false);
        config.setMovement(new LinearMovement(velocity));

        return new Projectile(config);
    }

    private Projectile createUserProjectile(String imageName, int damage, Vector velocity, double x, double y) {
        return createGenericProjectile(
                imageName, 12, 100, damage, velocity, true, x, y);
    }

    private Projectile createEnemyProjectile(String imageName, int damage, Vector velocity, double x, double y) {
        return createGenericProjectile(
                imageName, 34, 10, damage, velocity, false, x, y);
    }

    private Projectile createBossProjectile(int damage, double x, double y) {
        return createGenericProjectile(
                "fireball", 75, 15, damage, new Vector(-1, 0), false, x, y);
    }
}
