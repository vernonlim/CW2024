package dev.vernonlim.cw2024game.screens;

import java.util.*;

import dev.vernonlim.cw2024game.Main;
import dev.vernonlim.cw2024game.assets.AssetLoader;
import dev.vernonlim.cw2024game.assets.UpFrontAssetLoader;
import dev.vernonlim.cw2024game.elements.Background;
import dev.vernonlim.cw2024game.elements.Element;
import dev.vernonlim.cw2024game.elements.ProjectileListener;
import dev.vernonlim.cw2024game.elements.actors.ActiveActorDestructible;
import dev.vernonlim.cw2024game.elements.actors.Projectile;
import dev.vernonlim.cw2024game.elements.actors.UserPlane;
import dev.vernonlim.cw2024game.Controller;
import dev.vernonlim.cw2024game.elements.actors.UserProjectile;
import dev.vernonlim.cw2024game.input.Input;
import dev.vernonlim.cw2024game.overlays.GameplayOverlay;
import javafx.animation.*;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import javax.swing.Timer;

public abstract class LevelParent implements Screen {
    private static final int FRAME_RATE = 120;

    protected final Pane root;
    protected final Scene scene;
    private final Timeline timeline;
    protected final UserPlane user;
    private final Background background;

    private final List<ActiveActorDestructible> friendlyUnits;
    private final List<ActiveActorDestructible> enemyUnits;
    private final List<ActiveActorDestructible> userProjectiles;
    private final List<ActiveActorDestructible> enemyProjectiles;

    private int currentNumberOfEnemies;
    private final GameplayOverlay gameplayOverlay;

    private final Controller controller;
    private final Input input;
    protected final AssetLoader loader;
    protected final ProjectileListener projectileListener;

    private double lastUpdate;
    protected double lastEnemySpawnAttempt;

    protected Timer timer;
    protected double virtualTime;

    public LevelParent(Controller controller, AssetLoader loader, String backgroundImagePath, int playerInitialHealth) {
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

        // background
        this.background = new Background(root, loader, backgroundImagePath);

        // the overlay on top
        this.gameplayOverlay = new GameplayOverlay(stackPane, loader, playerInitialHealth);

        // letterboxing
        SceneSizeChangeListener.letterbox(scene, stackPane, Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);

        // initializing handlers
        this.input = new Input(scene);
        this.controller = controller;
        this.loader = loader;

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

        this.user = new UserPlane(root, loader, projectileListener, input, playerInitialHealth);
        friendlyUnits.add(user);

        initializeTimeline();
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
        handleUserProjectileCollisions();
        handleEnemyProjectileCollisions();
        handlePlaneCollisions(); // TODO: fix collision damage happening every frame
        removeAllDestroyedActors();
        updateKillCount();
        updateLevelView();
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

    private void handlePlaneCollisions() {
        handleCollisions(friendlyUnits, enemyUnits);
    }

    private void handleUserProjectileCollisions() {
        handleCollisions(userProjectiles, enemyUnits);
    }

    private void handleEnemyProjectileCollisions() {
        handleCollisions(enemyProjectiles, friendlyUnits);
    }

    private void handleCollisions(List<ActiveActorDestructible> actors1,
                                  List<ActiveActorDestructible> actors2) {
        for (ActiveActorDestructible actor : actors2) {
            for (ActiveActorDestructible otherActor : actors1) {
                if (actor.getCollisionBounds().intersects(otherActor.getCollisionBounds())) {
                    actor.collideWith(otherActor);
                }
            }
        }
    }

    private void handleEnemyPenetration() {
        for (ActiveActorDestructible enemy : enemyUnits) {
            if (enemyHasPenetratedDefenses(enemy)) {
                user.takeDamage();
                enemy.destroy();
            }
        }
    }

    private void updateLevelView() {
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