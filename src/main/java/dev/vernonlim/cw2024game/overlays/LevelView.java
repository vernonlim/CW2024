package dev.vernonlim.cw2024game.overlays;

import dev.vernonlim.cw2024game.elements.Element;
import dev.vernonlim.cw2024game.elements.GameOverImage;
import dev.vernonlim.cw2024game.elements.HeartDisplay;
import dev.vernonlim.cw2024game.elements.WinImage;
import javafx.scene.layout.Pane;

public class LevelView {
    private static final double HEART_DISPLAY_X_POSITION = 5;
    private static final double HEART_DISPLAY_Y_POSITION = 25;
    private static final int WIN_IMAGE_X_POSITION = 355;
    private static final int WIN_IMAGE_Y_POSITION = 175;
    private static final int LOSS_SCREEN_X_POSITION = -160;
    private static final int LOSS_SCREEN_Y_POSITION = -375;
    private final Pane root;
    private final WinImage winImage;
    private final Element gameOverImage;
    private final HeartDisplay heartDisplay;

    public LevelView(Pane root, int heartsToDisplay) {
        this.root = root;
        this.heartDisplay = new HeartDisplay(root, HEART_DISPLAY_X_POSITION, HEART_DISPLAY_Y_POSITION, heartsToDisplay);
        this.winImage = new WinImage(root, WIN_IMAGE_X_POSITION, WIN_IMAGE_Y_POSITION);
        this.gameOverImage = new GameOverImage(root, LOSS_SCREEN_X_POSITION, LOSS_SCREEN_Y_POSITION);
    }

    public void showInitialImages() {
        showHeartDisplay();
    }

    public void showHeartDisplay() {
        heartDisplay.show();
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
