package dev.vernonlim.cw2024game.screens;

import dev.vernonlim.cw2024game.Main;
import dev.vernonlim.cw2024game.elements.actors.ActiveActorDestructible;
import dev.vernonlim.cw2024game.elements.actors.EnemyCode;
import dev.vernonlim.cw2024game.elements.configs.ScreenConfig;

public class LevelOne extends Level {
    private static final ScreenCode NEXT_LEVEL = ScreenCode.LEVEL_TWO;
    private static final int TOTAL_ENEMIES = 5;
    private static final int KILLS_TO_ADVANCE = 10;
    private static final double ENEMY_SPAWN_PROBABILITY = .20;

    public LevelOne(ScreenConfig config) {
        super(config);
    }

    @Override
    protected void checkIfGameOver(double currentTime) {
        if (isUserDestroyed()) {
            loseGame();
        } else if (userHasReachedKillTarget()) {
            goToScreen(NEXT_LEVEL, userPlaneCode);
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

    private boolean userHasReachedKillTarget() {
        return getUserKills() >= KILLS_TO_ADVANCE;
    }
}
