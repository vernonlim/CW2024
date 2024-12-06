package dev.vernonlim.cw2024game.overlays;

import dev.vernonlim.cw2024game.Controller;
import dev.vernonlim.cw2024game.elements.configs.OverlayConfig;
import dev.vernonlim.cw2024game.screens.ScreenCode;
import dev.vernonlim.cw2024game.elements.TextBox;
import dev.vernonlim.cw2024game.elements.actors.UserPlaneCode;
import dev.vernonlim.cw2024game.factories.interfaces.OverlayFactory;
import dev.vernonlim.cw2024game.managers.InputManager;
import dev.vernonlim.cw2024game.screens.ScreenChangeHandler;
import javafx.scene.layout.Pane;

public class CharacterSelectOverlay extends MenuOverlay {
    public CharacterSelectOverlay(OverlayConfig config) {
        super(config);

        OverlayFactory gridElementFactory = getGridElementFactory();
        double rightPercent = getRightPercent();
        int totalRows = getTotalRows();

        TextBox character1 = gridElementFactory.createTextBox(
                "Regular Plane",
                ScreenCode.LEVEL_SELECT,
                UserPlaneCode.REGULAR_PLANE,
                rightPercent,
                totalRows
        );

        TextBox character2 = gridElementFactory.createTextBox(
                "Green Plane",
                ScreenCode.LEVEL_SELECT,
                UserPlaneCode.GREEN_PLANE,
                rightPercent,
                totalRows
        );

        addButton(character1);
        addButton(character2);

        show();
    }
}
