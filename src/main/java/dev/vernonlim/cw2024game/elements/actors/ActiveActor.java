package dev.vernonlim.cw2024game.elements.actors;

import dev.vernonlim.cw2024game.assets.AssetLoader;
import dev.vernonlim.cw2024game.elements.ImageElement;
import javafx.scene.layout.Pane;

public abstract class ActiveActor extends ImageElement {
    public ActiveActor(Pane root, AssetLoader loader, String imageName, int imageHeight) {
        super(root, loader, imageName);

        view.setFitHeight(imageHeight);
        view.setPreserveRatio(true);
    }

    public abstract void updatePosition(double deltaTime);

    public abstract void updateActor(double deltaTime, double currentTime);
}
