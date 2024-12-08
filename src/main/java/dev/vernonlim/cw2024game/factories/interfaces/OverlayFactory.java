package dev.vernonlim.cw2024game.factories.interfaces;

import dev.vernonlim.cw2024game.overlays.CharacterSelectOverlay;
import dev.vernonlim.cw2024game.screens.ScreenCode;
import dev.vernonlim.cw2024game.elements.*;
import dev.vernonlim.cw2024game.elements.actors.UserPlaneCode;
import dev.vernonlim.cw2024game.overlays.GameplayOverlay;
import dev.vernonlim.cw2024game.overlays.MenuOverlay;
import dev.vernonlim.cw2024game.overlays.TimerOverlay;
import javafx.scene.layout.Pane;

/**
 * An interface defining a Factory for creating many different Elements associated with Overlays.
 * These elements usually share the common feature of needing to be drawn on a different layer
 * than the root.
 */
public interface OverlayFactory {
    /**
     * Constructs a new OverlayFactory based on a new root Pane.
     *
     * @param newRoot the new root Pane
     * @return the new OverlayFactory
     */
    OverlayFactory withNewRoot(Pane newRoot);

    /**
     * Creates an image displayed during Game Over.
     *
     * @param x the x position
     * @param y the y position
     * @return the Game Over Image.
     */
    Element createGameOverImage(double xPosition, double yPosition);

    /**
     * Creates a Heart image for use in HeartDisplay.
     */
    Element createHeart();

    /**
     * Creates an image displayed on Game Win.
     *
     * @param x the x position
     * @param y the y position
     * @return the Win Image
     */
    Element createWinImage(double xPosition, double yPosition);

    /**
     * Creates a Heart Display for visually representing the player's health.
     *
     * @param x the x position
     * @param y the y position
     * @param heartsToDisplay the number of hearts to display initially
     * @return the Heart Display
     */
    HeartDisplay createHeartDisplay(double xPosition, double yPosition, int heartsToDisplay);

    /**
     * Creates a Gameplay Overlay displaying important player information like the Heart Display
     *
     * @param heartsToDisplay the number of hearts to display initially
     * @return the Gameplay Overlay
     */
    GameplayOverlay createGameplayOverlay(int heartsToDisplay);

    /**
     * Creates an image of an arrow indicating the current button selected in Menu Overlays.
     *
     * @return the Menu Arrow
     */
    Element createMenuArrow();

    /**
     * Creates a Main Menu Overlay containing interactable buttons.
     *
     * @return the Main Menu Overlay
     */
    MenuOverlay createMainMenuOverlay();

    /**
     * Creates a Character Select Overlay displaying the different User Planes.
     *
     * @return the Character Select Overlay
     */
    MenuOverlay createCharacterSelectOverlay();

    /**
     * Creates a Level Select Overlay displaying the list of Levels.
     *
     * @return the Level Select Overlay
     */
    MenuOverlay createLevelSelectOverlay(UserPlaneCode userPlaneCode);

    /**
     * Creates a Pause Overlay displayed when the game is paused.
     *
     * @param currentScreen the current screen the pause overlay is to be displayed over
     * @return
     */
    MenuOverlay createPauseOverlay(ScreenCode currentScreen);

    /**
     * Creates a TextBox (Button) from the given params.
     *
     * @param content the text to be displayed
     * @param screenCode the Screen the button links to
     * @param userPlaneCode the UserPlaneCode the button passes to the Screen
     * @param rightPercent the amount of horizontal space the button takes up
     * @param rows the number of rows the grid pane the button will live in has
     * @return the TextBox
     */
    TextBox createTextBox(String content, ScreenCode screenCode, UserPlaneCode userPlaneCode, double rightPercent, int rows);

    /**
     * Creates a TextBox (Button) from the given params with a default UserPlaneCode.
     *
     * @param content the text to be displayed
     * @param screenCode the Screen the button links to
     * @param rightPercent the amount of horizontal space the button takes up
     * @param rows the number of rows the grid pane the button will live in has
     * @return the TextBox
     */
    TextBox createTextBox(String content, ScreenCode screenCode, double rightPercent, int rows);

    /**
     * Creates a Time Display (countdown timer) that counts down towards 0.
     *
     * @param seconds the starting time
     * @return the TimeDisplay
     */
    TimeDisplay createTimeDisplay(int seconds);

    /**
     * Creates a Timer Overlay that contains a Time Display.
     *
     * @param secondsRemaining the starting time
     * @return the Timer Overlay
     */
    TimerOverlay createTimerOverlay(int secondsRemaining);
}
