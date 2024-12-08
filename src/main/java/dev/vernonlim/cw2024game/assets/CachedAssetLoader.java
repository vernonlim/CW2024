package dev.vernonlim.cw2024game.assets;

import dev.vernonlim.cw2024game.Controller;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;

import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class CachedAssetLoader extends CW2024AssetLoader {
    private final Map<String, Image> images;
    private final Map<String, AudioClip> sounds;

    public CachedAssetLoader() {
        images = new HashMap<>(imagePaths.size());
        sounds = new HashMap<>(soundPaths.size());
    }

    public Image loadImage(String name) {
        Image image = images.get(name);
        if (image == null) {
            String path = imagePaths.get(name);

            URL resourcePath = CachedAssetLoader.class.getResource(path);
            if (resourcePath == null) {
                Controller.triggerAlertAndExit("Image " + name + " doesn't exist at path " + path + ".");
            } else {
                Image fetched = new Image(resourcePath.toExternalForm());
                images.put(name, fetched);

                return fetched;
            }
        }

        return image;
    }

    public AudioClip loadSound(String name) {
        AudioClip sound = sounds.get(name);
        if (sound == null) {
            String path = soundPaths.get(name);

            URL resourcePath = CachedAssetLoader.class.getResource(path);
            if (resourcePath == null) {
                Controller.triggerAlertAndExit("Sound " + name + " doesn't exist at path " + path + ".");
            } else {
                AudioClip fetched = new AudioClip(resourcePath.toString());
                sounds.put(name, fetched);

                return fetched;
            }
        }

        return sound;
    }
}
