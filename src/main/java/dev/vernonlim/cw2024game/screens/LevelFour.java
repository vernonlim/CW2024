package dev.vernonlim.cw2024game.screens;

import dev.vernonlim.cw2024game.Main;
import dev.vernonlim.cw2024game.elements.actors.ActiveActorDestructible;
import dev.vernonlim.cw2024game.elements.actors.EnemyCode;
import dev.vernonlim.cw2024game.configs.ScreenConfig;

/**
 * The Fourth Level of the game.
 */
public class LevelFour extends CountdownLevel {
    /**
     * The last height an enemy spawned at.
     */
    private double lastSpawnHeight;

    /**
     * The number of enemies spawned this enemy spawn cycle.
     */
    private int spawnCount;

    /**
     * Constructs an instance of Level Four.
     *
     * @param config the configuration object containing the necessary data to construct the Level
     */
    public LevelFour(ScreenConfig config) {
        super(config);

        this.lastSpawnHeight = 0;
        this.spawnCount = 0;
    }

    @Override
    protected void checkIfGameOver(double currentTime) {
        if (isUserDestroyed()) {
            loseGame();
        } else if (currentTime >= getCountdownTime() * 1000.0f) {
            winGame();
        }
    }

    /**
     * Every 2000ms, spawns an enemy and then starts an enemy spawning cycle.
     * <p>
     * Each spawning cycle, 4 more enemies will spawn in the same location.
     *
     * @param currentTime the current time of the virtual timer
     */
    @Override
    protected void spawnEnemyUnits(double currentTime) {
        if (currentTime - getLastEnemySpawnAttempt() > 2000.0f) {
            setLastEnemySpawnAttempt(currentTime);

            spawnEnemyAtRandomY();
            spawnCount = 4;
        }

        if (spawnCount >= 1) {
            if (currentTime - getLastEnemySpawnAttempt() >= 200.0f) {
                setLastEnemySpawnAttempt(currentTime);

                spawnEnemy(lastSpawnHeight);
                spawnCount--;
            }
        }
    }

    /**
     * Spawns an enemy at a random Y coordinate.
     */
    private void spawnEnemyAtRandomY() {
        ActiveActorDestructible enemy = createEnemy();

        double enemyYPosition =
                Math.random() * (Main.SCREEN_HEIGHT - enemy.getHeight()) + enemy.getHalfHeight();

        enemy.setY(enemyYPosition);
        addEnemyUnit(enemy);

        lastSpawnHeight = enemyYPosition;
    }

    /**
     * Spawns an enemy at the given Y coordinate.
     * @param y the Y coordinate
     */
    private void spawnEnemy(double y) {
        ActiveActorDestructible enemy = createEnemy();

        enemy.setY(y);
        addEnemyUnit(enemy);
    }

    /**
     * Constructs a Red enemy
     *
     * @return a Red enemy
     */
    private ActiveActorDestructible createEnemy() {
        return getActorFactory().createEnemy(
                EnemyCode.ENEMY_RED,
                Main.SCREEN_WIDTH,
                Main.SCREEN_HEIGHT / 2.0f
        );
    }
}
