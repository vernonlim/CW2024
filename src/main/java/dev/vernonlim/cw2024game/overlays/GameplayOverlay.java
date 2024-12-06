package dev.vernonlim.cw2024game.overlays;

import dev.vernonlim.cw2024game.Main;
import dev.vernonlim.cw2024game.elements.Element;
import dev.vernonlim.cw2024game.elements.HeartDisplay;
import dev.vernonlim.cw2024game.elements.configs.OverlayConfig;
import dev.vernonlim.cw2024game.factories.interfaces.OverlayFactory;

public class GameplayOverlay extends FloatingOverlay {
    private static final double HEART_DISPLAY_X_POSITION = 5;
    private static final double HEART_DISPLAY_Y_POSITION = 25;
    private final Element winImage;
    private final Element gameOverImage;
    private final HeartDisplay heartDisplay;
    private final OverlayFactory overlayElementFactory;

    public GameplayOverlay(OverlayConfig config, int heartsToDisplay) {
        super(config);

        this.overlayElementFactory = config.getOverlayFactory().withNewRoot(this.container);

        this.heartDisplay = this.overlayElementFactory
                .createHeartDisplay(HEART_DISPLAY_X_POSITION, HEART_DISPLAY_Y_POSITION, heartsToDisplay);
        this.winImage = this.overlayElementFactory
                .createWinImage(Main.SCREEN_WIDTH / 2.0f, Main.SCREEN_HEIGHT / 2.0f);
        this.gameOverImage = this.overlayElementFactory
                .createGameOverImage(Main.SCREEN_WIDTH / 2.0f, Main.SCREEN_HEIGHT / 2.0f);

        this.heartDisplay.show();

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
