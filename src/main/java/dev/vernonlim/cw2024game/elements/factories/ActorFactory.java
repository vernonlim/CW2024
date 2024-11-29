package dev.vernonlim.cw2024game.elements.factories;

import dev.vernonlim.cw2024game.assets.AssetLoader;
import dev.vernonlim.cw2024game.elements.ProjectileListener;
import dev.vernonlim.cw2024game.elements.actors.*;
import dev.vernonlim.cw2024game.input.InputManager;
import javafx.scene.layout.Pane;

public class ActorFactory {
    private final Pane root;
    private final AssetLoader loader;
    private final InputManager inputManager;
    private final ProjectileListener projectileListener;

    public ActorFactory(Pane root, AssetLoader loader, InputManager inputManager, ProjectileListener projectileListener) {
        this.root = root;
        this.loader = loader;
        this.inputManager = inputManager;
        this.projectileListener = projectileListener;
    }

    public UserPlane createUserPlane(int initialHealth) {
        return new UserPlane(this, root, loader, projectileListener, inputManager, initialHealth);
    }

    public EnemyPlane createEnemyPlane(double initialXPos, double initialYPos) {
        return new EnemyPlane(this, root, loader, projectileListener, initialXPos, initialYPos);
    }

    public Boss createBoss() {
        return new Boss(this, root, loader, projectileListener);
    }

    public UserProjectile createUserProjectile(double initialXPos, double initialYPos) {
        return new UserProjectile(root, loader, initialXPos, initialYPos);
    }

    public EnemyProjectile createEnemyProjectile(double initialXPos, double initialYPos) {
        return new EnemyProjectile(root, loader, initialXPos, initialYPos);
    }

    public BossProjectile createBossProjectile(double initialXPos, double initialYPos) {
        return new BossProjectile(root, loader, initialXPos, initialYPos);
    }
}
