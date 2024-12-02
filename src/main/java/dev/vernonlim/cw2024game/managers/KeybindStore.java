package dev.vernonlim.cw2024game.managers;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.input.KeyCode;
import org.json.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import static java.util.Map.entry;

public class KeybindStore {
    public final Map<Action, BooleanProperty> actionsTriggered;

    // this could be read from a file
    public final Map<KeyCode, Action> keyBindings;

    public KeybindStore(String jsonPath) {
        this.actionsTriggered = createActionMap();
        this.keyBindings = createKeyCodeMap(jsonPath);
    }

    public boolean getActionStatus(Action action) {
        return actionsTriggered.get(action).get();
    }

    private Map<Action, BooleanProperty> createActionMap() {
        Map<Action, BooleanProperty> actionMap = new HashMap<>();
        for (Action action : Action.values()) {
            actionMap.put(action, new SimpleBooleanProperty());
        }

        return actionMap;
    }

    private Map<KeyCode, Action> createKeyCodeMap(String jsonPath) {
        String keybindsPath = KeybindStore.class.getResource(jsonPath).getFile();

        if (keybindsPath == null) {
            return getDefaultKeyCodeMap();
        }

        try {
            Path path = Paths.get(keybindsPath);
            String contents = Files.readString(path);

            return parseStringAsKeyCodeMap(contents);
        } catch (IOException e) {
            return getDefaultKeyCodeMap();
        }
    }

    private Map<KeyCode, Action> parseStringAsKeyCodeMap(String contents) {
        JSONObject obj = new JSONObject(contents);

        Map<KeyCode, Action> map = new HashMap<>();

        for (String keycode : obj.keySet()) {
            String action = obj.getString(keycode);

            try {
                map.put(KeyCode.valueOf(keycode), Action.valueOf(action));
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid keybinding! Continuing...");
            }
        }

        return map;
    }

    private Map<KeyCode, Action> getDefaultKeyCodeMap() {
        return Map.ofEntries(
                entry(KeyCode.UP, Action.UP),
                entry(KeyCode.DOWN, Action.DOWN),
                entry(KeyCode.LEFT, Action.LEFT),
                entry(KeyCode.RIGHT, Action.RIGHT),
                entry(KeyCode.SHIFT, Action.FOCUS),
                entry(KeyCode.Z, Action.FIRE)
        );
    }
}
