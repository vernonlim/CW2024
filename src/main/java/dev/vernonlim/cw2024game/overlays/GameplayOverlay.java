package dev.vernonlim.cw2024game.overlays;

import dev.vernonlim.cw2024game.Main;
import dev.vernonlim.cw2024game.configs.OverlayConfig;
import dev.vernonlim.cw2024game.elements.Element;
import dev.vernonlim.cw2024game.elements.HeartDisplay;
import dev.vernonlim.cw2024game.factories.interfaces.OverlayFactory;

/**
 * An overlay displayed during gameplay, containing the player's health display.
 * <p>
 * This class is modified.
 * Original Code: <a href="https://github.com/kooitt/CW2024/blob/master/src/main/java/com/example/demo/LevelView.java">GitHub</a>
 */
public class GameplayOverlay extends FloatingOverlay {
    /**
     * The offset for the Heart Display to be drawn from the left.
     */
    private static final double HEART_DISPLAY_X_OFFSET = 5;

    /**
     * The offset for the Heart Display to be drawn from the top.
     */
    private static final double HEART_DISPLAY_Y_OFFSET = 25;

    /**
     * The Element for the image to be displayed when winning.
     */
    private final Element winImage;

    /**
     * The Element for the image to be displayed during a game over.
     */
    private final Element gameOverImage;

    /**
     * The Element for the heart display.
     */
    private final HeartDisplay heartDisplay;

    /**
     * Constructs a Gameplay Overlay.
     *
     * @param config          the configuration object containing the necessary data to construct the Overlay
     * @param heartsToDisplay the number of hearts to display - this should match player health
     */
    public GameplayOverlay(OverlayConfig config, int heartsToDisplay) {
        super(config);

        OverlayFactory overlayElementFactory = config.getOverlayFactory().withNewRoot(this.container);

        this.heartDisplay = overlayElementFactory
                .createHeartDisplay(HEART_DISPLAY_X_OFFSET, HEART_DISPLAY_Y_OFFSET, heartsToDisplay);
        this.winImage = overlayElementFactory
                .createWinImage(Main.SCREEN_WIDTH / 2.0f, Main.SCREEN_HEIGHT / 2.0f);
        this.gameOverImage = overlayElementFactory
                .createGameOverImage(Main.SCREEN_WIDTH / 2.0f, Main.SCREEN_HEIGHT / 2.0f);

        this.heartDisplay.show();

        show();
    }

    /**
     * Shows the win image.
     */
    public void showWinImage() {
        winImage.show();
    }

    /**
     * Shows the game over image.
     */
    public void showGameOverImage() {
        gameOverImage.show();
    }

    /**
     * If the number of hearts remaining is lower than the number of hearts, remove them until the number matches.
     *
     * @param heartsRemaining the number of hearts remaining
     */
    public void removeHearts(int heartsRemaining) {
        int currentNumberOfHearts = heartDisplay.getHeartCount();

        for (int i = 0; i < currentNumberOfHearts - heartsRemaining; i++) {
            heartDisplay.removeHeart();
        }
    }
}
