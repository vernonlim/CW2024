package dev.vernonlim.cw2024game.screens;

import dev.vernonlim.cw2024game.Controller;
import dev.vernonlim.cw2024game.assets.AssetLoader;
import dev.vernonlim.cw2024game.elements.actors.UserPlaneCode;
import dev.vernonlim.cw2024game.elements.configs.ScreenConfig;
import dev.vernonlim.cw2024game.managers.KeybindStore;
import dev.vernonlim.cw2024game.overlays.MenuOverlay;

public class MainMenu extends ScreenParent implements Screen {
    protected MenuOverlay menuOverlay;

    protected double lastUpdate;

    public MainMenu(ScreenConfig config) {
        super(config);

        initializeMenuOverlay();

        this.lastUpdate = System.currentTimeMillis();
    }

    protected void initializeMenuOverlay() {
        menuOverlay = overlayFactory.createMainMenuOverlay();
    }

    @Override
    protected void updateScene() {
        double currentTime = System.currentTimeMillis();
        double deltaTime = currentTime - lastUpdate;
        lastUpdate = currentTime;

        menuOverlay.update(currentTime);
    }
}
