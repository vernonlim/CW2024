package dev.vernonlim.cw2024game.configs;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 * A data class used for streamlining ImageElement creation
 */
public class ImageElementConfig extends ElementConfig {
    /**
     * The ImageView for the ImageElement.
     */
    private ImageView imageView;

    /**
     * Constructs an ImageElementConfig with the given params.
     *
     * @param root the root Pane on which the ImageElement is based
     */
    public ImageElementConfig(Pane root) {
        super(root);
    }

    /**
     * Gets the ImageView.
     *
     * @return the ImageView
     */
    public ImageView getImageView() {
        return imageView;
    }

    /**
     * Sets the Image. Automatically creates an ImageView from it.
     *
     * @param image the Image to set
     */
    public void setImage(Image image) {
        this.imageView = new ImageView(image);
        this.imageView.setPreserveRatio(true);
    }

    /**
     * Sets the fit height.
     *
     * @param height the fit height to set
     */
    public void setFitHeight(double height) {
        imageView.setFitHeight(height);
    }

    /**
     * Sets the fit width.
     *
     * @param width the fit width to set
     */
    public void setFitWidth(double width) {
        imageView.setFitWidth(width);
    }

    /**
     * Sets whether to preserve the ratio of the ImageView.
     *
     * @param preserve true to preserve the ratio, false otherwise
     */
    public void setPreserveRatio(boolean preserve) {
        imageView.setPreserveRatio(preserve);
    }

    /**
     * Sets whether the ImageView is focus traversable.
     *
     * @param traversable true if the ImageView is focus traversable, false otherwise
     */
    public void setFocusTraversable(boolean traversable) {
        imageView.setFocusTraversable(traversable);
    }
}
