package dev.vernonlim.cw2024game.screens;

import dev.vernonlim.cw2024game.Controller;
import dev.vernonlim.cw2024game.assets.AssetLoader;
import dev.vernonlim.cw2024game.elements.actors.Boss;
import dev.vernonlim.cw2024game.elements.actors.FighterPlane;
import dev.vernonlim.cw2024game.managers.KeybindStore;
import javafx.stage.Stage;

public class LevelTwo extends LevelParent {
    private final FighterPlane boss;

    public LevelTwo(Stage stage, Controller controller, AssetLoader loader, KeybindStore keybinds, String backgroundImageName, int playerInitialHealth) {
        super(stage, controller, loader, keybinds, backgroundImageName, playerInitialHealth);

        boss = actorFactory.createBoss();
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
