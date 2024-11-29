package dev.vernonlim.cw2024game.screens;

import dev.vernonlim.cw2024game.Main;
import dev.vernonlim.cw2024game.actors.ActiveActorDestructible;
import dev.vernonlim.cw2024game.actors.EnemyPlane;
import dev.vernonlim.cw2024game.Controller;
import dev.vernonlim.cw2024game.overlays.LevelView;

public class LevelOne extends LevelParent {
    private static final String BACKGROUND_IMAGE_NAME = "/images/background1.jpg";
    private static final String NEXT_LEVEL = "LEVEL_TWO";
    private static final int TOTAL_ENEMIES = 5;
    private static final int KILLS_TO_ADVANCE = 10;
    private static final double ENEMY_SPAWN_PROBABILITY = .20;
    private static final int PLAYER_INITIAL_HEALTH = 5;

    public LevelOne(Controller controller) {
        super(controller, BACKGROUND_IMAGE_NAME, PLAYER_INITIAL_HEALTH);
    }

    @Override
    protected void checkIfGameOver() {
        if (userIsDestroyed()) {
            loseGame();
        } else if (userHasReachedKillTarget()) {
            goToNextLevel(NEXT_LEVEL);
        }
    }

    @Override
    protected void initializeFriendlyUnits() {
        getRoot().getChildren().add(getUser());
    }

    @Override
    protected void spawnEnemyUnits(double currentTime) {
        if (currentTime - lastEnemySpawnAttempt > 50.0f) {
            lastEnemySpawnAttempt = currentTime;

            int currentNumberOfEnemies = getCurrentNumberOfEnemies();
            for (int i = 0; i < TOTAL_ENEMIES - currentNumberOfEnemies; i++) {
                if (Math.random() < ENEMY_SPAWN_PROBABILITY) {
                    double newEnemyInitialYPosition = Math.random() * getEnemyMaximumYPosition() + 54;
                    ActiveActorDestructible newEnemy = new EnemyPlane(Main.SCREEN_WIDTH, newEnemyInitialYPosition);
                    addEnemyUnit(newEnemy);
                }
            }
        }
    }

    @Override
    protected LevelView instantiateLevelView() {
        return new LevelView(getRoot(), PLAYER_INITIAL_HEALTH);
    }

    private boolean userHasReachedKillTarget() {
        return getUser().getNumberOfKills() >= KILLS_TO_ADVANCE;
    }
}
