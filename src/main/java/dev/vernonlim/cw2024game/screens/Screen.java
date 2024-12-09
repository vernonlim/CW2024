package dev.vernonlim.cw2024game.screens;

import javafx.scene.Scene;

/**
 * An interface representing a Screen abstractly.
 */
public interface Screen {
    /**
     * Start rendering the Screen.
     */
    void start();

    /**
     * Gets the Scene associated with this Screen
     *
     * @return the Scene associated with this Screen
     */
    Scene getScene();
}
