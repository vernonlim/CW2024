package dev.vernonlim.cw2024game.elements;

import dev.vernonlim.cw2024game.assets.AssetLoader;
import dev.vernonlim.cw2024game.elements.configs.ImageElementConfig;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class ImageElement extends Element {
    protected ImageView view;

    public ImageElement(ImageElementConfig config) {
        super(config);

        this.view = config.getImageView();
        this.node = view;

        if (config.shouldSetPosition()) {
            setPosition(config.getPosition());
        }
    }
}
