package dev.vernonlim.cw2024game.factories.interfaces;

import dev.vernonlim.cw2024game.screens.ScreenCode;
import dev.vernonlim.cw2024game.elements.actors.UserPlaneCode;
import dev.vernonlim.cw2024game.screens.Screen;

/**
 * An interface defining a Factory for creating Screens.
 */
public interface ScreenFactory {
    /**
     * Creates a screen from a given ScreenCode and UserPlaneCode
     *
     * @param screen the ScreenCode
     * @param code the UserPlaneCode
     * @return the created Screen
     */
    Screen createScreen(ScreenCode screen, UserPlaneCode code);
}
