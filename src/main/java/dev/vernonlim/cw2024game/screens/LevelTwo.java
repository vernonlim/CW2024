package dev.vernonlim.cw2024game.screens;

import dev.vernonlim.cw2024game.Controller;
import dev.vernonlim.cw2024game.assets.AssetLoader;
import dev.vernonlim.cw2024game.elements.actors.EnemyCode;
import dev.vernonlim.cw2024game.elements.actors.FighterPlane;
import dev.vernonlim.cw2024game.elements.actors.UserPlaneCode;
import dev.vernonlim.cw2024game.elements.configs.ScreenConfig;
import dev.vernonlim.cw2024game.managers.KeybindStore;
import javafx.stage.Stage;

public class LevelTwo extends LevelParent {
    private final FighterPlane boss;

    public LevelTwo(ScreenConfig config) {
        super(config);

        boss = actorFactory.createEnemy(EnemyCode.BOSS, 0, 0);
    }

    @Override
    protected void checkIfGameOver(double currentTime) {
        if (user.isDestroyed()) {
            loseGame();
        } else if (boss.isDestroyed()) {
            goToScreen(ScreenCode.LEVEL_THREE, userPlaneCode);
        }
    }

    @Override
    protected void spawnEnemyUnits(double currentTime) {
        if (getCurrentNumberOfEnemies() == 0) {
            addEnemyUnit(boss);
        }
    }
}
