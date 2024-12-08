package dev.vernonlim.cw2024game.screens;

import dev.vernonlim.cw2024game.Controller;
import dev.vernonlim.cw2024game.elements.Element;
import dev.vernonlim.cw2024game.elements.ProjectileListener;
import dev.vernonlim.cw2024game.elements.actors.ActiveActorDestructible;
import dev.vernonlim.cw2024game.elements.actors.Projectile;
import dev.vernonlim.cw2024game.elements.actors.UserPlane;
import dev.vernonlim.cw2024game.configs.ScreenConfig;
import dev.vernonlim.cw2024game.factories.ActorFactoryImpl;
import dev.vernonlim.cw2024game.factories.ProjectileFactoryImpl;
import dev.vernonlim.cw2024game.factories.interfaces.ActorFactory;
import dev.vernonlim.cw2024game.factories.interfaces.ProjectileFactory;
import dev.vernonlim.cw2024game.managers.CollisionManager;
import dev.vernonlim.cw2024game.managers.DamageCollisionManager;
import dev.vernonlim.cw2024game.overlays.GameplayOverlay;
import dev.vernonlim.cw2024game.overlays.MenuOverlay;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.stage.WindowEvent;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public abstract class Level extends ScreenParent implements Screen {
    private final UserPlane user;
    private ProjectileListener projectileListener;

    private ProjectileFactory projectileFactory;
    private ActorFactory actorFactory;

    private final CollisionManager collisionManager;
    private final GameplayOverlay gameplayOverlay;
    private final MenuOverlay pauseOverlay;
    private List<ActiveActorDestructible> friendlyUnits;
    private List<ActiveActorDestructible> enemyUnits;
    private List<ActiveActorDestructible> userProjectiles;
    private List<ActiveActorDestructible> enemyProjectiles;
    private double lastUpdate;
    private double lastEnemySpawnAttempt;
    private Timer timer;
    private double virtualTime;

    private int currentNumberOfEnemies;

    private boolean paused;
    private double lastPaused;

    private boolean backToMainMenu;

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

    private void initializeProjectileListener() {
        projectileListener = new ProjectileListener() {
            @Override
            public void onFire(Projectile projectile) {
                projectile.show();

                if (projectile.isUserProjectile()) {
                    userProjectiles.add(projectile);
                } else {
                    enemyProjectiles.add(projectile);
                }
            }
        };
    }

    private void initializeUnitArrays() {
        friendlyUnits = new ArrayList<>();
        enemyUnits = new ArrayList<>();
        userProjectiles = new ArrayList<>();
        enemyProjectiles = new ArrayList<>();
    }

    private void initializeFactories() {
        projectileFactory = new ProjectileFactoryImpl(root, loader);
        actorFactory = new ActorFactoryImpl(root, loader, inputManager, projectileFactory, projectileListener, elementFactory);
    }

    private void initializeState() {
        this.paused = false;
        this.lastPaused = -99999.0;

        this.currentNumberOfEnemies = 0;

        this.lastUpdate = 0.0; // mostly arbitrary time at the start
        this.virtualTime = 0.0;
        this.timer = createTimer();
        this.lastEnemySpawnAttempt = -99999.0; // set to an arbitrary negative time to simulate no enemies having spawned
    }

    private void fixCloseRequestHandling() {
        Controller.getStage().setOnCloseRequest(new EventHandler<WindowEvent>() {
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

    protected abstract void checkIfGameOver(double currentTime);

    protected abstract void spawnEnemyUnits(double currentTime);

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

    protected void removeDestroyedActors(List<ActiveActorDestructible> actors) {
        List<ActiveActorDestructible> destroyedActors =
                actors.stream().filter(ActiveActorDestructible::isDestroyed).toList();
        destroyedActors.forEach(Element::hide);
        actors.removeAll(destroyedActors);
    }

    protected void handleAllCollisions() {
        collisionManager.handleCollisions(friendlyUnits, enemyUnits);
        collisionManager.handleCollisions(userProjectiles, enemyUnits);
        collisionManager.handleCollisions(enemyProjectiles, friendlyUnits);
    }

    protected void handleEnemyPenetration() {
        for (ActiveActorDestructible enemy : enemyUnits) {
            if (enemyHasPenetratedDefenses(enemy)) {
                user.takeDamage(1);
                enemy.destroy();
            }
        }
    }

    protected void updateOverlays(double currentTime) {
        gameplayOverlay.removeHearts(user.getHealth());
    }

    private void updateKillCount() {
        for (int i = 0; i < currentNumberOfEnemies - enemyUnits.size(); i++) {
            user.incrementKillCount();
        }
    }

    protected boolean enemyHasPenetratedDefenses(ActiveActorDestructible enemy) {
        return enemy.getX() < 0;
    }

    protected void winGame() {
        gameplayOverlay.showWinImage();
        pauseOverlay.show();
        paused = true;
        backToMainMenu = true;
    }

    protected void loseGame() {
        gameplayOverlay.showGameOverImage();
        pauseOverlay.show();
        paused = true;
        backToMainMenu = true;
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

    protected int getUserKills() {
        return user.getNumberOfKills();
    }

    protected double getLastEnemySpawnAttempt() {
        return lastEnemySpawnAttempt;
    }

    protected void setLastEnemySpawnAttempt(double time) {
        lastEnemySpawnAttempt = time;
    }

    protected boolean isUserDestroyed() {
        return user.isDestroyed();
    }

    protected ActorFactory getActorFactory() {
        return actorFactory;
    }
}