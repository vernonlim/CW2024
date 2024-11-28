package dev.vernonlim.cw2024game.managers;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.Map;

import static java.util.Map.entry;

public class InputManager {
    public final Map<KeyCode, BooleanProperty> keysPressed = Map.ofEntries(
            entry(KeyCode.UP, new SimpleBooleanProperty()),
            entry(KeyCode.DOWN, new SimpleBooleanProperty()),
            entry(KeyCode.LEFT, new SimpleBooleanProperty()),
            entry(KeyCode.RIGHT, new SimpleBooleanProperty()),
            entry(KeyCode.SPACE, new SimpleBooleanProperty()),
            entry(KeyCode.Z, new SimpleBooleanProperty()),
            entry(KeyCode.W, new SimpleBooleanProperty()),
            entry(KeyCode.A, new SimpleBooleanProperty()),
            entry(KeyCode.S, new SimpleBooleanProperty()),
            entry(KeyCode.D, new SimpleBooleanProperty()),
            entry(KeyCode.SHIFT, new SimpleBooleanProperty())
    );

    public InputManager(Scene scene) {
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent e) {
                BooleanProperty prop = keysPressed.get(e.getCode());
                if (prop != null) {
                    prop.set(true);
                }
            }
        });
        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent e) {
                BooleanProperty prop = keysPressed.get(e.getCode());
                if (prop != null) {
                    prop.set(false);
                }
            }
        });
    }

    public boolean anyPressed(KeyCode... codes) {
        boolean pressed = false;

        for (KeyCode code : codes) {
            pressed = pressed || keysPressed.get(code).get();
        }

        return pressed;
    }

    public boolean isFirePressed() {
        return anyPressed(KeyCode.SPACE, KeyCode.Z);
    }

    public boolean isUpPressed() {
        return anyPressed(KeyCode.UP, KeyCode.W);
    }

    public boolean isDownPressed() {
        return anyPressed(KeyCode.DOWN, KeyCode.S);
    }

    public boolean isRightPressed() {
        return anyPressed(KeyCode.RIGHT, KeyCode.D);
    }

    public boolean isLeftPressed() {
        return anyPressed(KeyCode.LEFT, KeyCode.A);
    }

    public boolean isFocusPressed() {
        return anyPressed(KeyCode.SHIFT);
    }
}
