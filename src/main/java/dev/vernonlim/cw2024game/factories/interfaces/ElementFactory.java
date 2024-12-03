package dev.vernonlim.cw2024game.factories.interfaces;

import dev.vernonlim.cw2024game.elements.*;
import javafx.scene.layout.Pane;

public interface ElementFactory {
    ElementFactory withNewRoot(Pane newRoot);
    Element createShieldImage();
    Element createBackground(String imageName);
}
