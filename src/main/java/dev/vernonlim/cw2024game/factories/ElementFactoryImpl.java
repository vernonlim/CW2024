package dev.vernonlim.cw2024game.factories;

import dev.vernonlim.cw2024game.Main;
import dev.vernonlim.cw2024game.assets.AssetLoader;
import dev.vernonlim.cw2024game.elements.*;
import dev.vernonlim.cw2024game.elements.actors.*;
import dev.vernonlim.cw2024game.factories.interfaces.ElementFactory;
import javafx.scene.layout.Pane;
import javafx.scene.image.ImageView;

public class ElementFactoryImpl extends AbstractFactoryImpl implements ElementFactory {
    public ElementFactoryImpl(Pane root, AssetLoader loader) {
        super(root, loader);
    }

    public Element createShieldImage() {
        ImageView imageView = makeView("shield");
        imageView.setFitHeight(68);

        return new ShieldImage(root, imageView);
    }

    public Element createBackground(String imageName) {
        ImageView imageView = makeView(imageName);

        imageView.setFocusTraversable(true);
        imageView.setFitHeight(Main.SCREEN_HEIGHT);
        imageView.setFitWidth(Main.SCREEN_WIDTH);
        imageView.setPreserveRatio(false);

        return new Background(root, imageView);
    }
}
