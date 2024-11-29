package dev.vernonlim.cw2024game.elements;

import dev.vernonlim.cw2024game.assets.AssetLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public abstract class ImageElement extends Element {
    protected ImageView view;
    protected AssetLoader loader;

    public ImageElement(Pane root, AssetLoader loader) {
        super(root);
        this.loader = loader;
    }

    public ImageElement(Pane root, AssetLoader loader, String imageName) {
        super(root);
        this.loader = loader;

        Image image = loader.loadImage(imageName);
        view = new ImageView(image);
        node = view;
    }
}
