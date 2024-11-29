package dev.vernonlim.cw2024game.screens;

import dev.vernonlim.cw2024game.elements.actors.Boss;
import dev.vernonlim.cw2024game.Controller;
import dev.vernonlim.cw2024game.overlays.Overlay;
import javafx.scene.layout.Pane;

public class LevelTwo extends LevelParent {
    private static final String BACKGROUND_IMAGE_NAME = "/images/background2.jpg";
    private static final int PLAYER_INITIAL_HEALTH = 5;
    private final Boss boss;

    public LevelTwo(Controller controller) {
        super(controller, BACKGROUND_IMAGE_NAME, PLAYER_INITIAL_HEALTH);

        boss = new Boss(getRoot(), projectileListener);
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
    protected Overlay instantiateOverlay(Pane root) {
        return new Overlay(root, PLAYER_INITIAL_HEALTH);
    }
}
