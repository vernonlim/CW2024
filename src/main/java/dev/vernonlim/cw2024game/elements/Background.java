package dev.vernonlim.cw2024game.elements;

import dev.vernonlim.cw2024game.Main;
import dev.vernonlim.cw2024game.assets.AssetLoader;
import javafx.scene.layout.Pane;

public class Background extends ImageElement {
    public Background(Pane root, AssetLoader loader, String imagePath) {
        super(root, loader, imagePath);

        view.setFocusTraversable(true);
        view.setFitHeight(Main.SCREEN_HEIGHT);
        view.setFitWidth(Main.SCREEN_WIDTH);

        show();
    }

    public void requestFocus() {
        view.requestFocus();
    }
}