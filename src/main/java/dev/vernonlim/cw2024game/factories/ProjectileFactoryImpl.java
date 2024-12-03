package dev.vernonlim.cw2024game.factories;

import dev.vernonlim.cw2024game.assets.AssetLoader;
import dev.vernonlim.cw2024game.elements.ProjectileCode;
import dev.vernonlim.cw2024game.elements.Vector;
import dev.vernonlim.cw2024game.elements.actors.BossProjectile;
import dev.vernonlim.cw2024game.elements.actors.EnemyProjectile;
import dev.vernonlim.cw2024game.elements.actors.Projectile;
import dev.vernonlim.cw2024game.elements.actors.UserProjectile;
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
            case ProjectileCode.ENEMY -> createEnemyProjectile(initialXPos, initialYPos, 1);
            case ProjectileCode.BOSS -> createBossProjectile(initialXPos, initialYPos, 1);
        };
    }

    protected Projectile createUserProjectile(String imageName, double initialXPos, double initialYPos, int damage, Vector velocity) {
        ImageView imageView = makeView(imageName);
        imageView.setFitHeight(12);

        ActorStrategy actorStrategy = new LinearProjectileStrategy(velocity);

        UserProjectile projectile = new UserProjectile(actorStrategy, root, imageView, damage);
        projectile.setPosition(initialXPos, initialYPos);

        return projectile;
    }

    protected Projectile createEnemyProjectile(double initialXPos, double initialYPos, int damage) {
        ImageView imageView = makeView("enemyFire");
        imageView.setFitHeight(34);

        ActorStrategy actorStrategy = new LinearProjectileStrategy(new Vector(-1, 0));

        EnemyProjectile projectile = new EnemyProjectile(actorStrategy, root, imageView, damage);
        projectile.setPosition(initialXPos, initialYPos);

        return projectile;
    }

    protected Projectile createBossProjectile(double initialXPos, double initialYPos, int damage) {
        ImageView imageView = makeView("fireball");
        imageView.setFitHeight(75);

        ActorStrategy actorStrategy = new LinearProjectileStrategy(new Vector(-1, 0));

        BossProjectile projectile = new BossProjectile(actorStrategy, root, imageView, damage);
        projectile.setPosition(initialXPos, initialYPos);

        return projectile;
    }
}
