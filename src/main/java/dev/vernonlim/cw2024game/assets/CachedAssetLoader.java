package dev.vernonlim.cw2024game.assets;

import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * An Asset Loader that caches images after loading them the first time.
 */
public class CachedAssetLoader extends CW2024AssetLoader {
    /**
     * A map between image names and their corresponding Images.
     */
    private final Map<String, Image> images;

    /**
     * A map between sound names and their corresponding AudioClips.
     */
    private final Map<String, AudioClip> sounds;

    /**
     * Constructs a CachedAssetLoader.
     */
    public CachedAssetLoader() {
        images = new HashMap<>(imagePaths.size());
        sounds = new HashMap<>(soundPaths.size());
    }

    @Override
    public Image loadImage(String name) {
        Image image = images.get(name);
        if (image == null) {
            String path = imagePaths.get(name);

            URL resourcePath = CachedAssetLoader.class.getResource(path);
            if (resourcePath == null) {
                return null;
            } else {
                Image fetched = new Image(resourcePath.toExternalForm());
                images.put(name, fetched);

                return fetched;
            }
        }

        return image;
    }

    @Override
    public AudioClip loadSound(String name) {
        AudioClip sound = sounds.get(name);
        if (sound == null) {
            String path = soundPaths.get(name);

            URL resourcePath = CachedAssetLoader.class.getResource(path);
            if (resourcePath == null) {
                return null;
            } else {
                AudioClip fetched = new AudioClip(resourcePath.toString());
                sounds.put(name, fetched);

                return fetched;
            }
        }

        return sound;
    }
}
