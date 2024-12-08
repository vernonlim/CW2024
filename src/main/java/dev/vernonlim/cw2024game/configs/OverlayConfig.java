package dev.vernonlim.cw2024game.configs;

import dev.vernonlim.cw2024game.elements.actors.UserPlaneCode;
import dev.vernonlim.cw2024game.factories.interfaces.OverlayFactory;
import dev.vernonlim.cw2024game.managers.InputManager;
import dev.vernonlim.cw2024game.screens.ScreenChangeHandler;
import dev.vernonlim.cw2024game.screens.ScreenCode;
import javafx.scene.layout.Pane;

/**
 * A data class used for streamlining Overlay creation
 */
public class OverlayConfig extends ElementConfig {
    /**
     * The OverlayFactory for the Overlay.
     */
    private OverlayFactory overlayFactory;

    /**
     * The InputManager for the Overlay.
     */
    private InputManager input;

    /**
     * The ScreenChangeHandler for the Overlay.
     */
    private ScreenChangeHandler screenChangeHandler;

    /**
     * The current ScreenCode.
     */
    private ScreenCode currentScreen;

    /**
     * The current UserPlaneCode.
     */
    private UserPlaneCode userPlaneCode;

    /**
     * Constructs an OverlayConfig with the given params.
     *
     * @param root the root Pane on which the Overlay is based on
     */
    public OverlayConfig(Pane root) {
        super(root);
    }

    /**
     * Constructs an OverlayConfig with the given params.
     *
     * @param root the root Pane on which the Overlay is based
     * @param overlayFactory the OverlayFactory used to produce nested elements
     */
    public OverlayConfig(Pane root, OverlayFactory overlayFactory) {
        this(root);
        this.overlayFactory = overlayFactory;
    }

    /**
     * Constructs an OverlayConfig with the given params.
     *
     * @param root the root Pane on which the Overlay is based on
     * @param overlayFactory the OverlayFactory used to produce nested elements
     * @param input the InputManager used to handle user input
     * @param screenChangeHandler the ScreenChangeHandler used to trigger screen changes
     */
    public OverlayConfig(Pane root, OverlayFactory overlayFactory, InputManager input, ScreenChangeHandler screenChangeHandler) {
        this(root, overlayFactory);
        this.input = input;
        this.screenChangeHandler = screenChangeHandler;
    }

    /**
     * Gets the OverlayFactory.
     * 
     * @return the OverlayFactory
     */
    public OverlayFactory getOverlayFactory() {
        return overlayFactory;
    }

    /**
     * Sets the OverlayFactory.
     * 
     * @param overlayFactory the OverlayFactory to set
     */
    public void setOverlayFactory(OverlayFactory overlayFactory) {
        this.overlayFactory = overlayFactory;
    }

    /**
     * Gets the InputManager.
     * 
     * @return the InputManager
     */
    public InputManager getInput() {
        return input;
    }

    /**
     * Sets the InputManager.
     * 
     * @param input the InputManager to set
     */
    public void setInput(InputManager input) {
        this.input = input;
    }

    /**
     * Gets the ScreenChangeHandler.
     * 
     * @return the ScreenChangeHandler
     */
    public ScreenChangeHandler getScreenChangeHandler() {
        return screenChangeHandler;
    }

    /**
     * Sets the ScreenChangeHandler.
     * 
     * @param screenChangeHandler the ScreenChangeHandler to set
     */
    public void setScreenChangeHandler(ScreenChangeHandler screenChangeHandler) {
        this.screenChangeHandler = screenChangeHandler;
    }

    /**
     * Gets the current ScreenCode.
     * 
     * @return the current ScreenCode
     */
    public ScreenCode getCurrentScreen() {
        return currentScreen;
    }

    /**
     * Sets the current ScreenCode.
     * 
     * @param currentScreen the current ScreenCode to set
     */
    public void setCurrentScreen(ScreenCode currentScreen) {
        this.currentScreen = currentScreen;
    }

    /**
     * Gets the UserPlaneCode.
     * 
     * @return the UserPlaneCode
     */
    public UserPlaneCode getUserPlaneCode() {
        return userPlaneCode;
    }

    /**
     * Sets the UserPlaneCode.
     * 
     * @param userPlaneCode the UserPlaneCode to set
     */
    public void setUserPlaneCode(UserPlaneCode userPlaneCode) {
        this.userPlaneCode = userPlaneCode;
    }
}
