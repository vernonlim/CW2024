package dev.vernonlim.cw2024game.elements.configs;

import dev.vernonlim.cw2024game.assets.AssetLoader;
import dev.vernonlim.cw2024game.elements.actors.UserPlaneCode;
import dev.vernonlim.cw2024game.managers.KeybindStore;
import dev.vernonlim.cw2024game.screens.ScreenCode;

public class ScreenConfig {
    private KeybindStore keybindStore;
    private AssetLoader assetLoader;
    private String backgroundImageName;
    private ScreenCode currentScreenCode;
    private UserPlaneCode userPlaneCode;
    private int countdownTime;

    public ScreenConfig(KeybindStore keybindStore, AssetLoader assetLoader) {
        this.keybindStore = keybindStore;
        this.assetLoader = assetLoader;
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

    public int getCountdownTime() {
        return countdownTime;
    }

    public void setCountdownTime(int countdownTime) {
        this.countdownTime = countdownTime;
    }
}
