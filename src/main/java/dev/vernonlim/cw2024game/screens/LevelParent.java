package dev.vernonlim.cw2024game.screens;

import java.util.*;

import dev.vernonlim.cw2024game.Main;
import dev.vernonlim.cw2024game.elements.Element;
import dev.vernonlim.cw2024game.elements.actors.ActiveActorDestructible;
import dev.vernonlim.cw2024game.elements.actors.FighterPlane;
import dev.vernonlim.cw2024game.elements.actors.UserPlane;
import dev.vernonlim.cw2024game.Controller;
import dev.vernonlim.cw2024game.input.Input;
import dev.vernonlim.cw2024game.overlays.LevelView;
import javafx.animation.*;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public abstract class LevelParent {
    private static final int FRAME_RATE = 1000;
    private final double enemyMaximumYPosition;

    private final Pane root;
    private final Scene scene;
    private final Timeline timeline;
    private final UserPlane user;
    private final ImageView background;

    private final List<ActiveActorDestructible> friendlyUnits;
    private final List<ActiveActorDestructible> enemyUnits;
    private final List<ActiveActorDestructible> userProjectiles;
    private final List<ActiveActorDestructible> enemyProjectiles;

    private int currentNumberOfEnemies;
    private final LevelView levelView;

    private final Controller controller;
    private final Input input;

    private double lastUpdate;
    protected double lastEnemySpawnAttempt;

    public LevelParent(Controller controller, String backgroundImagePath, int playerInitialHealth) {
        this.root = new Pane();

        // VERY important - limits the size of the Pane Node used for drawing
        // This allows the StackPane to properly align it
        root.setMaxHeight(Main.SCREEN_HEIGHT);
        root.setMaxWidth(Main.SCREEN_WIDTH);

//        root.setClip(new Rectangle(screenWidth, screenHeight));

        // To align the root pane above
        StackPane stackPane = new StackPane(root);
        stackPane.setStyle("-fx-background-color: black;");

        // Keeps the "camera" of sorts fixed in place
        this.scene = new Scene(new Group(stackPane));

        this.input = new Input(scene);

        SceneSizeChangeListener.letterbox(scene, stackPane, Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);

        this.timeline = new Timeline(60);
        this.user = new UserPlane(root, playerInitialHealth, input);
        this.background = new ImageView(new Image(Controller.fetchResourcePath(backgroundImagePath)));

        this.friendlyUnits = new ArrayList<>();
        this.enemyUnits = new ArrayList<>();
        this.userProjectiles = new ArrayList<>();
        this.enemyProjectiles = new ArrayList<>();

        this.enemyMaximumYPosition = Main.SCREEN_HEIGHT - 108;
        this.levelView = instantiateLevelView();
        this.currentNumberOfEnemies = 0;
        this.lastUpdate = System.currentTimeMillis(); // mostly arbitrary time at the start
        this.lastEnemySpawnAttempt = -99999; // set to an arbitrary negative time to simulate no enemies having spawned
        initializeTimeline();
        friendlyUnits.add(user);

        this.controller = controller;
    }

    protected abstract void initializeFriendlyUnits();

    protected abstract void checkIfGameOver();

    protected abstract void spawnEnemyUnits(double currentTime);

    protected abstract LevelView instantiateLevelView();

    public Scene initializeScene() {
        initializeBackground();
        initializeFriendlyUnits();
        levelView.showInitialImages();
        return scene;
    }

    public void startGame() {
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
        double currentTime = System.currentTimeMillis();
        double deltaTime = currentTime - lastUpdate;
        lastUpdate = currentTime;

        // uses deltaTime to scale movement, currentTime to determine if some event should trigger
        updateActors(deltaTime, currentTime);
        spawnEnemyUnits(currentTime);
        generateEnemyFire(currentTime);
        generateUserFire(currentTime);

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

    private void initializeBackground() {
        background.setFocusTraversable(true);
        background.setFitHeight(Main.SCREEN_HEIGHT);
        background.setFitWidth(Main.SCREEN_WIDTH);
        root.getChildren().add(background);
    }

    private void generateUserFire(double currentTime) {
        ActiveActorDestructible projectile = user.fireProjectile(currentTime);

        if (projectile != null) {
            projectile.show();
            userProjectiles.add(projectile);
        }
    }

    private void generateEnemyFire(double currentTime) {
        enemyUnits.forEach(enemy -> spawnEnemyProjectile(((FighterPlane) enemy).fireProjectile(currentTime)));
    }

    private void spawnEnemyProjectile(ActiveActorDestructible projectile) {
        if (projectile != null) {
            projectile.show();
            enemyProjectiles.add(projectile);
        }
    }

    private void updateActors(double deltaTime, double currentTime) {
        friendlyUnits.forEach(plane -> plane.updateActor(deltaTime, currentTime));
        enemyUnits.forEach(enemy -> enemy.updateActor(deltaTime, currentTime));
        userProjectiles.forEach(projectile -> projectile.updateActor(deltaTime, currentTime));
        enemyProjectiles.forEach(projectile -> projectile.updateActor(deltaTime, currentTime));
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
                if (actor.getBoundsInParent().intersects(otherActor.getBoundsInParent())) {
                    actor.takeDamage();
                    otherActor.takeDamage();
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
        levelView.removeHearts(user.getHealth());
    }

    private void updateKillCount() {
        for (int i = 0; i < currentNumberOfEnemies - enemyUnits.size(); i++) {
            user.incrementKillCount();
        }
    }

    private boolean enemyHasPenetratedDefenses(ActiveActorDestructible enemy) {
        return Math.abs(enemy.getX()) > Main.SCREEN_WIDTH;
    }

    protected void winGame() {
        timeline.stop();
        levelView.showWinImage();
    }

    protected void loseGame() {
        timeline.stop();
        levelView.showGameOverImage();
    }

    protected UserPlane getUser() {
        return user;
    }

    protected Pane getRoot() {
        return root;
    }

    protected int getCurrentNumberOfEnemies() {
        return enemyUnits.size();
    }

    protected void addEnemyUnit(ActiveActorDestructible enemy) {
        enemy.show();
        enemyUnits.add(enemy);
    }

    protected double getEnemyMaximumYPosition() {
        return enemyMaximumYPosition;
    }

    protected boolean userIsDestroyed() {
        return user.isDestroyed();
    }

    private void updateNumberOfEnemies() {
        currentNumberOfEnemies = enemyUnits.size();
    }

}