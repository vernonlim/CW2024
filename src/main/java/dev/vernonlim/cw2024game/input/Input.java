package dev.vernonlim.cw2024game.input;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.Map;

import static java.util.Map.entry;

public class Input {
    private final Map<Action, BooleanProperty> actionsTriggered = Map.ofEntries(
            entry(Action.UP, new SimpleBooleanProperty()),
            entry(Action.DOWN, new SimpleBooleanProperty()),
            entry(Action.LEFT, new SimpleBooleanProperty()),
            entry(Action.RIGHT, new SimpleBooleanProperty()),
            entry(Action.FIRE, new SimpleBooleanProperty()),
            entry(Action.FOCUS, new SimpleBooleanProperty())
    );

    // this could be read from a file
    private final Map<KeyCode, Action> keyBindings = Map.ofEntries(
            entry(KeyCode.UP, Action.UP),
            entry(KeyCode.DOWN, Action.DOWN),
            entry(KeyCode.LEFT, Action.LEFT),
            entry(KeyCode.RIGHT, Action.RIGHT),
            entry(KeyCode.SPACE, Action.FIRE),
            entry(KeyCode.SHIFT, Action.FOCUS),
            entry(KeyCode.Z, Action.FIRE),
            entry(KeyCode.W, Action.UP),
            entry(KeyCode.A, Action.LEFT),
            entry(KeyCode.S, Action.DOWN),
            entry(KeyCode.D, Action.RIGHT)
    );

    public Input(Scene scene) {
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent e) {
                Action action = keyBindings.get(e.getCode());
                if (action != null) {
                    actionsTriggered.get(action).set(true);
                }
            }
        });
        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent e) {
                Action action = keyBindings.get(e.getCode());
                if (action != null) {
                    actionsTriggered.get(action).set(false);
                }
            }
        });
    }

    public boolean isFirePressed() {
        return actionsTriggered.get(Action.FIRE).get();
    }

    public boolean isUpPressed() {
        return actionsTriggered.get(Action.UP).get();
    }

    public boolean isDownPressed() {
        return actionsTriggered.get(Action.DOWN).get();
    }

    public boolean isLeftPressed() {
        return actionsTriggered.get(Action.LEFT).get();
    }

    public boolean isRightPressed() {
        return actionsTriggered.get(Action.RIGHT).get();
    }

    public boolean isFocusPressed() {
        return actionsTriggered.get(Action.FOCUS).get();
    }
}
