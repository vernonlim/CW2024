package dev.vernonlim.cw2024game.elements;

import dev.vernonlim.cw2024game.configs.ImageElementConfig;
import javafx.scene.image.ImageView;

/**
 * An Element with an ImageView for a Node.
 */
public class ImageElement extends Element {
    /**
     * The ImageView for this ImageElement.
     */
    protected final ImageView view;

    /**
     * Constructs an ImageElement.
     *
     * @param config the configuration object containing the necessary data to construct the ImageElement
     */
    public ImageElement(ImageElementConfig config) {
        super(config);

        this.view = config.getImageView();
        this.node = view;

        if (config.shouldSetPosition()) {
            setPosition(config.getPosition());
        }
    }
}
