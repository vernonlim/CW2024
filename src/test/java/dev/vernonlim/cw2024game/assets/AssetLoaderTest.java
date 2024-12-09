package dev.vernonlim.cw2024game.assets;

import dev.vernonlim.cw2024game.configs.ImageElementConfig;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AssetLoaderTest extends ApplicationTest {
    ArrayList<AssetLoader> loaders;

    @Override
    public void start(Stage stage) {
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