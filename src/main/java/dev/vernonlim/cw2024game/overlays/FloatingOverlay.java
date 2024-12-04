package dev.vernonlim.cw2024game.overlays;

import dev.vernonlim.cw2024game.Main;
import dev.vernonlim.cw2024game.elements.Element;
import javafx.scene.layout.Pane;

public class FloatingOverlay extends Element {
    public final Pane pane;

    public FloatingOverlay(Pane root) {
        super(root);

        this.pane = new Pane();
        this.node = pane;

        pane.setMaxHeight(Main.SCREEN_HEIGHT);
        pane.setMaxWidth(Main.SCREEN_WIDTH);
    }
}
