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
     * @return true if Fire is pressed, otherwise false
     */
    public boolean isFirePressed() {
        return keybindStore.getActionStatus(Action.FIRE);
    }

    /**
     * @return true if Up is pressed, otherwise false
     */
    public boolean isUpPressed() {
        return keybindStore.getActionStatus(Action.UP);
    }

    /**
     * @return true if Down is pressed, otherwise false
     */
    public boolean isDownPressed() {
        return keybindStore.getActionStatus(Action.DOWN);
    }

    /**
     * @return true if Left is pressed, otherwise false
     */
    public boolean isLeftPressed() {
        return keybindStore.getActionStatus(Action.LEFT);
    }

    /**
     * @return true if Right is pressed, otherwise false
     */
    public boolean isRightPressed() {
        return keybindStore.getActionStatus(Action.RIGHT);
    }

    /**
     * @return true if Focus is pressed, otherwise false
     */
    public boolean isFocusPressed() {
        return keybindStore.getActionStatus(Action.FOCUS);
    }

    /**
     * @return true if Pause is pressed, otherwise false
     */
    public boolean isPausePressed() {
        return keybindStore.getActionStatus(Action.PAUSE);
    }

    /**
     * @return true if Confirm is pressed, otherwise false
     */
    public boolean isConfirmPressed() {
        return keybindStore.getActionStatus(Action.CONFIRM);
    }
}
