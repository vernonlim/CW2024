package dev.vernonlim.cw2024game.screens;

import dev.vernonlim.cw2024game.configs.ScreenConfig;

/**
 * The Character Select Screen, consisting of buttons allowing the player to choose a plane.
 */
public class CharacterSelect extends MainMenu implements Screen {
    /**
     * Constructs the Character Select screen.
     *
     * @param config the configuration object containing the necessary data to construct the Level
     */
    public CharacterSelect(ScreenConfig config) {
        super(config);
    }

    @Override
    protected void initializeMenuOverlay() {
        setMenuOverlay(overlayFactory.createCharacterSelectOverlay());
    }
}
