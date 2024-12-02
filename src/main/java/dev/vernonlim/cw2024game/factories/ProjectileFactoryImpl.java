package dev.vernonlim.cw2024game.factories;

import dev.vernonlim.cw2024game.assets.AssetLoader;
import dev.vernonlim.cw2024game.elements.ProjectileCode;
import dev.vernonlim.cw2024game.elements.actors.BossProjectile;
import dev.vernonlim.cw2024game.elements.actors.EnemyProjectile;
import dev.vernonlim.cw2024game.elements.actors.Projectile;
import dev.vernonlim.cw2024game.elements.actors.UserProjectile;
import dev.vernonlim.cw2024game.factories.interfaces.ProjectileFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class ProjectileFactoryImpl extends FactoryParent implements ProjectileFactory {
    public ProjectileFactoryImpl(Pane root, AssetLoader loader) {
        super(root, loader);
    }

    public Projectile createProjectile(ProjectileCode code, double initialXPos, double initialYPos) {
        return switch (code) {
            case ProjectileCode.USER -> createUserProjectile(initialXPos, initialYPos);
            case ProjectileCode.ENEMY -> createEnemyProjectile(initialXPos, initialYPos);
            case ProjectileCode.BOSS -> createBossProjectile(initialXPos, initialYPos);
        };
    }

    protected Projectile createUserProjectile(double initialXPos, double initialYPos) {
        ImageView imageView = makeView("userfire");
        imageView.setFitHeight(12);

        UserProjectile projectile = new UserProjectile(root, imageView);
        projectile.setPosition(initialXPos, initialYPos);

        return projectile;
    }

    protected Projectile createEnemyProjectile(double initialXPos, double initialYPos) {
        ImageView imageView = makeView("enemyFire");
        imageView.setFitHeight(34);

        EnemyProjectile projectile = new EnemyProjectile(root, imageView);
        projectile.setPosition(initialXPos, initialYPos);

        return projectile;
    }

    protected Projectile createBossProjectile(double initialXPos, double initialYPos) {
        ImageView imageView = makeView("fireball");
        imageView.setFitHeight(75);

        BossProjectile projectile = new BossProjectile(root, imageView);
        projectile.setPosition(initialXPos, initialYPos);

        return projectile;
    }
}
