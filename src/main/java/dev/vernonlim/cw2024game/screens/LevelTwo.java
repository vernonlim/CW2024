package dev.vernonlim.cw2024game.screens;

import dev.vernonlim.cw2024game.elements.actors.Boss;
import dev.vernonlim.cw2024game.Controller;
import dev.vernonlim.cw2024game.overlays.LevelView;
import dev.vernonlim.cw2024game.overlays.LevelViewLevelTwo;
import javafx.scene.layout.Pane;

public class LevelTwo extends LevelParent {
    private static final String BACKGROUND_IMAGE_NAME = "/images/background2.jpg";
    private static final int PLAYER_INITIAL_HEALTH = 5;
    private final Boss boss;
    private LevelViewLevelTwo levelView;

    public LevelTwo(Controller controller) {
        super(controller, BACKGROUND_IMAGE_NAME, PLAYER_INITIAL_HEALTH);

        boss = new Boss(getRoot(), levelView);
    }

    @Override
    protected void initializeFriendlyUnits() {
        getUser().show();
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
    protected void spawnEnemyUnits(double currentTime) {
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
