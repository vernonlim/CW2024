package dev.vernonlim.cw2024game.managers;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;

/**
 * Handles user input, adding event handlers to the given Scene that trigger
 * corresponding changes in the KeybindStore.
 */
public class InputManager {
    /**
     * The KeybindStore for this InputManager
     */
    private final KeybindStore keybindStore;

    /**
     * Constructs an InputManager for a given Scene and KeybindStore.
     *
     * @param scene the Scene
     * @param keybindStore the KeybindStore
     */
    public InputManager(Scene scene, KeybindStore keybindStore) {
        this.keybindStore = keybindStore;

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent e) {
                Action action = keybindStore.getAction(e.getCode());
                if (action != null) {
                    keybindStore.triggerAction(action);
                }
            }
        });
        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent e) {
                Action action = keybindStore.getAction(e.getCode());
                if (action != null) {
                    keybindStore.unsetAction(action);
                }
            }
        });
    }

    /**
     * Indicates whether Fire is pressed.
     *
     * @return true if Fire is pressed, otherwise false
     */
    public boolean isFirePressed() {
        return keybindStore.getActionStatus(Action.FIRE);
    }

    /**
     * Indicates whether Up is pressed.
     *
     * @return true if Up is pressed, otherwise false
     */
    public boolean isUpPressed() {
        return keybindStore.getActionStatus(Action.UP);
    }

    /**
     * Indicates whether Down is pressed.
     *
     * @return true if Down is pressed, otherwise false
     */
    public boolean isDownPressed() {
        return keybindStore.getActionStatus(Action.DOWN);
    }

    /**
     * Indicates whether Left is pressed.
     *
     * @return true if Left is pressed, otherwise false
     */
    public boolean isLeftPressed() {
        return keybindStore.getActionStatus(Action.LEFT);
    }

    /**
     * Indicates whether Right is pressed.
     *
     * @return true if Right is pressed, otherwise false
     */
    public boolean isRightPressed() {
        return keybindStore.getActionStatus(Action.RIGHT);
    }

    /**
     * Indicates whether Focus is pressed.
     *
     * @return true if Focus is pressed, otherwise false
     */
    public boolean isFocusPressed() {
        return keybindStore.getActionStatus(Action.FOCUS);
    }

    /**
     * Indicates whether Pause is pressed.
     *
     * @return true if Pause is pressed, otherwise false
     */
    public boolean isPausePressed() {
        return keybindStore.getActionStatus(Action.PAUSE);
    }

    /**
     * Indicates whether Confirm is pressed.
     *
     * @return true if Confirm is pressed, otherwise false
     */
    public boolean isConfirmPressed() {
        return keybindStore.getActionStatus(Action.CONFIRM);
    }
}
