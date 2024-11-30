package dev.vernonlim.cw2024game.screens;

import dev.vernonlim.cw2024game.Controller;
import dev.vernonlim.cw2024game.assets.AssetLoader;
import dev.vernonlim.cw2024game.elements.actors.Boss;
import javafx.stage.Stage;

public class LevelTwo extends LevelParent {
    private static final String BACKGROUND_IMAGE_NAME = "background2";
    private static final int PLAYER_INITIAL_HEALTH = 5;
    private final Boss boss;

    public LevelTwo(Stage stage, Controller controller, AssetLoader loader) {
        super(stage, controller, loader, BACKGROUND_IMAGE_NAME, PLAYER_INITIAL_HEALTH);

        boss = elementFactory.createBoss();
    }

    @Override
    protected void checkIfGameOver() {
        if (user.isDestroyed()) {
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
}
