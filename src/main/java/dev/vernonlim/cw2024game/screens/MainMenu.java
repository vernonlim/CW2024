package dev.vernonlim.cw2024game.screens;

import dev.vernonlim.cw2024game.configs.ScreenConfig;
import dev.vernonlim.cw2024game.overlays.MenuOverlay;

/**
 * The Main Menu Screen, allowing the player to start the game.
 */
public class MainMenu extends ScreenParent implements Screen {
    /**
     * The overlay drawn on the scene handling most of the logic/interactivity.
     */
    private MenuOverlay menuOverlay;

    /**
     * Constructs a Main Menu.
     *
     * @param config the configuration object containing the necessary data to construct the Screen
     */
    public MainMenu(ScreenConfig config) {
        super(config);

        initializeMenuOverlay();
    }

    /**
     * Initializes the Menu Overlay.
     */
    protected void initializeMenuOverlay() {
        menuOverlay = overlayFactory.createMainMenuOverlay();
    }

    @Override
    protected void updateScene() {
        double currentTime = System.currentTimeMillis();

        menuOverlay.update(currentTime);
    }

    /**
     * Gets the MenuOverlay associated with this Screen
     *
     *
     * @return the MenuOverlay associated with this Screen
     */
    protected MenuOverlay getMenuOverlay() {
        return menuOverlay;
    }

    /**
     * Sets the MenuOverlay associated with this Screen.
     *
     * @param menuOverlay the menu overlay to be set
     */
    protected void setMenuOverlay(MenuOverlay menuOverlay) {
        this.menuOverlay = menuOverlay;
    }
}
