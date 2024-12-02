package dev.vernonlim.cw2024game.elements;

import dev.vernonlim.cw2024game.assets.AssetLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class ImageElement extends Element {
    protected ImageView view;

    public ImageElement(Pane root, ImageView imageView) {
        super(root);

        view = imageView;
        node = view;
    }
}
