package dev.vernonlim.cw2024game.factories.interfaces;

import dev.vernonlim.cw2024game.ScreenCode;
import dev.vernonlim.cw2024game.elements.actors.UserPlaneCode;
import dev.vernonlim.cw2024game.screens.Screen;

public interface ScreenFactory {
    Screen createScreen(ScreenCode screen, UserPlaneCode code);
}
