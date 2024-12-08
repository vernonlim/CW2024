package dev.vernonlim.cw2024game.configs;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class ImageElementConfig extends ElementConfig {
    private ImageView imageView;

    public ImageElementConfig(Pane root) {
        super(root);
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImage(Image image) {
        this.imageView = new ImageView(image);
        this.imageView.setPreserveRatio(true);
    }

    public void setFitHeight(double height) {
        imageView.setFitHeight(height);
    }

    public void setFitWidth(double width) {
        imageView.setFitWidth(width);
    }

    public void setPreserveRatio(boolean preserve) {
        imageView.setPreserveRatio(preserve);
    }

    public void setFocusTraversable(boolean traversable) {
        imageView.setFocusTraversable(traversable);
    }
}
