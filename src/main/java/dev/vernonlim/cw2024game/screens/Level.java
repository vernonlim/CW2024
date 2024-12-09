package dev.vernonlim.cw2024game.screens;

import dev.vernonlim.cw2024game.Controller;
import dev.vernonlim.cw2024game.configs.ScreenConfig;
import dev.vernonlim.cw2024game.elements.Element;
import dev.vernonlim.cw2024game.elements.ProjectileListener;
import dev.vernonlim.cw2024game.elements.actors.ActiveActorDestructible;
import dev.vernonlim.cw2024game.elements.actors.UserPlane;
import dev.vernonlim.cw2024game.factories.ActorFactoryImpl;
import dev.vernonlim.cw2024game.factories.ProjectileFactoryImpl;
import dev.vernonlim.cw2024game.factories.interfaces.ActorFactory;
import dev.vernonlim.cw2024game.factories.interfaces.ProjectileFactory;
import dev.vernonlim.cw2024game.managers.CollisionManager;
import dev.vernonlim.cw2024game.managers.DamageCollisionManager;
import dev.vernonlim.cw2024game.overlays.GameplayOverlay;
import dev.vernonlim.cw2024game.overlays.MenuOverlay;
import javafx.application.Platform;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The abstract parent class for all Levels containing shared behaviour.
 * <p>
 * This class is modified.
 * Original Code: <a href="https://github.com/kooitt/CW2024/blob/master/src/main/java/com/example/demo/LevelParent.java">GitHub</a>
 */
public abstract class Level extends ScreenParent implements Screen {
    /**
     * The UserPlane associated with this Level representing the player character.
     */
    private final UserPlane user;
    /**
     * The CollisionManager associated with this Level.
     */
    private final CollisionManager collisionManager;
    /**
     * The GameplayOverlay associated with this Level.
     */
    private final GameplayOverlay gameplayOverlay;
    /**
     * The MenuOverlay associated with this Level.
     */
    private final MenuOverlay pauseOverlay;
    /**
     * The ProjectileListener associated with this Level. Meant to be passed to Actors.
     */
    private ProjectileListener projectileListener;
    /**
     * The ActorFactory associated with this Level.
     */
    private ActorFactory actorFactory;
    /**
     * The list of friendly Actors for this Level.
     */
    private List<ActiveActorDestructible> friendlyUnits;

    /**
     * The list of enemy Actors for this Level.
     */
    private List<ActiveActorDestructible> enemyUnits;

    /**
     * The list of user projectiles for this Level.
     */
    private List<ActiveActorDestructible> userProjectiles;

    /**
     * The list of enemy projectiles for this Level.
     */
    private List<ActiveActorDestructible> enemyProjectiles;

    /**
     * The last time this Level was updated.
     */
    private double lastUpdate;

    /**
     * The last time an enemy spawn attempt ran.
     */
    private double lastEnemySpawnAttempt;

    /**
     * The virtual timer associated with this Level.
     */
    private Timer timer;

    /**
     * The virtual time for this Level.
     */
    private double virtualTime;

    /**
     * The current number of enemies for this Level.
     */
    private int currentNumberOfEnemies;

    /**
     * Indicates whether this Level is paused.
     */
    private boolean paused;

    /**
     * The last time this Level was paused.
     */
    private double lastPaused;

    /**
     * Indicates whether the Main Menu should be loaded.
     */
    private boolean backToMainMenu;

    /**
     * Constructs a Level with a continually updating Timeline, but no Actors present.
     *
     * @param config the configuration object containing the necessary data to construct the Level
     */
    public Level(ScreenConfig config) {
        super(config);

        this.collisionManager = new DamageCollisionManager();

        initializeProjectileListener();
        initializeFactories();
        initializeUnitArrays();

        // must be run after the unit arrays are initialized
        this.user = actorFactory.createUserPlane(userPlaneCode);
        friendlyUnits.add(user);

        // the overlay on top
        this.gameplayOverlay = overlayFactory.createGameplayOverlay(user.getHealth());
        this.pauseOverlay = overlayFactory.createPauseOverlay(currentScreen);

        initializeState();
        fixCloseRequestHandling();
    }

    /**
     * Initializes the projectile listener for this Level.
     */
    private void initializeProjectileListener() {
        projectileListener = projectile -> {
            projectile.show();

            if (projectile.isAllyProjectile()) {
                userProjectiles.add(projectile);
            } else {
                enemyProjectiles.add(projectile);
            }
        };
    }

    /**
     * Initializes the arrays storing the actors for this Level.
     */
    private void initializeUnitArrays() {
        friendlyUnits = new ArrayList<>();
        enemyUnits = new ArrayList<>();
        userProjectiles = new ArrayList<>();
        enemyProjectiles = new ArrayList<>();
    }

    /**
     * Initializes the factories for this Level.
     */
    private void initializeFactories() {
        ProjectileFactory projectileFactory = new ProjectileFactoryImpl(root, loader);
        actorFactory = new ActorFactoryImpl(root, loader, inputManager, projectileFactory, projectileListener, elementFactory);
    }

    /**
     * Initializes the state fields for this Level.
     */
    private void initializeState() {
        this.paused = false;
        this.lastPaused = -99999.0;

        this.currentNumberOfEnemies = 0;

        this.lastUpdate = 0.0; // mostly arbitrary time at the start
        this.virtualTime = 0.0;
        this.timer = createTimer();
        this.lastEnemySpawnAttempt = -99999.0; // set to an arbitrary negative time to simulate no enemies having spawned
    }

    /**
     * Attempts to fix the game not quitting on close request.
     */
    private void fixCloseRequestHandling() {
        Controller.getStage().setOnCloseRequest(event -> {
            try {
                timer.stop();
                Thread.sleep(100);
                Platform.exit();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });
    }

    /**
     * Creates a Timer that increments virtualTime every millisecond.
     *
     * @return a Timer that increments virtualTime every millisecond
     */
    private Timer createTimer() {
        Timer timer = new Timer(1, (ae) -> virtualTime += 1);
        timer.start();
        return timer;
    }

    /**
     * Checks if the game is over yet, and perform the corresponding action.
     *
     * @param currentTime the current time of the virtual timer
     */
    protected abstract void checkIfGameOver(double currentTime);

    /**
     * Spawns enemy units for the depending on the amount of time that has passed.
     *
     * @param currentTime the current time of the virtual timer
     */
    protected abstract void spawnEnemyUnits(double currentTime);

    /**
     * Updates the Scene for this Level with the virtual time that has passed.
     */
    @Override
    protected void updateScene() {
        double currentTime = virtualTime;
        double deltaTime = currentTime - lastUpdate;
        lastUpdate = currentTime;

        double realCurrentTime = System.currentTimeMillis();

        handlePauseToggle(realCurrentTime);

        if (backToMainMenu || paused) {
            pauseOverlay.update(realCurrentTime);
            return;
        }

        updateActors(deltaTime, currentTime);
        spawnEnemyUnits(currentTime);
        updateNumberOfEnemies();
        handleEnemyPenetration();
        handleAllCollisions();
        removeAllDestroyedActors();
        updateKillCount();
        updateOverlays(currentTime);
        checkIfGameOver(currentTime);
    }

    /**
     * Checks if the pause button is pressed and if so, toggle pause.
     *
     * @param realCurrentTime the real time that has passed
     */
    private void handlePauseToggle(double realCurrentTime) {
        if (inputManager.isPausePressed() && realCurrentTime - lastPaused > 200.0) {
            lastPaused = realCurrentTime;

            paused = !paused;

            if (paused) {
                timer.stop();
                pauseOverlay.show();
            } else {
                timer.start();
                pauseOverlay.hide();
            }
        }
    }

    /**
     * Updates each Actor in this Level.
     *
     * @param deltaTime   the difference in virtual time between the previous update and current
     * @param currentTime the current virtual time
     */
    private void updateActors(double deltaTime, double currentTime) {
        userProjectiles.forEach(projectile -> projectile.updateActor(deltaTime, currentTime));
        enemyProjectiles.forEach(projectile -> projectile.updateActor(deltaTime, currentTime));
        friendlyUnits.forEach(plane -> plane.updateActor(deltaTime, currentTime));
        enemyUnits.forEach(enemy -> enemy.updateActor(deltaTime, currentTime));
    }

    /**
     * Removes all actors marked as destroyed within the Level's actor lists.
     */
    private void removeAllDestroyedActors() {
        removeDestroyedActors(friendlyUnits);
        removeDestroyedActors(enemyUnits);
        removeDestroyedActors(userProjectiles);
        removeDestroyedActors(enemyProjectiles);
    }

    /**
     * Removes the actors marked as destroyed within a list. Hides them from the scene, and then removes them from the list.
     *
     * @param actors the list of actors
     */
    protected void removeDestroyedActors(List<ActiveActorDestructible> actors) {
        List<ActiveActorDestructible> destroyedActors =
                actors.stream().filter(ActiveActorDestructible::isDestroyed).toList();
        destroyedActors.forEach(Element::hide);
        actors.removeAll(destroyedActors);
    }

    /**
     * Handles collisions between actors.
     */
    protected void handleAllCollisions() {
        collisionManager.handleCollisions(friendlyUnits, enemyUnits);
        collisionManager.handleCollisions(userProjectiles, enemyUnits);
        collisionManager.handleCollisions(enemyProjectiles, friendlyUnits);
    }

    /**
     * Handles enemies reaching the left of the playable window.
     */
    protected void handleEnemyPenetration() {
        for (ActiveActorDestructible enemy : enemyUnits) {
            if (enemyHasPenetratedDefenses(enemy)) {
                user.takeDamage(1);
                enemy.destroy();
            }
        }
    }

    /**
     * Updates overlays.
     *
     * @param currentTime the current virtual time
     */
    protected void updateOverlays(double currentTime) {
        gameplayOverlay.removeHearts(user.getHealth());
    }

    /**
     * Updates the kill count.
     */
    private void updateKillCount() {
        for (int i = 0; i < currentNumberOfEnemies - enemyUnits.size(); i++) {
            user.incrementKillCount();
        }
    }

    /**
     * Checks whether an enemy has passed the left side of the window.
     *
     * @param enemy the enemy to be checked
     * @return true if the enemy is past the left side of the window, false otherwise
     */
    protected boolean enemyHasPenetratedDefenses(ActiveActorDestructible enemy) {
        return enemy.getX() < 0;
    }

    /**
     * Starts the win sequence of this Level
     */
    protected void winGame() {
        gameplayOverlay.showWinImage();
        pauseOverlay.show();
        paused = true;
        backToMainMenu = true;
    }

    /**
     * Starts the loss sequence of this Level.
     */
    protected void loseGame() {
        gameplayOverlay.showGameOverImage();
        pauseOverlay.show();
        paused = true;
        backToMainMenu = true;
    }

    /**
     * Gets the current number of enemies.
     *
     * @return the current number of enemies
     */
    protected int getCurrentNumberOfEnemies() {
        return enemyUnits.size();
    }

    /**
     * Adds an enemy actor to the Level. Shows them and then adds them to the list.
     *
     * @param enemy the enemy to be added
     */
    protected void addEnemyUnit(ActiveActorDestructible enemy) {
        enemy.show();
        enemyUnits.add(enemy);
    }

    /**
     * Updates the number of enemies for this Level.
     */
    private void updateNumberOfEnemies() {
        currentNumberOfEnemies = enemyUnits.size();
    }

    /**
     * Gets the number of user kills for this Level.
     *
     * @return the number of user kills
     */
    protected int getUserKills() {
        return user.getNumberOfKills();
    }

    /**
     * Gets the last enemy spawn attempt for this Level.
     *
     * @return the last enemy spawn attempt
     */
    protected double getLastEnemySpawnAttempt() {
        return lastEnemySpawnAttempt;
    }

    /**
     * Sets the last enemy spawn attempt for this Level.
     *
     * @param time the time to be set
     */
    protected void setLastEnemySpawnAttempt(double time) {
        lastEnemySpawnAttempt = time;
    }

    /**
     * Indicates whether the user has been destroyed.
     *
     * @return true if the user has been destroyed, false otherwise
     */
    protected boolean isUserDestroyed() {
        return user.isDestroyed();
    }

    /**
     * Gets the ActorFactory for this Level.
     *
     * @return the ActorFactory for this Level
     */
    protected ActorFactory getActorFactory() {
        return actorFactory;
    }
}