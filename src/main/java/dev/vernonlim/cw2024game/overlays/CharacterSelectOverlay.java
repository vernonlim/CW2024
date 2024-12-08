package dev.vernonlim.cw2024game.overlays;

import dev.vernonlim.cw2024game.elements.TextBox;
import dev.vernonlim.cw2024game.elements.actors.UserPlaneCode;
import dev.vernonlim.cw2024game.configs.OverlayConfig;
import dev.vernonlim.cw2024game.factories.interfaces.OverlayFactory;
import dev.vernonlim.cw2024game.screens.ScreenCode;

/**
 * An overlay displayed on the Character Select Screen.
 */
public class CharacterSelectOverlay extends MenuOverlay {
    /**
     * Constructs a Character Select Overlay.
     *
     * @param config the configuration object containing the necessary data to construct the Overlay
     */
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
