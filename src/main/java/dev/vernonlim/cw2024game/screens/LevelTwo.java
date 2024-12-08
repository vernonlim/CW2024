package dev.vernonlim.cw2024game.screens;

import dev.vernonlim.cw2024game.elements.actors.EnemyCode;
import dev.vernonlim.cw2024game.elements.actors.FighterPlane;
import dev.vernonlim.cw2024game.configs.ScreenConfig;

public class LevelTwo extends Level {
    private final FighterPlane boss;

    public LevelTwo(ScreenConfig config) {
        super(config);

        boss = getActorFactory().createEnemy(EnemyCode.BOSS, 0, 0);
    }

    @Override
    protected void checkIfGameOver(double currentTime) {
        if (isUserDestroyed()) {
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
