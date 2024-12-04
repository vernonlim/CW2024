package dev.vernonlim.cw2024game.screens;

import dev.vernonlim.cw2024game.Controller;
import dev.vernonlim.cw2024game.assets.AssetLoader;
import dev.vernonlim.cw2024game.elements.actors.UserPlaneCode;
import dev.vernonlim.cw2024game.managers.KeybindStore;

public class CharacterSelect extends MainMenu implements Screen {
    public CharacterSelect(Controller controller, AssetLoader loader, KeybindStore keybinds, String backgroundImagePath, ScreenCode currentScreen, UserPlaneCode userPlaneCode) {
        super(controller, loader, keybinds, backgroundImagePath, currentScreen, userPlaneCode);
    }

    @Override
    protected void initializeMenuOverlay() {
        menuOverlay = overlayFactory.createCharacterSelectOverlay();
    }
}
