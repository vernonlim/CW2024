package dev.vernonlim.cw2024game.overlays;

import dev.vernonlim.cw2024game.elements.TextBox;
import dev.vernonlim.cw2024game.configs.OverlayConfig;
import dev.vernonlim.cw2024game.screens.ScreenCode;

public class MainMenuOverlay extends MenuOverlay {
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
