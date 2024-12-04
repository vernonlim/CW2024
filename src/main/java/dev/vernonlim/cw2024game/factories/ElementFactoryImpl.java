package dev.vernonlim.cw2024game.factories;

import dev.vernonlim.cw2024game.Main;
import dev.vernonlim.cw2024game.assets.AssetLoader;
import dev.vernonlim.cw2024game.elements.*;
import dev.vernonlim.cw2024game.elements.actors.*;
import dev.vernonlim.cw2024game.factories.interfaces.ElementFactory;
import dev.vernonlim.cw2024game.factories.interfaces.OverlayFactory;
import javafx.scene.layout.Pane;
import javafx.scene.image.ImageView;

public class ElementFactoryImpl extends FactoryParent implements ElementFactory {
    public ElementFactoryImpl(Pane root, AssetLoader loader) {
        super(root, loader);
    }

    public ElementFactory withNewRoot(Pane newRoot) {
        return new ElementFactoryImpl(newRoot, loader);
    }

    public Element createShieldImage() {
        ImageView imageView = makeView("shield");
        imageView.setFitHeight(68);

        return new ImageElement(root, imageView);
    }

    public Element createBackground(String imageName) {
        ImageView imageView = makeView(imageName);

        imageView.setFocusTraversable(true);
        imageView.setFitHeight(Main.SCREEN_HEIGHT);
        imageView.setFitWidth(Main.SCREEN_WIDTH);
        imageView.setPreserveRatio(false);

        ImageElement background = new ImageElement(root, imageView);
        background.show();

        return background;
    }
}
