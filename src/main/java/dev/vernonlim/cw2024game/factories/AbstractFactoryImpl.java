package dev.vernonlim.cw2024game.factories;

import dev.vernonlim.cw2024game.assets.AssetLoader;
import dev.vernonlim.cw2024game.elements.ProjectileListener;
import dev.vernonlim.cw2024game.managers.InputManager;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public abstract class AbstractFactoryImpl {
    protected Pane root;
    protected final AssetLoader loader;

    public AbstractFactoryImpl(Pane root, AssetLoader loader) {
        this.root = root;
        this.loader = loader;
    }

    protected ImageView makeView(String imageName) {
        Image image = loader.loadImage(imageName);
        ImageView imageView = new ImageView(image);
        imageView.setPreserveRatio(true);

        return imageView;
    }
}
