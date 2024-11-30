package dev.vernonlim.cw2024game.elements.actors;

import dev.vernonlim.cw2024game.assets.AssetLoader;
import dev.vernonlim.cw2024game.elements.ImageElement;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public abstract class ActiveActor extends ImageElement {
    public ActiveActor(Pane root, ImageView imageView) {
        super(root, imageView);
    }

    public abstract void updatePosition(double deltaTime);

    public abstract void updateActor(double deltaTime, double currentTime);
}
