package dev.vernonlim.cw2024game.screens;

import dev.vernonlim.cw2024game.configs.ScreenConfig;
import dev.vernonlim.cw2024game.overlays.MenuOverlay;

public class MainMenu extends ScreenParent implements Screen {
    private MenuOverlay menuOverlay;

    public MainMenu(ScreenConfig config) {
        super(config);

        initializeMenuOverlay();
    }

    protected void initializeMenuOverlay() {
        menuOverlay = overlayFactory.createMainMenuOverlay();
    }

    @Override
    protected void updateScene() {
        double currentTime = System.currentTimeMillis();

        menuOverlay.update(currentTime);
    }

    protected MenuOverlay getMenuOverlay() {
        return menuOverlay;
    }

    protected void setMenuOverlay(MenuOverlay menuOverlay) {
        this.menuOverlay = menuOverlay;
    }
}
