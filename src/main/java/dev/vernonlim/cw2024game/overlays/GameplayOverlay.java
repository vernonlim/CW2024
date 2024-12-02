package dev.vernonlim.cw2024game.overlays;

import dev.vernonlim.cw2024game.Main;
import dev.vernonlim.cw2024game.elements.Element;
import dev.vernonlim.cw2024game.elements.HeartDisplay;
import dev.vernonlim.cw2024game.elements.WinImage;
import dev.vernonlim.cw2024game.factories.ElementFactory;
import javafx.scene.layout.Pane;

public class GameplayOverlay extends Element {
    private static final double HEART_DISPLAY_X_POSITION = 5;
    private static final double HEART_DISPLAY_Y_POSITION = 25;
    public final Pane pane;
    private final WinImage winImage;
    private final Element gameOverImage;
    private final HeartDisplay heartDisplay;
    private final ElementFactory overlayElementFactory;

    public GameplayOverlay(ElementFactory elementFactory, Pane root, int heartsToDisplay) {
        super(root);

        this.pane = new Pane();
        this.node = pane;

        pane.setMaxHeight(Main.SCREEN_HEIGHT);
        pane.setMaxWidth(Main.SCREEN_WIDTH);

        this.overlayElementFactory = elementFactory.withNewRoot(pane);

        this.heartDisplay = overlayElementFactory.createHeartDisplay(HEART_DISPLAY_X_POSITION, HEART_DISPLAY_Y_POSITION, heartsToDisplay);
        this.winImage = overlayElementFactory.createWinImage(Main.SCREEN_WIDTH / 2.0f, Main.SCREEN_HEIGHT / 2.0f);
        this.gameOverImage = overlayElementFactory.createGameOverImage(Main.SCREEN_WIDTH / 2.0f, Main.SCREEN_HEIGHT / 2.0f);

        heartDisplay.show();

        show();
    }

    public void showWinImage() {
        winImage.show();
    }

    public void showGameOverImage() {
        gameOverImage.show();
    }

    public void removeHearts(int heartsRemaining) {
        int currentNumberOfHearts = heartDisplay.getHeartCount();

        for (int i = 0; i < currentNumberOfHearts - heartsRemaining; i++) {
            heartDisplay.removeHeart();
        }
    }
}
