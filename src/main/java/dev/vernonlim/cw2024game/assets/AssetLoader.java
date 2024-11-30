package dev.vernonlim.cw2024game.assets;

import javafx.scene.image.Image;
import javafx.scene.media.Media;

public interface AssetLoader {
    Image loadImage(String name);
    Media loadSound(String name);
}
