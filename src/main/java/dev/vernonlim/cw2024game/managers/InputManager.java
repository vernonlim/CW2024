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
                Action action = keybindStore.keyBindings.get(e.getCode());
                if (action != null) {
                    keybindStore.actionsTriggered.get(action).set(true);
                }
            }
        });
        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent e) {
                Action action = keybindStore.keyBindings.get(e.getCode());
                if (action != null) {
                    keybindStore.actionsTriggered.get(action).set(false);
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
}
