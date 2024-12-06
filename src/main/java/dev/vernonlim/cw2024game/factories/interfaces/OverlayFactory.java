package dev.vernonlim.cw2024game.factories.interfaces;

import dev.vernonlim.cw2024game.overlays.CharacterSelectOverlay;
import dev.vernonlim.cw2024game.screens.ScreenCode;
import dev.vernonlim.cw2024game.elements.*;
import dev.vernonlim.cw2024game.elements.actors.UserPlaneCode;
import dev.vernonlim.cw2024game.overlays.GameplayOverlay;
import dev.vernonlim.cw2024game.overlays.MenuOverlay;
import dev.vernonlim.cw2024game.overlays.TimerOverlay;
import javafx.scene.layout.Pane;

public interface OverlayFactory {
    OverlayFactory withNewRoot(Pane newRoot);
    Element createGameOverImage(double xPosition, double yPosition);
    Element createWinImage(double xPosition, double yPosition);
    Element createHeart();
    HeartDisplay createHeartDisplay(double xPosition, double yPosition, int heartsToDisplay);
    GameplayOverlay createGameplayOverlay(int heartsToDisplay);
    Element createMenuArrow();
    MenuOverlay createMainMenuOverlay();
    MenuOverlay createCharacterSelectOverlay();
    MenuOverlay createLevelSelectOverlay(UserPlaneCode userPlaneCode);
    MenuOverlay createPauseOverlay(ScreenCode currentScreen);
    TextBox createTextBox(String content, ScreenCode screenCode, UserPlaneCode userPlaneCode, double rightPercent, int rows);
    TextBox createTextBox(String content, ScreenCode screenCode, double rightPercent, int rows);
    TimeDisplay createTimeDisplay(int seconds);
    TimerOverlay createTimerOverlay(int secondsRemaining);
}
