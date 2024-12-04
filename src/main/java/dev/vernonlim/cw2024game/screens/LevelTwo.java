package dev.vernonlim.cw2024game.screens;

import dev.vernonlim.cw2024game.Controller;
import dev.vernonlim.cw2024game.ScreenCode;
import dev.vernonlim.cw2024game.assets.AssetLoader;
import dev.vernonlim.cw2024game.elements.actors.Boss;
import dev.vernonlim.cw2024game.elements.actors.EnemyCode;
import dev.vernonlim.cw2024game.elements.actors.FighterPlane;
import dev.vernonlim.cw2024game.elements.actors.UserPlaneCode;
import dev.vernonlim.cw2024game.managers.KeybindStore;
import javafx.stage.Stage;

public class LevelTwo extends LevelParent {
    private final FighterPlane boss;

    public LevelTwo(Stage stage, Controller controller, AssetLoader loader, KeybindStore keybinds, String backgroundImageName, ScreenCode currentScreen, UserPlaneCode userPlaneCode) {
        super(stage, controller, loader, keybinds, backgroundImageName, currentScreen, userPlaneCode);

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
