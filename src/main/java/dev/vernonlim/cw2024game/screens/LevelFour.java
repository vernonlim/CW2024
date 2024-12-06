package dev.vernonlim.cw2024game.screens;

import dev.vernonlim.cw2024game.Controller;
import dev.vernonlim.cw2024game.Main;
import dev.vernonlim.cw2024game.assets.AssetLoader;
import dev.vernonlim.cw2024game.elements.actors.ActiveActorDestructible;
import dev.vernonlim.cw2024game.elements.actors.EnemyCode;
import dev.vernonlim.cw2024game.elements.actors.UserPlaneCode;
import dev.vernonlim.cw2024game.elements.configs.ScreenConfig;
import dev.vernonlim.cw2024game.managers.KeybindStore;
import dev.vernonlim.cw2024game.overlays.TimerOverlay;
import javafx.stage.Stage;

public class LevelFour extends LevelParent {
    protected TimerOverlay timerOverlay;
    protected final int SECONDS_REMAINING = 20;
    protected double lastSpawnHeight;
    protected int spawnCount;

    public LevelFour(ScreenConfig config) {
        super(config);

        this.timerOverlay = overlayFactory.createTimerOverlay(SECONDS_REMAINING);
        this.lastSpawnHeight = 0;
        this.spawnCount = 0;
    }

    @Override
    protected void updateOverlays(double currentTime) {
        super.updateOverlays(currentTime);

        timerOverlay.update(currentTime);
    }

    @Override
    protected void checkIfGameOver(double currentTime) {
        if (user.isDestroyed()) {
            loseGame();
        } else if (currentTime >= SECONDS_REMAINING * 1000.0f) {
            winGame();
        }
    }

    @Override
    protected void spawnEnemyUnits(double currentTime) {
        if (currentTime - lastEnemySpawnAttempt > 2000.0f) {
            lastEnemySpawnAttempt = currentTime;

            ActiveActorDestructible enemy = actorFactory.createEnemy(EnemyCode.ENEMY_RED, Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT / 2.0f);
            double enemyYPosition = Math.random() * (Main.SCREEN_HEIGHT - enemy.getHeight()) + enemy.getHalfHeight();
            enemy.setY(enemyYPosition);
            addEnemyUnit(enemy);

            lastSpawnHeight = enemyYPosition;
            spawnCount = 4;
        }

        if (spawnCount >= 1) {
            if (currentTime - lastEnemySpawnAttempt >= 200.0f) {
                lastEnemySpawnAttempt = currentTime;

                ActiveActorDestructible enemy = actorFactory.createEnemy(EnemyCode.ENEMY_RED, Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT / 2.0f);
                enemy.setY(lastSpawnHeight);
                addEnemyUnit(enemy);
                spawnCount--;
            }
        }
    }
}
