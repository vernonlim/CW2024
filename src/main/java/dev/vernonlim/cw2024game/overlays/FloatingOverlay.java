package dev.vernonlim.cw2024game.overlays;

import dev.vernonlim.cw2024game.Main;
import dev.vernonlim.cw2024game.elements.ContainerElement;
import dev.vernonlim.cw2024game.configs.ElementConfig;
import javafx.scene.layout.Pane;

public class FloatingOverlay extends ContainerElement {
    public FloatingOverlay(ElementConfig config) {
        super(config);

        this.container = new Pane();
        this.node = container;

        container.setMaxHeight(Main.SCREEN_HEIGHT);
        container.setMaxWidth(Main.SCREEN_WIDTH);
    }
}
