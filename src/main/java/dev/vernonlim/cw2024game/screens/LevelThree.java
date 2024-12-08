package dev.vernonlim.cw2024game.screens;

import dev.vernonlim.cw2024game.Main;
import dev.vernonlim.cw2024game.elements.actors.ActiveActorDestructible;
import dev.vernonlim.cw2024game.elements.actors.EnemyCode;
import dev.vernonlim.cw2024game.configs.ScreenConfig;

public class LevelThree extends CountdownLevel {
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
