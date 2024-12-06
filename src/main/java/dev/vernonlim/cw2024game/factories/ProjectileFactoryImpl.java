package dev.vernonlim.cw2024game.factories;

import dev.vernonlim.cw2024game.assets.AssetLoader;
import dev.vernonlim.cw2024game.elements.Vector;
import dev.vernonlim.cw2024game.elements.actors.Projectile;
import dev.vernonlim.cw2024game.elements.actors.ProjectileCode;
import dev.vernonlim.cw2024game.elements.configs.ProjectileConfig;
import dev.vernonlim.cw2024game.elements.strategies.LinearProjectileStrategy;
import dev.vernonlim.cw2024game.factories.interfaces.ProjectileFactory;
import javafx.scene.layout.Pane;

public class ProjectileFactoryImpl extends FactoryParent implements ProjectileFactory {
    public ProjectileFactoryImpl(Pane root, AssetLoader loader) {
        super(root, loader);
    }

    public Projectile createProjectile(ProjectileCode code, double x, double y) {
        return switch (code) {
            case ProjectileCode.USER ->
                    createUserProjectile("userfire", x, y, 10, new Vector(1, 0));
            case ProjectileCode.USER_ROUND ->
                    createUserProjectile("circlebullet", x, y, 5, new Vector(1, 0));
            case ProjectileCode.USER_ROUND_GREEN ->
                    createUserProjectile("circlebulletgreen", x, y, 10, new Vector(1, 0));
            case ProjectileCode.USER_ROUND_UP ->
                    createUserProjectile("circlebullet", x, y, 5, new Vector(1, 0.3));
            case ProjectileCode.USER_ROUND_DOWN ->
                    createUserProjectile("circlebullet", x, y, 5, new Vector(1, -0.3));
            case ProjectileCode.ENEMY ->
                    createEnemyProjectile("enemyFire", x, y, 1, new Vector(-1, 0));
            case ProjectileCode.ENEMY_ROUND_DOWN ->
                    createEnemyProjectile("circlebulletblue", x, y, 1, new Vector(-1, -0.3));
            case ProjectileCode.ENEMY_ROUND_UP ->
                    createEnemyProjectile("circlebulletblue", x, y, 1, new Vector(-1, 0.3));
            case ProjectileCode.BOSS ->
                    createBossProjectile(x, y, 1);
        };
    }

    protected Projectile createGenericProjectile(String imageName, double fitHeight, double speed, double x, double y, int damage, Vector velocity, boolean travellingRight) {
        ProjectileConfig config = new ProjectileConfig(root);
        config.setSpeed(speed);
        config.setDamage(damage);
        config.setPosition(x, y);
        config.setImage(loadImage(imageName));
        config.setFitHeight(fitHeight);
        config.setUserProjectile(travellingRight);
        config.setAlwaysInBounds(false);
        config.setActorStrategy(new LinearProjectileStrategy(velocity));

        return new Projectile(config);
    }

    protected Projectile createUserProjectile(String imageName, double initialXPos, double initialYPos, int damage, Vector velocity) {
        return createGenericProjectile(imageName, 12, 100, initialXPos, initialYPos, damage, velocity, true);
    }

    protected Projectile createEnemyProjectile(String imageName, double initialXPos, double initialYPos, int damage, Vector velocity) {
        return createGenericProjectile(imageName, 34, 10, initialXPos, initialYPos, damage, velocity, false);
    }

    protected Projectile createBossProjectile(double initialXPos, double initialYPos, int damage) {
        return createGenericProjectile("fireball", 75, 15, initialXPos, initialYPos, damage, new Vector(-1, 0), false);
    }
}
