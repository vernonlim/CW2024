package dev.vernonlim.cw2024game.configs;

import dev.vernonlim.cw2024game.assets.AssetLoader;
import dev.vernonlim.cw2024game.elements.actors.UserPlaneCode;
import dev.vernonlim.cw2024game.managers.KeybindStore;
import dev.vernonlim.cw2024game.screens.ScreenCode;

/**
 * A data class used for streamlining Screen creation
 */
public class ScreenConfig {
    /**
     * The KeybindStore for the Screen.
     */
    private KeybindStore keybindStore;

    /**
     * The AssetLoader for the Screen.
     */
    private AssetLoader assetLoader;

    /**
     * The background image name for the Screen.
     */
    private String backgroundImageName;

    /**
     * The current ScreenCode.
     */
    private ScreenCode currentScreenCode;

    /**
     * The current UserPlaneCode.
     */
    private UserPlaneCode userPlaneCode;

    /**
     * The countdown time for Countdown Levels.
     */
    private int countdownTime;

    /**
     * Constructs a ScreenConfig with the given params.
     *
     * @param keybindStore the KeybindStore for the Screen
     * @param assetLoader the AssetLoader for the Screen
     */
    public ScreenConfig(KeybindStore keybindStore, AssetLoader assetLoader) {
        this.keybindStore = keybindStore;
        this.assetLoader = assetLoader;
    }

    /**
     * Gets the KeybindStore.
     * 
     * @return the KeybindStore
     */
    public KeybindStore getKeybindStore() {
        return keybindStore;
    }

    /**
     * Sets the KeybindStore.
     * 
     * @param keybindStore the KeybindStore to set
     */
    public void setKeybindStore(KeybindStore keybindStore) {
        this.keybindStore = keybindStore;
    }

    /**
     * Gets the AssetLoader.
     * 
     * @return the AssetLoader
     */
    public AssetLoader getAssetLoader() {
        return assetLoader;
    }

    /**
     * Sets the AssetLoader.
     * 
     * @param assetLoader the AssetLoader to set
     */
    public void setAssetLoader(AssetLoader assetLoader) {
        this.assetLoader = assetLoader;
    }

    /**
     * Gets the background image name.
     * 
     * @return the background image name
     */
    public String getBackgroundImageName() {
        return backgroundImageName;
    }

    /**
     * Sets the background image name.
     * 
     * @param backgroundImageName the background image name to set
     */
    public void setBackgroundImageName(String backgroundImageName) {
        this.backgroundImageName = backgroundImageName;
    }

    /**
     * Gets the current ScreenCode.
     * 
     * @return the current ScreenCode
     */
    public ScreenCode getCurrentScreenCode() {
        return currentScreenCode;
    }

    /**
     * Sets the current ScreenCode.
     * 
     * @param currentScreenCode the current ScreenCode to set
     */
    public void setCurrentScreenCode(ScreenCode currentScreenCode) {
        this.currentScreenCode = currentScreenCode;
    }

    /**
     * Gets the UserPlaneCode.
     * 
     * @return the UserPlaneCode
     */
    public UserPlaneCode getUserPlaneCode() {
        return userPlaneCode;
    }

    /**
     * Sets the UserPlaneCode.
     * 
     * @param userPlaneCode the UserPlaneCode to set
     */
    public void setUserPlaneCode(UserPlaneCode userPlaneCode) {
        this.userPlaneCode = userPlaneCode;
    }

    /**
     * Gets the countdown time.
     * 
     * @return the countdown time
     */
    public int getCountdownTime() {
        return countdownTime;
    }

    /**
     * Sets the countdown time.
     * 
     * @param countdownTime the countdown time to set
     */
    public void setCountdownTime(int countdownTime) {
        this.countdownTime = countdownTime;
    }
}
