package dev.vernonlim.cw2024game.managers;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;

public class InputManager {
    private final KeybindStore keybindStore;

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

    public boolean isFirePressed() {
        return keybindStore.getActionStatus(Action.FIRE);
    }

    public boolean isUpPressed() {
        return keybindStore.getActionStatus(Action.UP);
    }

    public boolean isDownPressed() {
        return keybindStore.getActionStatus(Action.DOWN);
    }

    public boolean isLeftPressed() {
        return keybindStore.getActionStatus(Action.LEFT);
    }

    public boolean isRightPressed() {
        return keybindStore.getActionStatus(Action.RIGHT);
    }

    public boolean isFocusPressed() {
        return keybindStore.getActionStatus(Action.FOCUS);
    }

    public boolean isPausePressed() {
        return keybindStore.getActionStatus(Action.PAUSE);
    }

    public boolean isConfirmPressed() {
        return keybindStore.getActionStatus(Action.CONFIRM);
    }
}
