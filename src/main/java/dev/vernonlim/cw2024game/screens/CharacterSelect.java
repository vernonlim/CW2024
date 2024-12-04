package dev.vernonlim.cw2024game.screens;

import dev.vernonlim.cw2024game.Controller;
import dev.vernonlim.cw2024game.ScreenCode;
import dev.vernonlim.cw2024game.assets.AssetLoader;
import dev.vernonlim.cw2024game.elements.actors.UserPlaneCode;
import dev.vernonlim.cw2024game.managers.KeybindStore;
import dev.vernonlim.cw2024game.overlays.MenuOverlay;

public class CharacterSelect extends MainMenu implements Screen {
    public CharacterSelect(Controller controller, AssetLoader loader, KeybindStore keybinds, String backgroundImagePath, ScreenCode currentScreen) {
        super(controller, loader, keybinds, backgroundImagePath, currentScreen);
    }

    @Override
    protected void initializeMenuOverlay() {
        menuOverlay = overlayFactory.createCharacterSelectOverlay();
    }
}
