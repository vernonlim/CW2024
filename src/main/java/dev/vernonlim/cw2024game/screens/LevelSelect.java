package dev.vernonlim.cw2024game.screens;

import dev.vernonlim.cw2024game.configs.ScreenConfig;

public class LevelSelect extends MainMenu implements Screen {
    public LevelSelect(ScreenConfig config) {
        super(config);
    }

    @Override
    protected void initializeMenuOverlay() {
        setMenuOverlay(overlayFactory.createLevelSelectOverlay(userPlaneCode));
    }
}
