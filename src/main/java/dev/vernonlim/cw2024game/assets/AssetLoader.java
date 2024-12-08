package dev.vernonlim.cw2024game.assets;

import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;

/**
 * An interface representing an Asset Loader, a class that handles the fetching of assets.
 */
public interface AssetLoader {
    Image loadImage(String name);
    AudioClip loadSound(String name);
}
