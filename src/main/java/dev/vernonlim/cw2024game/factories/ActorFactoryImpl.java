package dev.vernonlim.cw2024game.factories;

import dev.vernonlim.cw2024game.assets.AssetLoader;
import dev.vernonlim.cw2024game.elements.ProjectileListener;
import dev.vernonlim.cw2024game.elements.actors.*;
import dev.vernonlim.cw2024game.factories.interfaces.ActorFactory;
import dev.vernonlim.cw2024game.factories.interfaces.ElementFactory;
import dev.vernonlim.cw2024game.factories.interfaces.ProjectileFactory;
import dev.vernonlim.cw2024game.managers.InputManager;
import javafx.scene.image.ImageView;
import javafx.scene.media.AudioClip;
import javafx.scene.layout.Pane;

public class ActorFactoryImpl extends FactoryParent implements ActorFactory {
    protected InputManager inputManager;
    protected ProjectileListener projectileListener;
    protected ProjectileFactory projectileFactory;
    protected ElementFactory elementFactory;

    public ActorFactoryImpl(Pane root, AssetLoader loader, InputManager inputManager, ProjectileFactory projectileFactory, ProjectileListener projectileListener, ElementFactory elementFactory) {
        super(root, loader);

        this.inputManager = inputManager;
        this.projectileFactory = projectileFactory;
        this.projectileListener = projectileListener;
        this.elementFactory = elementFactory;
    }

    public UserPlane createUserPlane(int initialHealth) {
        ImageView imageView = makeView("userplane");
        imageView.setFitHeight(40);

        AudioClip fireSound = loader.loadSound("gunshot");

        return new UserPlane(projectileFactory, root, projectileListener, inputManager, imageView, fireSound, initialHealth);
    }

    public FighterPlane createEnemyPlane(double initialXPos, double initialYPos) {
        ImageView imageView = makeView("enemyplane");
        imageView.setFitHeight(54);

        EnemyPlane plane = new EnemyPlane(projectileFactory, root, projectileListener, imageView);
        plane.setPosition(initialXPos, initialYPos);

        return plane;
    }

    public FighterPlane createBoss() {
        ImageView imageView = makeView("bossplane");
        imageView.setFitHeight(56);

        return new Boss(elementFactory, projectileFactory, root, projectileListener, imageView);
    }
}
