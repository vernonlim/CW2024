package dev.vernonlim.cw2024game.overlays;

import dev.vernonlim.cw2024game.Controller;
import dev.vernonlim.cw2024game.ScreenCode;
import dev.vernonlim.cw2024game.elements.ClickListener;
import dev.vernonlim.cw2024game.elements.TextBox;
import dev.vernonlim.cw2024game.factories.interfaces.OverlayFactory;
import dev.vernonlim.cw2024game.managers.InputManager;
import dev.vernonlim.cw2024game.screens.ScreenChangeHandler;
import javafx.scene.layout.Pane;

public class PauseOverlay extends MenuOverlay {
    public PauseOverlay(Controller controller, OverlayFactory overlayFactory, Pane root, InputManager input, ScreenChangeHandler screenChangeHandler, ScreenCode currentScreen) {
        super(controller, overlayFactory, root, input, screenChangeHandler);

        TextBox backToMenu = gridElementFactory.createTextBox("Back to Main Menu", new ClickListener() {
            @Override
            public void onClick() {
                screenChangeHandler.changeScreen(ScreenCode.MAIN_MENU);
            }
        });

        TextBox retry = gridElementFactory.createTextBox("Retry Level", new ClickListener() {
            @Override
            public void onClick() {
                screenChangeHandler.changeScreen(currentScreen);
            }
        });

        addButton(backToMenu);
        addButton(retry);
    }
}
