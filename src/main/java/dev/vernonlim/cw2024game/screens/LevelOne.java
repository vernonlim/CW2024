package dev.vernonlim.cw2024game.screens;

import dev.vernonlim.cw2024game.Main;
import dev.vernonlim.cw2024game.configs.ScreenConfig;
import dev.vernonlim.cw2024game.elements.actors.ActiveActorDestructible;
import dev.vernonlim.cw2024game.elements.actors.EnemyCode;

/**
 * The First Level of the game.
 * <p>
 * This class is modified.
 * Original Code: <a href="https://github.com/kooitt/CW2024/blob/master/src/main/java/com/example/demo/LevelOne.java">GitHub</a>
 */
public class LevelOne extends Level {
    /**
     * Total enemies that should be on screen at once.
     */
    private static final int TOTAL_ENEMIES = 5;

    /**
     * The number of kills needed to advance.
     */
    private static final int KILLS_TO_ADVANCE = 10;

    /**
     * The probability an enemy will spawn every 50.0ms period.
     */
    private static final double ENEMY_SPAWN_PROBABILITY = .20;

    /**
     * Constructs an instance of Level One.
     *
     * @param config the configuration object containing the necessary data to construct the Level
     */
    public LevelOne(ScreenConfig config) {
        super(config);
    }

    @Override
    protected void checkIfGameOver(double currentTime) {
        if (isUserDestroyed()) {
            loseGame();
        } else if (userHasReachedKillTarget()) {
            goToScreen(ScreenCode.LEVEL_TWO, userPlaneCode);
        }
    }

    @Override
    protected void spawnEnemyUnits(double currentTime) {
        if (currentTime - getLastEnemySpawnAttempt() > 50.0f) {
            setLastEnemySpawnAttempt(currentTime);

            int currentNumberOfEnemies = getCurrentNumberOfEnemies();
            for (int i = 0; i < TOTAL_ENEMIES - currentNumberOfEnemies; i++) {
                if (Math.random() < ENEMY_SPAWN_PROBABILITY) {
                    spawnEnemy();
                }
            }
        }
    }

    /**
     * Spawns a Regular enemy.
     */
    private void spawnEnemy() {
        ActiveActorDestructible enemy =
                getActorFactory().createEnemy(
                        EnemyCode.ENEMY_PLANE,
                        Main.SCREEN_WIDTH,
                        Main.SCREEN_HEIGHT / 2.0
                );

        double enemyYPosition =
                Math.random() * (Main.SCREEN_HEIGHT - enemy.getHeight()) + enemy.getHalfHeight();

        enemy.setY(enemyYPosition);
        addEnemyUnit(enemy);
    }

    /**
     * Indicates whether the user has reached the target number of kills.
     *
     * @return the target number of kills
     */
    private boolean userHasReachedKillTarget() {
        return getUserKills() >= KILLS_TO_ADVANCE;
    }
}
