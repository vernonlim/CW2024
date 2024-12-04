package dev.vernonlim.cw2024game.overlays;

import dev.vernonlim.cw2024game.Controller;
import dev.vernonlim.cw2024game.screens.ScreenCode;
import dev.vernonlim.cw2024game.elements.TextBox;
import dev.vernonlim.cw2024game.elements.actors.UserPlaneCode;
import dev.vernonlim.cw2024game.factories.interfaces.OverlayFactory;
import dev.vernonlim.cw2024game.managers.InputManager;
import dev.vernonlim.cw2024game.screens.ScreenChangeHandler;
import javafx.scene.layout.Pane;

public class LevelSelectOverlay extends MenuOverlay {
    public LevelSelectOverlay(Controller controller, OverlayFactory overlayFactory, Pane root, InputManager input, ScreenChangeHandler screenChangeHandler, UserPlaneCode userPlaneCode) {
        super(controller, overlayFactory, root, input, screenChangeHandler);

        TextBox levelOne = gridElementFactory.createTextBox("Level One", ScreenCode.LEVEL_ONE, userPlaneCode, rightPercent, totalRows);

        TextBox levelTwo = gridElementFactory.createTextBox("Level Two", ScreenCode.LEVEL_TWO, userPlaneCode, rightPercent, totalRows);

        TextBox levelThree = gridElementFactory.createTextBox("Level Three", ScreenCode.LEVEL_THREE, userPlaneCode, rightPercent, totalRows);

        TextBox levelFour = gridElementFactory.createTextBox("Level Four", ScreenCode.LEVEL_FOUR, userPlaneCode, rightPercent, totalRows);

        addButton(levelOne);
        addButton(levelTwo);
        addButton(levelThree);
        addButton(levelFour);

        show();
    }
}
