package dev.vernonlim.cw2024game.elements;

import dev.vernonlim.cw2024game.Main;
import dev.vernonlim.cw2024game.assets.AssetLoader;
import javafx.scene.layout.Pane;
import javafx.scene.image.ImageView;

public class Background extends ImageElement {
    public Background(Pane root, ImageView imageView) {
        super(root, imageView);

        show();
    }

    public void requestFocus() {
        view.requestFocus();
    }
}
