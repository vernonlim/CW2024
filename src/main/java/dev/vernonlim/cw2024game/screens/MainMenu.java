package dev.vernonlim.cw2024game.screens;

import dev.vernonlim.cw2024game.Controller;
import dev.vernonlim.cw2024game.ScreenCode;
import dev.vernonlim.cw2024game.assets.AssetLoader;
import dev.vernonlim.cw2024game.elements.actors.UserPlaneCode;
import dev.vernonlim.cw2024game.managers.KeybindStore;
import dev.vernonlim.cw2024game.overlays.MenuOverlay;

public class MainMenu extends ScreenParent implements Screen {
    protected MenuOverlay menuOverlay;
    protected final ScreenChangeHandler screenChangeHandler;

    protected double lastUpdate;

    public MainMenu(Controller controller, AssetLoader loader, KeybindStore keybinds, String backgroundImagePath, ScreenCode currentScreen) {
        super(controller, loader, keybinds, backgroundImagePath, currentScreen);

        this.screenChangeHandler = new ScreenChangeHandler() {
            @Override
            public void changeScreen(ScreenCode code) {
                changeScreen(code, UserPlaneCode.REGULAR_PLANE);
            }

            @Override
            public void changeScreen(ScreenCode code, UserPlaneCode userPlaneCode) {
                goToScreen(code, userPlaneCode);
            }
        };

        initializeMenuOverlay();

        this.lastUpdate = System.currentTimeMillis();
    }

    protected void initializeMenuOverlay() {
        menuOverlay = overlayFactory.createMainMenuOverlay(screenChangeHandler);
    }

    @Override
    protected void updateScene() {
        double currentTime = System.currentTimeMillis();
        double deltaTime = currentTime - lastUpdate;
        lastUpdate = currentTime;

        menuOverlay.update(currentTime);
    }
}
