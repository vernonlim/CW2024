package dev.vernonlim.cw2024game.levels;

import dev.vernonlim.cw2024game.Boss;
import dev.vernonlim.cw2024game.LevelView;
import dev.vernonlim.cw2024game.LevelViewLevelTwo;
import dev.vernonlim.cw2024game.controller.Controller;

public class LevelTwo extends LevelParent {
    private static final String BACKGROUND_IMAGE_NAME = "/dev/vernonlim/cw2024game/images/background2.jpg";
    private static final int PLAYER_INITIAL_HEALTH = 5;
    private final Boss boss;
    private LevelViewLevelTwo levelView;

    public LevelTwo(Controller controller, double screenHeight, double screenWidth) {
        super(controller, BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH);
        boss = new Boss();
    }

    @Override
    protected void initializeFriendlyUnits() {
        getRoot().getChildren().add(getUser());
    }

    @Override
    protected void checkIfGameOver() {
        if (userIsDestroyed()) {
            loseGame();
        } else if (boss.isDestroyed()) {
            winGame();
        }
    }

    @Override
    protected void spawnEnemyUnits(double deltaTime) {
        if (getCurrentNumberOfEnemies() == 0) {
            addEnemyUnit(boss);
        }
    }

    @Override
    protected LevelView instantiateLevelView() {
        levelView = new LevelViewLevelTwo(getRoot(), PLAYER_INITIAL_HEALTH);
        return levelView;
    }
}
