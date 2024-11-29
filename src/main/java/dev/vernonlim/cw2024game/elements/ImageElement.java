package dev.vernonlim.cw2024game.elements;

import dev.vernonlim.cw2024game.Controller;
import javafx.scene.layout.Pane;
import javafx.scene.image.*;

public abstract class ImageElement extends Element {
    protected ImageView view;

    public ImageElement(Pane root) {
        super(root);
    }

    public ImageElement(Pane root, String imagePath) {
        super(root);

        this.view = new ImageView(new Image(Controller.fetchResourcePath(imagePath)));
        this.node = view;
    }
}
