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

    public UserPlane createUserPlane(UserPlaneCode code) {
        return switch (code) {
            case REGULAR_PLANE -> createRegularPlane();
            case GREEN_PLANE -> createGreenPlane();
        };
    }

    private UserPlane createRegularPlane() {
        int initialHealth = 5;

        ImageView imageView = makeView("userplane");
        imageView.setFitHeight(40);

        AudioClip fireSound = loader.loadSound("gunshot");

        return new RegularPlane(projectileFactory, root, projectileListener, inputManager, imageView, fireSound, initialHealth, 24.0f, 7.0f);
    }

    private UserPlane createGreenPlane() {
        int initialHealth = 3;

        ImageView imageView = makeView("userplane2");
        imageView.setFitHeight(40);

        AudioClip fireSound = loader.loadSound("laser");

        return new GreenPlane(projectileFactory, root, projectileListener, inputManager, imageView, fireSound, initialHealth, 30.0f, 7.0f);
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
