package dev.vernonlim.cw2024game.screens;

import dev.vernonlim.cw2024game.Controller;
import dev.vernonlim.cw2024game.ScreenCode;
import dev.vernonlim.cw2024game.assets.AssetLoader;
import dev.vernonlim.cw2024game.elements.Element;
import dev.vernonlim.cw2024game.elements.ProjectileListener;
import dev.vernonlim.cw2024game.elements.actors.*;
import dev.vernonlim.cw2024game.factories.*;
import dev.vernonlim.cw2024game.factories.interfaces.ActorFactory;
import dev.vernonlim.cw2024game.factories.interfaces.ProjectileFactory;
import dev.vernonlim.cw2024game.managers.CollisionManager;
import dev.vernonlim.cw2024game.managers.DamageCollisionManager;
import dev.vernonlim.cw2024game.managers.KeybindStore;
import dev.vernonlim.cw2024game.overlays.GameplayOverlay;
import dev.vernonlim.cw2024game.overlays.MenuOverlay;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public abstract class LevelParent extends ScreenParent implements Screen {
    protected final UserPlane user;
    protected final ProjectileListener projectileListener;

    protected final ProjectileFactory projectileFactory;
    protected final ActorFactory actorFactory;

    protected final CollisionManager collisionManager;

    protected final UserPlaneCode userPlaneCode;

    private final List<ActiveActorDestructible> friendlyUnits;
    private final List<ActiveActorDestructible> enemyUnits;
    private final List<ActiveActorDestructible> userProjectiles;
    private final List<ActiveActorDestructible> enemyProjectiles;

    protected final GameplayOverlay gameplayOverlay;
    protected final MenuOverlay pauseOverlay;

    protected double lastUpdate;
    protected double lastEnemySpawnAttempt;
    protected Timer timer;
    protected double virtualTime;

    protected int currentNumberOfEnemies;

    protected boolean paused;
    protected double lastPaused;

    protected boolean won;

    public LevelParent(Stage stage, Controller controller, AssetLoader loader, KeybindStore keybinds, String backgroundImagePath, ScreenCode currentScreen, UserPlaneCode userPlaneCode) {
        super(controller, loader, keybinds, backgroundImagePath, currentScreen);

        // initializing handlers
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

        this.projectileFactory = new ProjectileFactoryImpl(root, loader);
        this.actorFactory = new ActorFactoryImpl(root, loader, inputManager, projectileFactory, projectileListener, elementFactory);

        this.friendlyUnits = new ArrayList<>();
        this.enemyUnits = new ArrayList<>();
        this.userProjectiles = new ArrayList<>();
        this.enemyProjectiles = new ArrayList<>();

        this.userPlaneCode = userPlaneCode;

        this.user = actorFactory.createUserPlane(userPlaneCode);
        friendlyUnits.add(user);

        // the overlay on top
        this.gameplayOverlay = overlayFactory.createGameplayOverlay(user.getHealth());
        this.pauseOverlay = overlayFactory.createPauseOverlay(new ScreenChangeHandler() {
            @Override
            public void changeScreen(ScreenCode code) {
                changeScreen(code, UserPlaneCode.REGULAR_PLANE);
            }

            @Override
            public void changeScreen(ScreenCode code, UserPlaneCode unused) {
                goToScreen(code, userPlaneCode);
            }
        }, currentScreen);

        this.paused = false;
        this.lastPaused = -99999;

        this.currentNumberOfEnemies = 0;

        this.lastUpdate = 0; // mostly arbitrary time at the start
        this.virtualTime = 0;
        this.timer = createTimer();
        this.lastEnemySpawnAttempt = -99999; // set to an arbitrary negative time to simulate no enemies having spawned


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

    protected abstract void checkIfGameOver();

    protected abstract void spawnEnemyUnits(double currentTime);

    @Override
    protected void updateScene() {
        double currentTime = virtualTime;
        double deltaTime = currentTime - lastUpdate;
        lastUpdate = currentTime;

        double realCurrentTime = System.currentTimeMillis();

        if (won) {
            pauseOverlay.update(realCurrentTime);

            return;
        }

        if (inputManager.isPausePressed() && realCurrentTime - lastPaused > 200.0f) {
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

        if (paused) {
            pauseOverlay.update(realCurrentTime);

            return;
        }

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
                user.takeDamage(1);
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
        gameplayOverlay.showWinImage();
        pauseOverlay.show();
        paused = true;
        won = true;
    }

    protected void loseGame() {
        timer.stop();
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