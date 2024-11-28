package dev.vernonlim.cw2024game.levels;

import java.util.*;
import java.util.stream.Collectors;

import dev.vernonlim.cw2024game.*;
import dev.vernonlim.cw2024game.controller.Controller;
import dev.vernonlim.cw2024game.controller.SceneSizeChangeListener;
import javafx.animation.*;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.*;
import javafx.scene.input.*;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public abstract class LevelParent {
    private static final int FRAME_RATE = 5000;
    private final double screenHeight;
    private final double screenWidth;
    private final double enemyMaximumYPosition;

    private final Group root;
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

    private double lastUpdate;
    protected double lastEnemySpawnAttempt;

    public BooleanProperty upPressed = new SimpleBooleanProperty();
    public BooleanProperty downPressed = new SimpleBooleanProperty();
    public BooleanProperty leftPressed = new SimpleBooleanProperty();
    public BooleanProperty rightPressed = new SimpleBooleanProperty();
    public BooleanProperty spacePressed = new SimpleBooleanProperty();

    public LevelParent(Controller controller, String backgroundImagePath, double screenHeight, double screenWidth, int playerInitialHealth) {
        // To make this resizeable
        Pane pane = new Pane();
        pane.setStyle("-fx-background-color: black;");

        this.root = new Group(pane);
        this.scene = new Scene(root, screenWidth, screenHeight);

        letterbox(scene, pane, screenWidth, screenHeight);

        this.timeline = new Timeline(FRAME_RATE);
        this.user = new UserPlane(playerInitialHealth);
        this.background = new ImageView(new Image(Controller.fetchResourcePath(backgroundImagePath)));

        this.friendlyUnits = new ArrayList<>();
        this.enemyUnits = new ArrayList<>();
        this.userProjectiles = new ArrayList<>();
        this.enemyProjectiles = new ArrayList<>();

        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;
        this.enemyMaximumYPosition = screenHeight - 108;
        this.levelView = instantiateLevelView();
        this.currentNumberOfEnemies = 0;
        this.lastUpdate = System.currentTimeMillis(); // mostly arbitrary time at the start
        this.lastEnemySpawnAttempt = -99999; // set to an arbitrary negative time to simulate no enemies having spawned
        initializeTimeline();
        initializeListeners();
        friendlyUnits.add(user);

        this.controller = controller;
    }

    // Credit:
    // https://stackoverflow.com/questions/16606162/javafx-fullscreen-resizing-elements-based-upon-screen-size
    private void letterbox(Scene scene, Pane contentPane, double initWidth, double initHeight) {
        final double ratio = initWidth / initHeight;

        SceneSizeChangeListener sizeListener = new SceneSizeChangeListener(scene, ratio, initHeight, initWidth, contentPane);
        scene.widthProperty().addListener(sizeListener);
        scene.heightProperty().addListener(sizeListener);
    }

    protected abstract void initializeFriendlyUnits();

    protected abstract void checkIfGameOver();

    protected abstract void spawnEnemyUnits(double currentTime);

    protected abstract LevelView instantiateLevelView();

    public Scene initializeScene() {
        initializeBackground();
        initializeControls();
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

    private void initializeListeners() {
        upPressed.addListener((observable, wasPressed, nowPressed) -> {
            user.shouldMoveUp = nowPressed;
        });
        downPressed.addListener((observable, wasPressed, nowPressed) -> {
            user.shouldMoveDown = nowPressed;
        });
        leftPressed.addListener((observable, wasPressed, nowPressed) -> {
            user.shouldMoveLeft = nowPressed;
        });
        rightPressed.addListener((observable, wasPressed, nowPressed) -> {
            user.shouldMoveRight = nowPressed;
        });
        spacePressed.addListener((observable, wasPressed, nowPressed) -> {
            user.shouldFire = nowPressed;
        });
    }

    private void initializeBackground() {
        background.setFocusTraversable(true);
        background.setFitHeight(screenHeight);
        background.setFitWidth(screenWidth);
        root.getChildren().add(background);
    }

    private void initializeControls() {
        background.setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent e) {
                KeyCode kc = e.getCode();
                if (kc == KeyCode.UP) upPressed.set(true);
                if (kc == KeyCode.DOWN) downPressed.set(true);
                if (kc == KeyCode.LEFT) leftPressed.set(true);
                if (kc == KeyCode.RIGHT) rightPressed.set(true);
                if (kc == KeyCode.SPACE || kc == KeyCode.Z) spacePressed.set(true);
            }
        });
        background.setOnKeyReleased(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent e) {
                KeyCode kc = e.getCode();
                if (kc == KeyCode.UP) upPressed.set(false);
                if (kc == KeyCode.DOWN) downPressed.set(false);
                if (kc == KeyCode.LEFT) leftPressed.set(false);
                if (kc == KeyCode.RIGHT) rightPressed.set(false);
                if (kc == KeyCode.SPACE) spacePressed.set(false);
                if (kc == KeyCode.SPACE || kc == KeyCode.Z) spacePressed.set(false);
            }
        });
    }

    private void generateUserFire(double currentTime) {
        ActiveActorDestructible projectile = user.fireProjectile(currentTime);

        if (projectile != null) {
            root.getChildren().add(projectile);
            userProjectiles.add(projectile);
        }
    }

    private void generateEnemyFire(double currentTime) {
        enemyUnits.forEach(enemy -> spawnEnemyProjectile(((FighterPlane) enemy).fireProjectile(currentTime)));
    }

    private void spawnEnemyProjectile(ActiveActorDestructible projectile) {
        if (projectile != null) {
            root.getChildren().add(projectile);
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
        List<ActiveActorDestructible> destroyedActors = actors.stream().filter(actor -> actor.isDestroyed())
                .collect(Collectors.toList());
        root.getChildren().removeAll(destroyedActors);
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
        return Math.abs(enemy.getTranslateX()) > screenWidth;
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

    protected Group getRoot() {
        return root;
    }

    protected int getCurrentNumberOfEnemies() {
        return enemyUnits.size();
    }

    protected void addEnemyUnit(ActiveActorDestructible enemy) {
        enemyUnits.add(enemy);
        root.getChildren().add(enemy);
    }

    protected double getEnemyMaximumYPosition() {
        return enemyMaximumYPosition;
    }

    protected double getScreenWidth() {
        return screenWidth;
    }

    protected boolean userIsDestroyed() {
        return user.isDestroyed();
    }

    private void updateNumberOfEnemies() {
        currentNumberOfEnemies = enemyUnits.size();
    }

}