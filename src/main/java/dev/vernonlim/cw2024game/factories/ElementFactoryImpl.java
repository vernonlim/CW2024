package dev.vernonlim.cw2024game.factories;

import dev.vernonlim.cw2024game.Main;
import dev.vernonlim.cw2024game.assets.AssetLoader;
import dev.vernonlim.cw2024game.configs.ImageElementConfig;
import dev.vernonlim.cw2024game.elements.Element;
import dev.vernonlim.cw2024game.elements.ImageElement;
import dev.vernonlim.cw2024game.factories.interfaces.ElementFactory;
import javafx.scene.layout.Pane;

/**
 * The default implementation of ElementFactory for CW2024Game.
 * <p>
 * Some of the logic within this class comes from the original project. The sources come from many different places.
 * Original Project: <a href="https://github.com/kooitt/CW2024">GitHub</a>
 */
public class ElementFactoryImpl extends Factory implements ElementFactory {
    /**
     * Constucts an ElementFactory from the given params.
     *
     * @param root   the root Pane elements will be based on
     * @param loader the AssetLoader handling loading of assets
     */
    public ElementFactoryImpl(Pane root, AssetLoader loader) {
        super(root, loader);
    }

    public Element createShieldImage() {
        ImageElementConfig config = new ImageElementConfig(root);
        config.setImage(loadImage("shield"));
        config.setFitHeight(68.0);

        return new ImageElement(config);
    }

    public Element createBackground(String imageName) {
        ImageElementConfig config = new ImageElementConfig(root);
        config.setImage(loadImage(imageName));

        config.setFocusTraversable(true);
        config.setFitHeight(Main.SCREEN_HEIGHT);
        config.setFitWidth(Main.SCREEN_WIDTH);
        config.setPreserveRatio(false);

        ImageElement background = new ImageElement(config);
        background.show();

        return background;
    }
}
