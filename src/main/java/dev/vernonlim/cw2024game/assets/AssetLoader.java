package dev.vernonlim.cw2024game.assets;

import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.AudioClip;

public interface AssetLoader {
    Image loadImage(String name);
    AudioClip loadSound(String name);
}
