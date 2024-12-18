package dev.vernonlim.cw2024game.overlays;

import dev.vernonlim.cw2024game.configs.OverlayConfig;
import dev.vernonlim.cw2024game.elements.TextBox;
import dev.vernonlim.cw2024game.elements.actors.UserPlaneCode;
import dev.vernonlim.cw2024game.factories.interfaces.OverlayFactory;
import dev.vernonlim.cw2024game.screens.ScreenCode;

/**
 * An overlay displayed on the Level Select Screen.
 */
public class LevelSelectOverlay extends MenuOverlay {
    /**
     * Constructs a Level Select Overlay.
     *
     * @param config the configuration object containing the necessary data to construct the Overlay
     */
    public LevelSelectOverlay(OverlayConfig config) {
        super(config);

        UserPlaneCode currentPlane = config.getUserPlaneCode();

        OverlayFactory gridElementFactory = getGridElementFactory();
        double rightPercent = getRightPercent();
        int totalRows = getTotalRows();

        TextBox levelOne = gridElementFactory.createTextBox(
                "Level One",
                ScreenCode.LEVEL_ONE,
                currentPlane,
                rightPercent,
                totalRows
        );

        TextBox levelTwo = gridElementFactory.createTextBox(
                "Level Two",
                ScreenCode.LEVEL_TWO,
                currentPlane,
                rightPercent,
                totalRows
        );

        TextBox levelThree = gridElementFactory.createTextBox(
                "Level Three",
                ScreenCode.LEVEL_THREE,
                currentPlane,
                rightPercent,
                totalRows
        );

        TextBox levelFour = gridElementFactory.createTextBox(
                "Level Four",
                ScreenCode.LEVEL_FOUR,
                currentPlane,
                rightPercent,
                totalRows
        );

        addButton(levelOne);
        addButton(levelTwo);
        addButton(levelThree);
        addButton(levelFour);

        show();
    }
}
