package dev.vernonlim.cw2024game.screens;

import dev.vernonlim.cw2024game.ScreenCode;
import dev.vernonlim.cw2024game.elements.actors.UserPlaneCode;

public interface ScreenChangeHandler {
    void changeScreen(ScreenCode code, UserPlaneCode userPlaneCode);
}
