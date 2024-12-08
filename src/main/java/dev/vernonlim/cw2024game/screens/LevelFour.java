package dev.vernonlim.cw2024game.screens;

import dev.vernonlim.cw2024game.Main;
import dev.vernonlim.cw2024game.elements.actors.ActiveActorDestructible;
import dev.vernonlim.cw2024game.elements.actors.EnemyCode;
import dev.vernonlim.cw2024game.configs.ScreenConfig;

public class LevelFour extends CountdownLevel {
    private double lastSpawnHeight;
    private int spawnCount;

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

    private void spawnEnemyAtRandomY() {
        ActiveActorDestructible enemy = createEnemy();

        double enemyYPosition =
                Math.random() * (Main.SCREEN_HEIGHT - enemy.getHeight()) + enemy.getHalfHeight();

        enemy.setY(enemyYPosition);
        addEnemyUnit(enemy);

        lastSpawnHeight = enemyYPosition;
    }

    private void spawnEnemy(double y) {
        ActiveActorDestructible enemy = createEnemy();

        enemy.setY(y);
        addEnemyUnit(enemy);
    }

    private ActiveActorDestructible createEnemy() {
        return getActorFactory().createEnemy(
                EnemyCode.ENEMY_RED,
                Main.SCREEN_WIDTH,
                Main.SCREEN_HEIGHT / 2.0f
        );
    }
}
