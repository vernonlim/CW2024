package dev.vernonlim.cw2024game.assets;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import static java.util.Map.entry;

import dev.vernonlim.cw2024game.Controller;
import javafx.scene.image.*;
import javafx.scene.media.Media;

public class UpFrontAssetLoader extends AssetLoader {
    private final Map<String, Image> images;
    private final Map<String, Media> sounds;

    public UpFrontAssetLoader() {
        images = new HashMap<>(imagePaths.size());
        sounds = new HashMap<>(soundPaths.size());

        for (String name : imagePaths.keySet()) {
            String path = imagePaths.get(name);

            URL resourcePath = UpFrontAssetLoader.class.getResource(path);
            if (resourcePath == null) {
                Controller.triggerAlertAndExit("Image " + name + " doesn't exist at path " + path + ".");
            } else {
                images.put(name, new Image(resourcePath.toExternalForm()));
            }
        }

        for (String name : soundPaths.keySet()) {
            String path = soundPaths.get(name);

            URL resourcePath = UpFrontAssetLoader.class.getResource(path);
            if (resourcePath == null) {
                Controller.triggerAlertAndExit("Sound " + name + " doesn't exist at path " + path + ".");
            } else {
                sounds.put(name, new Media(resourcePath.toExternalForm()));
            }
        }
    }

    @Override
    public Image loadImage(String name) {
        Image image = images.get(name);
        if (image == null) {
            return images.get("notfound");
        }

        return image;
    }

    @Override
    public Media loadSound(String name) {
        Media sound = sounds.get(name);
        if (sound == null) {
            return sounds.get("error");
        }

        return sound;
    }
}
