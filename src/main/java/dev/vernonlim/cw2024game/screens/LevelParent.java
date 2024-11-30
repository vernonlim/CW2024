package dev.vernonlim.cw2024game.screens;

import dev.vernonlim.cw2024game.Controller;
import dev.vernonlim.cw2024game.Main;
import dev.vernonlim.cw2024game.assets.AssetLoader;
import dev.vernonlim.cw2024game.elements.Background;
import dev.vernonlim.cw2024game.elements.Element;
import dev.vernonlim.cw2024game.elements.ProjectileListener;
import dev.vernonlim.cw2024game.elements.actors.ActiveActorDestructible;
import dev.vernonlim.cw2024game.elements.actors.Projectile;
import dev.vernonlim.cw2024game.elements.actors.UserPlane;
import dev.vernonlim.cw2024game.elements.actors.UserProjectile;
import dev.vernonlim.cw2024game.elements.factories.ElementFactory;
import dev.vernonlim.cw2024game.managers.CollisionManager;
import dev.vernonlim.cw2024game.managers.DamageCollisionManager;
import dev.vernonlim.cw2024game.managers.InputManager;
import dev.vernonlim.cw2024game.overlays.GameplayOverlay;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public abstract class LevelParent implements Screen {
    private static final int FRAME_RATE = 120;

    protected final Pane root;
    protected final Scene scene;
    protected final UserPlane user;
    protected final AssetLoader loader;
    protected final ProjectileListener projectileListener;
    protected final ElementFactory elementFactory;
    private final Controller controller;
    private final InputManager inputManager;
    private final CollisionManager collisionManager;
    private final Stage stage; // this is just here for stopping the timer thread...

    private final Timeline timeline;
    private final Background background;

    private final List<ActiveActorDestructible> friendlyUnits;
    private final List<ActiveActorDestructible> enemyUnits;
    private final List<ActiveActorDestructible> userProjectiles;
    private final List<ActiveActorDestructible> enemyProjectiles;

    private final GameplayOverlay gameplayOverlay;

    private double lastUpdate;
    protected double lastEnemySpawnAttempt;
    protected Timer timer;
    protected double virtualTime;

    private int currentNumberOfEnemies;

    public LevelParent(Stage stage, Controller controller, AssetLoader loader, String backgroundImagePath, int playerInitialHealth) {
        this.stage = stage;

        // initializing the main nodes
        this.root = new Pane();

        // VERY important - limits the size of the Pane Node used for drawing
        // This allows the StackPane to properly align it
        root.setMaxHeight(Main.SCREEN_HEIGHT);
        root.setMaxWidth(Main.SCREEN_WIDTH);
        root.setClip(new Rectangle(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT));

        // To align the root pane above
        StackPane stackPane = new StackPane(root);
        stackPane.setStyle("-fx-background-color: black;");

        // Keeps the "camera" of sorts fixed in place
        this.scene = new Scene(new Group(stackPane));

        // letterboxing
        SceneSizeChangeListener.letterbox(scene, stackPane, Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);

        // initializing handlers
        this.inputManager = new InputManager(scene);
        this.controller = controller;
        this.loader = loader;
        this.collisionManager = new DamageCollisionManager();

        // what to do with projectiles?
        this.projectileListener = new ProjectileListener() {
            @Override
            public void onFire(Projectile projectile) {
                projectile.show();

                if (projectile instanceof UserProjectile) {
                    userProjectiles.add(projectile);
                } else {
                    enemyProjectiles.add(projectile);
                }
            }
        };

        this.elementFactory = new ElementFactory(root, loader, inputManager, projectileListener);

        // background
        this.background = elementFactory.createBackground(backgroundImagePath);

        // the overlay on top
        this.gameplayOverlay = elementFactory.withNewRoot(stackPane).createGameplayOverlay(playerInitialHealth);

        // activity
        this.timeline = new Timeline(FRAME_RATE);

        this.friendlyUnits = new ArrayList<>();
        this.enemyUnits = new ArrayList<>();
        this.userProjectiles = new ArrayList<>();
        this.enemyProjectiles = new ArrayList<>();

        this.currentNumberOfEnemies = 0;

        this.lastUpdate = System.currentTimeMillis(); // mostly arbitrary time at the start
        this.virtualTime = 0;
        this.timer = createTimer();
        this.lastEnemySpawnAttempt = -99999; // set to an arbitrary negative time to simulate no enemies having spawned

        this.user = elementFactory.createUserPlane(playerInitialHealth);
        friendlyUnits.add(user);

        initializeTimeline();

        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                try {
                    timer.stop();
                    Thread.sleep(100);
                    Platform.exit();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private Timer createTimer() {
        Timer timer = new Timer(1, (ae) -> {
            virtualTime += 1;
        });
        timer.start();
        return timer;
    }

    public Scene getScene() {
        return scene;
    }

    protected abstract void checkIfGameOver();

    protected abstract void spawnEnemyUnits(double currentTime);

    public void start() {
        background.requestFocus();
        timeline.play();
    }

    public void goToNextLevel(String levelName) {
        timeline.pause();
        Platform.runLater(() -> {
            controller.goToLevel(levelName);
        });
    }

    private void updateScene() {
        double currentTime = virtualTime;
        double deltaTime = currentTime - lastUpdate;
        lastUpdate = currentTime;

        // uses deltaTime to scale movement, currentTime to determine if some event should trigger
        updateActors(deltaTime, currentTime); // also spawns projectiles
        spawnEnemyUnits(currentTime);

        // these should be updated ASAP, so they don't need time information
        updateNumberOfEnemies();
        handleEnemyPenetration();
        handleAllCollisions();
        removeAllDestroyedActors();
        updateKillCount();
        updateOverlays();
        checkIfGameOver();
    }

    private void initializeTimeline() {
        timeline.setCycleCount(Timeline.INDEFINITE);
        KeyFrame gameLoop = new KeyFrame(Duration.millis(1000f / FRAME_RATE), e -> updateScene());
        timeline.getKeyFrames().add(gameLoop);
    }

    private void updateActors(double deltaTime, double currentTime) {
        userProjectiles.forEach(projectile -> projectile.updateActor(deltaTime, currentTime));
        enemyProjectiles.forEach(projectile -> projectile.updateActor(deltaTime, currentTime));
        friendlyUnits.forEach(plane -> plane.updateActor(deltaTime, currentTime));
        enemyUnits.forEach(enemy -> enemy.updateActor(deltaTime, currentTime));
    }

    private void removeAllDestroyedActors() {
        removeDestroyedActors(friendlyUnits);
        removeDestroyedActors(enemyUnits);
        removeDestroyedActors(userProjectiles);
        removeDestroyedActors(enemyProjectiles);
    }

    private void removeDestroyedActors(List<ActiveActorDestructible> actors) {
        List<ActiveActorDestructible> destroyedActors = actors.stream().filter(ActiveActorDestructible::isDestroyed).toList();
        destroyedActors.forEach(Element::hide);
        actors.removeAll(destroyedActors);
    }

    private void handleAllCollisions() {
        collisionManager.handleCollisions(friendlyUnits, enemyUnits);
        collisionManager.handleCollisions(userProjectiles, enemyUnits);
        collisionManager.handleCollisions(enemyProjectiles, friendlyUnits);
    }

    private void handleEnemyPenetration() {
        for (ActiveActorDestructible enemy : enemyUnits) {
            if (enemyHasPenetratedDefenses(enemy)) {
                user.takeDamage();
                enemy.destroy();
            }
        }
    }

    private void updateOverlays() {
        gameplayOverlay.removeHearts(user.getHealth());
    }

    private void updateKillCount() {
        for (int i = 0; i < currentNumberOfEnemies - enemyUnits.size(); i++) {
            user.incrementKillCount();
        }
    }

    private boolean enemyHasPenetratedDefenses(ActiveActorDestructible enemy) {
        return enemy.getX() < 0;
    }

    protected void winGame() {
        timeline.stop();
        gameplayOverlay.showWinImage();
    }

    protected void loseGame() {
        timeline.stop();
        gameplayOverlay.showGameOverImage();
    }

    protected int getCurrentNumberOfEnemies() {
        return enemyUnits.size();
    }

    protected void addEnemyUnit(ActiveActorDestructible enemy) {
        enemy.show();
        enemyUnits.add(enemy);
    }

    private void updateNumberOfEnemies() {
        currentNumberOfEnemies = enemyUnits.size();
    }
}