package dev.vernonlim.cw2024game.screens;

import dev.vernonlim.cw2024game.elements.configs.ScreenConfig;

public class CharacterSelect extends MainMenu implements Screen {
    public CharacterSelect(ScreenConfig config) {
        super(config);
    }

    @Override
    protected void initializeMenuOverlay() {
        setMenuOverlay(overlayFactory.createCharacterSelectOverlay());
    }
}
