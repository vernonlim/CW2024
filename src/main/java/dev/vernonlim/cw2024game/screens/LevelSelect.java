package dev.vernonlim.cw2024game.screens;

import dev.vernonlim.cw2024game.configs.ScreenConfig;

/**
 * A Level Select Screen allowing users to select one of the Levels present in the game.
 */
public class LevelSelect extends MainMenu implements Screen {
    /**
     * Constructs a Level Select Screen from the given params.
     *
     * @param config the configuration object containing the necessary data to construct the Screen
     */
    public LevelSelect(ScreenConfig config) {
        super(config);
    }

    @Override
    protected void initializeMenuOverlay() {
        setMenuOverlay(overlayFactory.createLevelSelectOverlay(userPlaneCode));
    }
}
