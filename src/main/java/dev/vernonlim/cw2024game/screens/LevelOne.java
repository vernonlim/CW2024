package dev.vernonlim.cw2024game.screens;

import dev.vernonlim.cw2024game.Controller;
import dev.vernonlim.cw2024game.ScreenList;
import dev.vernonlim.cw2024game.Main;
import dev.vernonlim.cw2024game.assets.AssetLoader;
import dev.vernonlim.cw2024game.elements.actors.ActiveActorDestructible;
import dev.vernonlim.cw2024game.managers.KeybindStore;
import javafx.stage.Stage;

public class LevelOne extends LevelParent {
    private static final ScreenList NEXT_LEVEL = ScreenList.TWO;
    private static final int TOTAL_ENEMIES = 5;
    private static final int KILLS_TO_ADVANCE = 10;
    private static final double ENEMY_SPAWN_PROBABILITY = .20;
    private static final int PLAYER_INITIAL_HEALTH = 5;

    public LevelOne(Stage stage, Controller controller, AssetLoader loader, KeybindStore keybinds, String backgroundImageName, int playerInitialHealth) {
        super(stage, controller, loader, keybinds, backgroundImageName, playerInitialHealth);
    }

    @Override
    protected void checkIfGameOver() {
        if (user.isDestroyed()) {
            loseGame();
        } else if (userHasReachedKillTarget()) {
            goToNextLevel(NEXT_LEVEL);
        }
    }

    @Override
    protected void spawnEnemyUnits(double currentTime) {
        if (currentTime - lastEnemySpawnAttempt > 50.0f) {
            lastEnemySpawnAttempt = currentTime;

            int currentNumberOfEnemies = getCurrentNumberOfEnemies();
            for (int i = 0; i < TOTAL_ENEMIES - currentNumberOfEnemies; i++) {
                if (Math.random() < ENEMY_SPAWN_PROBABILITY) {
                    ActiveActorDestructible enemy = actorFactory.createEnemyPlane(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT / 2.0f);
                    double enemyYPosition = Math.random() * (Main.SCREEN_HEIGHT - enemy.getHeight()) + enemy.getHalfHeight();
                    enemy.setY(enemyYPosition);
                    addEnemyUnit(enemy);
                }
            }
        }
    }

    private boolean userHasReachedKillTarget() {
        return user.getNumberOfKills() >= KILLS_TO_ADVANCE;
    }
}
