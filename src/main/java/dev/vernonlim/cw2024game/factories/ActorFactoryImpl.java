package dev.vernonlim.cw2024game.factories;

import dev.vernonlim.cw2024game.Main;
import dev.vernonlim.cw2024game.assets.AssetLoader;
import dev.vernonlim.cw2024game.elements.ProjectileListener;
import dev.vernonlim.cw2024game.elements.actors.*;
import dev.vernonlim.cw2024game.elements.configs.BossConfig;
import dev.vernonlim.cw2024game.elements.configs.FighterPlaneConfig;
import dev.vernonlim.cw2024game.elements.configs.UserPlaneConfig;
import dev.vernonlim.cw2024game.elements.strategies.*;
import dev.vernonlim.cw2024game.factories.interfaces.ActorFactory;
import dev.vernonlim.cw2024game.factories.interfaces.ElementFactory;
import dev.vernonlim.cw2024game.factories.interfaces.ProjectileFactory;
import dev.vernonlim.cw2024game.managers.InputManager;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;

public class ActorFactoryImpl extends Factory implements ActorFactory {
    private final InputManager inputManager;
    private final ProjectileListener projectileListener;
    private final ProjectileFactory projectileFactory;
    private final ElementFactory elementFactory;

    public ActorFactoryImpl(Pane root, AssetLoader loader, InputManager inputManager, ProjectileFactory projectileFactory, ProjectileListener projectileListener, ElementFactory elementFactory) {
        super(root, loader);

        this.inputManager = inputManager;
        this.projectileFactory = projectileFactory;
        this.projectileListener = projectileListener;
        this.elementFactory = elementFactory;
    }

    @Override
    public UserPlane createUserPlane(UserPlaneCode userPlaneCode) {
        return switch (userPlaneCode) {
            case REGULAR_PLANE -> createRegularPlane();
            case GREEN_PLANE -> createGreenPlane();
        };
    }

    @Override
    public FighterPlane createEnemy(EnemyCode enemyCode, double initialXPos, double initialYPos) {
        return switch (enemyCode) {
            case ENEMY_PLANE -> createEnemyPlane(initialXPos, initialYPos);
            case BOSS -> createBoss();
            case ENEMY_BLUE -> createEnemyBlue(initialXPos, initialYPos);
            case ENEMY_RED -> createEnemyRed(initialXPos, initialYPos);
        };
    }

    private UserPlane createRegularPlane() {
        UserPlaneConfig config = new UserPlaneConfig(root, projectileFactory, projectileListener, inputManager);
        config.setPosition(5.0, Main.SCREEN_HEIGHT / 2.0);
        config.setHealth(5);
        config.setImage(loadImage("userplane"));
        config.setFitHeight(40.0);
        config.setFireSound(loader.loadSound("gunshot"));
        config.setDeathSound(loader.loadSound("pichuun"));
        config.setMovement(new RegularPlaneMovement(inputManager));
        config.setFiring(new RegularPlaneFiring(inputManager));
        config.setSpeed(24.0);
        config.setProjectileYOffset(7.0);

        return new RegularPlane(config);
    }

    private UserPlane createGreenPlane() {
        UserPlaneConfig config = new UserPlaneConfig(root, projectileFactory, projectileListener, inputManager);
        config.setPosition(5.0, Main.SCREEN_HEIGHT / 2.0);
        config.setHealth(3);
        config.setImage(loadImage("userplane2"));
        config.setFitHeight(40.0);
        config.setFireSound(loader.loadSound("laser"));
        config.setDeathSound(loader.loadSound("pichuun"));
        config.setMovement(new GreenPlaneMovement(inputManager));
        config.setFiring(new GreenPlaneFiring(inputManager));
        config.setSpeed(30.0);
        config.setProjectileYOffset(7.0);

        return new GreenPlane(config);
    }

    private FighterPlane createEnemyPlane(double initialXPos, double initialYPos) {
        FighterPlaneConfig config = new FighterPlaneConfig(root, projectileFactory, projectileListener);
        config.setPosition(initialXPos, initialYPos);
        config.setHealth(10);
        config.setImage(loadImage("enemyplane"));
        config.setFitHeight(54.0);
        config.setFireSound(loader.loadSound("missile"));
        config.setDeathSound(loader.loadSound("explosion"));
        config.setMovement(new EnemyPlaneMovement());
        config.setFiring(new EnemyPlaneFiring());
        config.setSpeed(5.0);
        config.setProjectileYOffset(7.0);
        config.setFacingRight(false);
        config.setAlwaysInBounds(false);

        return new EnemyPlane(config);
    }

    private FighterPlane createEnemyBlue(double initialXPos, double initialYPos) {
        FighterPlaneConfig config = new FighterPlaneConfig(root, projectileFactory, projectileListener);
        config.setPosition(initialXPos, initialYPos);
        config.setHealth(10);
        config.setImage(loadImage("enemyblue"));
        config.setFitHeight(54.0);
        config.setFireSound(loader.loadSound("laser"));
        config.setDeathSound(loader.loadSound("explosion"));
        config.setSpeed(5.0);
        config.setProjectileYOffset(7.0);
        config.setFacingRight(false);
        config.setAlwaysInBounds(false);
        config.setMovement(new EnemyPlaneMovement());

        EnemyPlane plane = new EnemyPlane(config);

        plane.setFiringStrategy(new BlueEnemyFiring(plane));

        return plane;
    }

    private FighterPlane createEnemyRed(double initialXPos, double initialYPos) {
        FighterPlaneConfig config = new FighterPlaneConfig(root, projectileFactory, projectileListener);
        config.setPosition(initialXPos, initialYPos);
        config.setHealth(10);
        config.setImage(loadImage("enemyred"));
        config.setFitHeight(54.0);
        config.setFireSound(loader.loadSound("laser"));
        config.setDeathSound(loader.loadSound("explosion"));
        config.setMovement(new EnemyPlaneMovement());
        config.setFiring(new RedEnemyFiring());
        config.setSpeed(50.0);
        config.setProjectileYOffset(7.0);
        config.setFacingRight(false);
        config.setAlwaysInBounds(false);

        return new EnemyPlane(config);
    }

    private FighterPlane createBoss() {
        BossConfig config = new BossConfig(root, projectileFactory, projectileListener);
        config.setPosition(Main.SCREEN_WIDTH - 5.0, Main.SCREEN_HEIGHT / 2.0);
        config.setHealth(1000);
        config.setImage(loadImage("bossplane"));
        config.setFitHeight(56.0);
        config.setFireSound(loader.loadSound("fireball"));
        config.setDeathSound(loader.loadSound("explosion"));
        config.setShieldSound(loader.loadSound("metalhit"));

        config.setShieldImage(elementFactory.createShieldImage());

        AudioClip damageSound = loader.loadSound("click");
        damageSound.setVolume(1.0);
        config.setDamageSound(damageSound);

        config.setMovement(new BossMovement());
        config.setFiring(new BossFiring());
        config.setShielding(new BossShielding());
        config.setSpeed(8.0);
        config.setProjectileYOffset(0.0);
        config.setFacingRight(false);
        config.setAlwaysInBounds(true);

        return new Boss(config);
    }
}
