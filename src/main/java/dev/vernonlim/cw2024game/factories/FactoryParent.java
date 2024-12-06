package dev.vernonlim.cw2024game.factories;

import dev.vernonlim.cw2024game.assets.AssetLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;

public abstract class FactoryParent {
    protected final Pane root;
    protected final AssetLoader loader;

    public FactoryParent(Pane root, AssetLoader loader) {
        this.root = root;
        this.loader = loader;
    }

    protected Image loadImage(String imageName) {
        return loader.loadImage(imageName);
    }

    protected AudioClip loadSound(String soundName) {
        return loader.loadSound(soundName);
    }
}
