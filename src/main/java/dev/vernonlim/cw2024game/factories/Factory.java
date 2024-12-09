package dev.vernonlim.cw2024game.factories;

import dev.vernonlim.cw2024game.assets.AssetLoader;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;

/**
 * An abstract class for shared implementation between all CW2024Game factories.
 * <p>
 * Some of the logic within this class comes from the original project. The sources come from many different places.
 * Original Project: <a href="https://github.com/kooitt/CW2024">GitHub</a>
 */
public abstract class Factory {
    /**
     * The root Pane for this Factory that elements will be based on.
     */
    protected final Pane root;

    /**
     * The AssetLoader for this Factory.
     */
    protected final AssetLoader loader;

    /**
     * Constructs a Factory from the given params.
     *
     * @param root   the root Pane elements will be based on
     * @param loader the AssetLoader handling loading of assets
     */
    public Factory(Pane root, AssetLoader loader) {
        this.root = root;
        this.loader = loader;
    }

    /**
     * Uses the AssetLoader to load an Image
     *
     * @param imageName the image name
     * @return the associated Image, or a default one
     */
    protected Image loadImage(String imageName) {
        return loader.loadImage(imageName);
    }

    /**
     * Uses the AssetLoader to load an AudioClip
     *
     * @param soundName the sound name
     * @return the associated AudioClip, or a default one
     */
    protected AudioClip loadSound(String soundName) {
        return loader.loadSound(soundName);
    }
}
