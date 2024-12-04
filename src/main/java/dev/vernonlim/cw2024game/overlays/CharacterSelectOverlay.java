package dev.vernonlim.cw2024game.overlays;

import dev.vernonlim.cw2024game.Controller;
import dev.vernonlim.cw2024game.ScreenCode;
import dev.vernonlim.cw2024game.elements.ClickListener;
import dev.vernonlim.cw2024game.elements.TextBox;
import dev.vernonlim.cw2024game.elements.actors.UserPlaneCode;
import dev.vernonlim.cw2024game.factories.interfaces.OverlayFactory;
import dev.vernonlim.cw2024game.managers.InputManager;
import dev.vernonlim.cw2024game.screens.ScreenChangeHandler;
import javafx.scene.layout.Pane;

public class CharacterSelectOverlay extends MenuOverlay {
    public CharacterSelectOverlay(Controller controller, OverlayFactory overlayFactory, Pane root, InputManager input, ScreenChangeHandler screenChangeHandler) {
        super(controller, overlayFactory, root, input, screenChangeHandler);

        TextBox character1 = gridElementFactory.createTextBox("Regular Plane", ScreenCode.LEVEL_ONE, UserPlaneCode.REGULAR_PLANE, rightPercent, totalRows);

        TextBox character2 = gridElementFactory.createTextBox("Green Plane", ScreenCode.LEVEL_ONE, UserPlaneCode.GREEN_PLANE, rightPercent, totalRows);

        addButton(character1);
        addButton(character2);

        show();
    }
}
