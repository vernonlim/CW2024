package dev.vernonlim.cw2024game.overlays;

import dev.vernonlim.cw2024game.Controller;
import dev.vernonlim.cw2024game.elements.configs.OverlayConfig;
import dev.vernonlim.cw2024game.screens.ScreenCode;
import dev.vernonlim.cw2024game.elements.TextBox;
import dev.vernonlim.cw2024game.factories.interfaces.OverlayFactory;
import dev.vernonlim.cw2024game.managers.InputManager;
import dev.vernonlim.cw2024game.screens.ScreenChangeHandler;
import javafx.scene.layout.Pane;

public class PauseOverlay extends MenuOverlay {
    public PauseOverlay(OverlayConfig config) {
        super(config);

        ScreenCode currentScreen = config.getCurrentScreen();

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
