package dev.vernonlim.cw2024game.screens;

import dev.vernonlim.cw2024game.Controller;
import dev.vernonlim.cw2024game.Main;
import dev.vernonlim.cw2024game.assets.AssetLoader;
import dev.vernonlim.cw2024game.elements.actors.ActiveActorDestructible;
import dev.vernonlim.cw2024game.elements.actors.EnemyCode;
import dev.vernonlim.cw2024game.elements.actors.UserPlaneCode;
import dev.vernonlim.cw2024game.managers.KeybindStore;
import dev.vernonlim.cw2024game.overlays.TimerOverlay;
import javafx.stage.Stage;

public class LevelThree extends LevelParent {
    protected TimerOverlay timerOverlay;
    protected final int SECONDS_REMAINING = 20;

    public LevelThree(Stage stage, Controller controller, AssetLoader loader, KeybindStore keybinds, String backgroundImageName, ScreenCode currentScreen, UserPlaneCode userPlaneCode) {
        super(stage, controller, loader, keybinds, backgroundImageName, currentScreen, userPlaneCode);

        this.timerOverlay = overlayFactory.createTimerOverlay(SECONDS_REMAINING);
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
            goToScreen(ScreenCode.LEVEL_FOUR, userPlaneCode);
        }
    }

    @Override
    protected void spawnEnemyUnits(double currentTime) {
        if (currentTime - lastEnemySpawnAttempt > 2000.0f) {
            lastEnemySpawnAttempt = currentTime;

            ActiveActorDestructible enemy1 = actorFactory.createEnemy(EnemyCode.ENEMY_BLUE, Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT / 2.0f);
            double enemyYPosition = enemy1.getHeight() + 50;
            enemy1.setY(enemyYPosition);
            addEnemyUnit(enemy1);

            ActiveActorDestructible enemy2 = actorFactory.createEnemy(EnemyCode.ENEMY_BLUE, Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT / 2.0f);
            double enemyYPosition2 = Main.SCREEN_HEIGHT - enemy2.getHeight() - 50;
            enemy2.setY(enemyYPosition2);
            addEnemyUnit(enemy2);
        }
    }
}
