package dev.vernonlim.cw2024game.factories.interfaces;

import dev.vernonlim.cw2024game.ScreenList;
import dev.vernonlim.cw2024game.screens.Screen;

public interface ScreenFactory {
    Screen createScreen(ScreenList screen);
}
