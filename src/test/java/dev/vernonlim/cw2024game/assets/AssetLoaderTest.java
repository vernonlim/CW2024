package dev.vernonlim.cw2024game.assets;

import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AssetLoaderTest {
    static ArrayList<AssetLoader> loaders;

    @BeforeAll
    static void setup() {
        Platform.startup(() -> {
        });

        loaders = new ArrayList<>();
        loaders.add(new UpFrontAssetLoader());
        loaders.add(new CachedAssetLoader());
    }

    @Test
    @DisplayName("Ensures the loader is returning the same instance of a resource when the load method is called")
    void sameImage() {
        for (AssetLoader loader : loaders) {
            imageTest(loader);
            soundTest(loader);
        }
    }

    void imageTest(AssetLoader loader) {
        Image image1 = loader.loadImage("notfound");
        Image image2 = loader.loadImage("notfound");

        assertEquals(image1, image2);
    }

    void soundTest(AssetLoader loader) {
        AudioClip clip1 = loader.loadSound("gunshot");
        AudioClip clip2 = loader.loadSound("gunshot");

        assertEquals(clip1, clip2);
    }
}