package dev.vernonlim.cw2024game.elements.configs;

import dev.vernonlim.cw2024game.Controller;
import dev.vernonlim.cw2024game.assets.AssetLoader;
import dev.vernonlim.cw2024game.elements.actors.UserPlaneCode;
import dev.vernonlim.cw2024game.managers.KeybindStore;
import dev.vernonlim.cw2024game.screens.ScreenCode;
import javafx.stage.Stage;

public class ScreenConfig {
    private Stage stage;
    private Controller controller;
    private KeybindStore keybindStore;
    private AssetLoader assetLoader;
    private String backgroundImageName;
    private ScreenCode currentScreenCode;
    private UserPlaneCode userPlaneCode;

    public ScreenConfig(Stage stage, Controller controller, KeybindStore keybindStore, AssetLoader assetLoader) {
        this.stage = stage;
        this.controller = controller;
        this.keybindStore = keybindStore;
        this.assetLoader = assetLoader;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public KeybindStore getKeybindStore() {
        return keybindStore;
    }

    public void setKeybindStore(KeybindStore keybindStore) {
        this.keybindStore = keybindStore;
    }

    public AssetLoader getAssetLoader() {
        return assetLoader;
    }

    public void setAssetLoader(AssetLoader assetLoader) {
        this.assetLoader = assetLoader;
    }

    public String getBackgroundImageName() {
        return backgroundImageName;
    }

    public void setBackgroundImageName(String backgroundImagePath) {
        this.backgroundImageName = backgroundImagePath;
    }

    public ScreenCode getCurrentScreenCode() {
        return currentScreenCode;
    }

    public void setCurrentScreenCode(ScreenCode currentScreenCode) {
        this.currentScreenCode = currentScreenCode;
    }

    public UserPlaneCode getUserPlaneCode() {
        return userPlaneCode;
    }

    public void setUserPlaneCode(UserPlaneCode userPlaneCode) {
        this.userPlaneCode = userPlaneCode;
    }
}
