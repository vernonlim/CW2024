package dev.vernonlim.cw2024game.screens;

import dev.vernonlim.cw2024game.Main;
import dev.vernonlim.cw2024game.elements.actors.ActiveActorDestructible;
import dev.vernonlim.cw2024game.elements.actors.EnemyCode;
import dev.vernonlim.cw2024game.configs.ScreenConfig;

/**
 * The Third Level of the game.
 */
public class LevelThree extends CountdownLevel {
    /**
     * Constructs an instance of Level Three.
     *
     * @param config the configuration object containing the necessary data to construct the Level
     */
    public LevelThree(ScreenConfig config) {
        super(config);
    }

    @Override
    protected void checkIfGameOver(double currentTime) {
        if (isUserDestroyed()) {
            loseGame();
        } else if (currentTime >= getCountdownTime() * 1000.0f) {
            goToScreen(ScreenCode.LEVEL_FOUR, userPlaneCode);
        }
    }

    @Override
    protected void spawnEnemyUnits(double currentTime) {
        if (currentTime - getLastEnemySpawnAttempt() > 2000.0f) {
            setLastEnemySpawnAttempt(currentTime);

            spawnEnemy(true);
            spawnEnemy(false);
        }
    }

    /**
     * Spawns a Blue enemy in one of two given positions
     *
     * @param top if true, the enemy will spawn at the top. the enemy will spawn at the bottom otherwise
     */
    private void spawnEnemy(boolean top) {
        ActiveActorDestructible enemy =
                getActorFactory().createEnemy(
                        EnemyCode.ENEMY_BLUE,
                        Main.SCREEN_WIDTH,
                        Main.SCREEN_HEIGHT / 2.0f);

        double enemyYPosition = top ?
                enemy.getHeight() + 50
                : Main.SCREEN_HEIGHT - enemy.getHeight() - 50;

        enemy.setY(enemyYPosition);
        addEnemyUnit(enemy);
    }
}
