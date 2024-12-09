package dev.vernonlim.cw2024game.overlays;

import dev.vernonlim.cw2024game.configs.OverlayConfig;
import dev.vernonlim.cw2024game.elements.TextBox;
import dev.vernonlim.cw2024game.screens.ScreenCode;

/**
 * An overlay displayed on the Main Menu.
 */
public class MainMenuOverlay extends MenuOverlay {
    /**
     * Constructs a Main Menu Overlay.
     *
     * @param config the configuration object containing the necessary data to construct the Overlay
     */
    public MainMenuOverlay(OverlayConfig config) {
        super(config);

        TextBox startGame = getGridElementFactory().createTextBox(
                "Start Game",
                ScreenCode.CHARACTER_SELECT,
                getRightPercent(), getTotalRows()
        );

        addButton(startGame);

        show();
    }
}
