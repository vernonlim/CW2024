package dev.vernonlim.cw2024game.managers;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.input.KeyCode;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import static java.util.Map.entry;

/**
 * Stores maps between KeyCodes and Actions, and Actions and BooleanProperties.
 * <p>
 * This thus keeps track of keybinds, their associated actions and their state.
 */
public class KeybindStore {
    /**
     * The map between Actions and BooleanProperties keeping track of action state.
     */
    private final Map<Action, BooleanProperty> actionsTriggered;

    /**
     * The map between KeyCodes and Actions used to implement player keybinds.
     */
    private final Map<KeyCode, Action> keyBindings;

    /**
     * Constructs a KeybindStore from a given path to a JSON file defining the keybinds.
     *
     * @param jsonPath the path to the JSON file
     */
    public KeybindStore(String jsonPath) {
        this.actionsTriggered = createActionMap();
        this.keyBindings = createKeyCodeMap(jsonPath);
    }

    /**
     * Gets the Action associated with a KeyCode.
     *
     * @param code the KeyCode
     * @return the associated Action
     */
    public Action getAction(KeyCode code) {
        return keyBindings.get(code);
    }

    /**
     * Sets the BooleanProperty associated with a given Action to true.
     *
     * @param action the Action
     */
    public void triggerAction(Action action) {
        actionsTriggered.get(action).set(true);
    }

    /**
     * Sets the BooleanProperty associated with a given Action to false.
     *
     * @param action the Action
     */
    public void unsetAction(Action action) {
        actionsTriggered.get(action).set(false);
    }

    /**
     * Gets the value of the BooleanProperty associated with a given Action.
     *
     * @param action the Action
     * @return the value of the associated BooleanProperty
     */
    public boolean getActionStatus(Action action) {
        return actionsTriggered.get(action).get();
    }

    /**
     * Creates the map between Actions and their associated BooleanProperties.
     *
     * @return the initialized map
     */
    private Map<Action, BooleanProperty> createActionMap() {
        Map<Action, BooleanProperty> actionMap = new HashMap<>();
        for (Action action : Action.values()) {
            actionMap.put(action, new SimpleBooleanProperty());
        }

        return actionMap;
    }

    /**
     * Creates the map between KeyCodes and their associated Actions from a JSON file
     * containing a map between keybinds and their associated KeyCodes.
     *
     * @param jsonPath the path to the file containing the keybinds
     * @return the initialized map
     */
    private Map<KeyCode, Action> createKeyCodeMap(String jsonPath) {
        URL keybindsURL = KeybindStore.class.getResource(jsonPath);

        if (keybindsURL == null) {
            return getDefaultKeyCodeMap();
        }

        try {
            Path path = Paths.get(keybindsURL.toURI());
            String contents = Files.readString(path);

            return parseStringAsKeyCodeMap(contents);
        } catch (Exception e) {
            System.out.println("Failed to read keybinds, information: " + e.getMessage());
            return getDefaultKeyCodeMap();
        }
    }

    /**
     * Parses a string containing the contents of a JSON file, and attempts to
     * parse it as a keybind to KeyCode map.
     *
     * @param contents the JSON file as a string
     * @return the initialized map
     */
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

    /**
     * Returns a default map between KeyCodes and actions if the keybind file is not found.
     *
     * @return the default map
     */
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
