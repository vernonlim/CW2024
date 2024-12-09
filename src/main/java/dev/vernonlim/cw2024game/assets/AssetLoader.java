package dev.vernonlim.cw2024game.assets;

import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;

/**
 * An interface representing an Asset Loader, a class that handles the fetching of assets.
 */
public interface AssetLoader {
    /**
     * Fetches an image with the given name.
     *
     * @param name the name of the image
     * @return an Image, if it exists
     */
    Image loadImage(String name);

    /**
     * Fetches a sound with the given name.
     *
     * @param name the name of the sound
     * @return an AudioClip, if it exists
     */
    AudioClip loadSound(String name);
}
