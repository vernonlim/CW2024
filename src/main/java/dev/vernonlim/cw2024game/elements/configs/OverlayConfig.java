package dev.vernonlim.cw2024game.elements.configs;

import dev.vernonlim.cw2024game.elements.actors.UserPlaneCode;
import dev.vernonlim.cw2024game.factories.interfaces.OverlayFactory;
import dev.vernonlim.cw2024game.managers.InputManager;
import dev.vernonlim.cw2024game.screens.ScreenChangeHandler;
import dev.vernonlim.cw2024game.screens.ScreenCode;
import javafx.scene.layout.Pane;

public class OverlayConfig extends ElementConfig {
    private OverlayFactory overlayFactory;
    private InputManager input;
    private ScreenChangeHandler screenChangeHandler;
    private ScreenCode currentScreen;
    private UserPlaneCode userPlaneCode;

    public OverlayConfig(Pane root) {
        super(root);
    }

    public OverlayConfig(Pane root, OverlayFactory overlayFactory) {
        this(root);

        this.overlayFactory = overlayFactory;
    }

    public OverlayConfig(Pane root, OverlayFactory overlayFactory, InputManager input, ScreenChangeHandler screenChangeHandler) {
        this(root, overlayFactory);

        this.input = input;
        this.screenChangeHandler = screenChangeHandler;
    }

    public OverlayFactory getOverlayFactory() {
        return overlayFactory;
    }

    public void setOverlayFactory(OverlayFactory overlayFactory) {
        this.overlayFactory = overlayFactory;
    }

    public InputManager getInput() {
        return input;
    }

    public void setInput(InputManager input) {
        this.input = input;
    }

    public ScreenChangeHandler getScreenChangeHandler() {
        return screenChangeHandler;
    }

    public void setScreenChangeHandler(ScreenChangeHandler screenChangeHandler) {
        this.screenChangeHandler = screenChangeHandler;
    }

    public ScreenCode getCurrentScreen() {
        return currentScreen;
    }

    public void setCurrentScreen(ScreenCode currentScreen) {
        this.currentScreen = currentScreen;
    }

    public UserPlaneCode getUserPlaneCode() {
        return userPlaneCode;
    }

    public void setUserPlaneCode(UserPlaneCode userPlaneCode) {
        this.userPlaneCode = userPlaneCode;
    }
}
