package dev.vernonlim.cw2024game.screens;

import dev.vernonlim.cw2024game.elements.actors.UserPlaneCode;

/**
 * An interface representing a Handler for changing screens.
 */
public interface ScreenChangeHandler {
    /**
     * Loads a screen with a specific UserPlane.
     *
     * @param screenCode the code of the screen to load
     * @param userPlaneCode the code of the user's plane
     */
    void changeScreen(ScreenCode screenCode, UserPlaneCode userPlaneCode);
}
