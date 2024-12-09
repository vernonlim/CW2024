package dev.vernonlim.cw2024game.overlays;

import dev.vernonlim.cw2024game.elements.TextBox;
import dev.vernonlim.cw2024game.configs.OverlayConfig;
import dev.vernonlim.cw2024game.factories.interfaces.OverlayFactory;
import dev.vernonlim.cw2024game.screens.ScreenCode;

/**
 * An overlay displayed when paused.
 */
public class PauseOverlay extends MenuOverlay {
    /**
     * Constructs a Pause Overlay.
     *
     * @param config the configuration object containing the necessary data to construct the Overlay
     */
    public PauseOverlay(OverlayConfig config) {
        super(config);

        ScreenCode currentScreen = config.getCurrentScreen();

        OverlayFactory gridElementFactory = getGridElementFactory();
        double rightPercent = getRightPercent();
        int totalRows = getTotalRows();

        TextBox backToMenu = gridElementFactory.createTextBox(
                "Back to Main Menu",
                ScreenCode.MAIN_MENU,
                rightPercent,
                totalRows
        );

        TextBox retry = gridElementFactory.createTextBox(
                "Retry Level",
                currentScreen,
                rightPercent,
                totalRows
        );

        addButton(backToMenu);
        addButton(retry);
    }
}
