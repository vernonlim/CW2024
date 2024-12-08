package dev.vernonlim.cw2024game.elements;

import dev.vernonlim.cw2024game.configs.ImageElementConfig;
import javafx.scene.image.ImageView;

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
