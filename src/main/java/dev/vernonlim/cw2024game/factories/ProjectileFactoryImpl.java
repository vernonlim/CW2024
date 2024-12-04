package dev.vernonlim.cw2024game.factories;

import dev.vernonlim.cw2024game.assets.AssetLoader;
import dev.vernonlim.cw2024game.elements.actors.ProjectileCode;
import dev.vernonlim.cw2024game.elements.Vector;
import dev.vernonlim.cw2024game.elements.actors.Projectile;
import dev.vernonlim.cw2024game.elements.strategies.ActorStrategy;
import dev.vernonlim.cw2024game.elements.strategies.LinearProjectileStrategy;
import dev.vernonlim.cw2024game.factories.interfaces.ProjectileFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class ProjectileFactoryImpl extends FactoryParent implements ProjectileFactory {
    public ProjectileFactoryImpl(Pane root, AssetLoader loader) {
        super(root, loader);
    }

    public Projectile createProjectile(ProjectileCode code, double initialXPos, double initialYPos) {
        return switch (code) {
            case ProjectileCode.USER -> createUserProjectile("userfire", initialXPos, initialYPos, 10, new Vector(1, 0));
            case ProjectileCode.USER_ROUND -> createUserProjectile("circlebullet", initialXPos, initialYPos, 5, new Vector(1, 0));
            case ProjectileCode.USER_ROUND_GREEN -> createUserProjectile("circlebulletgreen", initialXPos, initialYPos, 10, new Vector(1, 0));
            case ProjectileCode.USER_ROUND_UP -> createUserProjectile("circlebullet", initialXPos, initialYPos, 5, new Vector(1, 0.3));
            case ProjectileCode.USER_ROUND_DOWN -> createUserProjectile("circlebullet", initialXPos, initialYPos, 5, new Vector(1, -0.3));
            case ProjectileCode.ENEMY -> createEnemyProjectile("enemyFire", initialXPos, initialYPos, 1, new Vector(-1, 0));
            case ProjectileCode.ENEMY_ROUND_DOWN -> createEnemyProjectile("circlebulletblue", initialXPos, initialYPos, 1, new Vector(-1, -0.3));
            case ProjectileCode.ENEMY_ROUND_UP -> createEnemyProjectile("circlebulletblue", initialXPos, initialYPos, 1, new Vector(-1, 0.3));
            case ProjectileCode.BOSS -> createBossProjectile(initialXPos, initialYPos, 1);
        };
    }

    protected Projectile createGenericProjectile(String imageName, double fitHeight, double speed, double initialXPos, double initialYPos, int damage, Vector velocity, boolean travellingRight) {
        ImageView imageView = makeView(imageName);
        imageView.setFitHeight(fitHeight);

        ActorStrategy actorStrategy = new LinearProjectileStrategy(velocity);

        Projectile projectile = new Projectile(actorStrategy, root, imageView, damage, speed, travellingRight);
        projectile.setPosition(initialXPos, initialYPos);

        return projectile;
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
