package dev.vernonlim.cw2024game.screens;

import dev.vernonlim.cw2024game.Controller;
import dev.vernonlim.cw2024game.assets.AssetLoader;
import dev.vernonlim.cw2024game.elements.actors.EnemyCode;
import dev.vernonlim.cw2024game.elements.actors.FighterPlane;
import dev.vernonlim.cw2024game.elements.actors.UserPlaneCode;
import dev.vernonlim.cw2024game.managers.KeybindStore;
import javafx.stage.Stage;

public class LevelFive extends LevelParent {
    private final FighterPlane boss;

    public LevelFive(Stage stage, Controller controller, AssetLoader loader, KeybindStore keybinds, String backgroundImageName, ScreenCode currentScreen, UserPlaneCode userPlaneCode) {
        super(stage, controller, loader, keybinds, backgroundImageName, currentScreen, userPlaneCode);

        boss = actorFactory.createEnemy(EnemyCode.FINAL_BOSS, 0, 0);
    }

    @Override
    protected void checkIfGameOver(double currentTime) {

    }

    @Override
    protected void spawnEnemyUnits(double currentTime) {

    }
}
