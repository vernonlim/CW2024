package dev.vernonlim.cw2024game.screens;

import dev.vernonlim.cw2024game.Controller;
import dev.vernonlim.cw2024game.ScreenCode;
import dev.vernonlim.cw2024game.assets.AssetLoader;
import dev.vernonlim.cw2024game.managers.KeybindStore;
import dev.vernonlim.cw2024game.overlays.MenuOverlay;
import javafx.application.Platform;

public class MainMenu extends ScreenParent implements Screen {
    protected final MenuOverlay menuOverlay;

    protected double lastUpdate;

    public MainMenu(Controller controller, AssetLoader loader, KeybindStore keybinds, String backgroundImagePath, ScreenCode currentScreen) {
        super(controller, loader, keybinds, backgroundImagePath, currentScreen);

        this.menuOverlay = overlayFactory.createMenuOverlay(new ScreenChangeHandler() {
            @Override
            public void changeScreen(ScreenCode code) {
                goToScreen(code);
            }
        });

        this.lastUpdate = System.currentTimeMillis();
    }

    @Override
    protected void updateScene() {
        double currentTime = System.currentTimeMillis();
        double deltaTime = currentTime - lastUpdate;
        lastUpdate = currentTime;

        menuOverlay.update(currentTime);
    }
}
