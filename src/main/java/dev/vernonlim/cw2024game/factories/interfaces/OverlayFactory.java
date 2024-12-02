package dev.vernonlim.cw2024game.factories.interfaces;

import dev.vernonlim.cw2024game.elements.Element;
import dev.vernonlim.cw2024game.elements.HeartDisplay;
import dev.vernonlim.cw2024game.overlays.GameplayOverlay;
import javafx.scene.layout.Pane;

public interface OverlayFactory {
    OverlayFactory withNewRoot(Pane newRoot);
    Element createGameOverImage(double xPosition, double yPosition);
    Element createHeart();
    Element createWinImage(double xPosition, double yPosition);
    HeartDisplay createHeartDisplay(double xPosition, double yPosition, int heartsToDisplay);
    GameplayOverlay createGameplayOverlay(int heartsToDisplay);
}
