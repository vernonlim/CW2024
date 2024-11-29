package dev.vernonlim.cw2024game.overlays;

import dev.vernonlim.cw2024game.actors.ShieldImage;
import javafx.scene.layout.Pane;

public class LevelViewLevelTwo extends LevelView {
    private static final int SHIELD_X_POSITION = 1150;
    private static final int SHIELD_Y_POSITION = 500;
    private final Pane root;
    private final ShieldImage shieldImage;

    public LevelViewLevelTwo(Pane root, int heartsToDisplay) {
        super(root, heartsToDisplay);
        this.root = root;
        this.shieldImage = new ShieldImage(SHIELD_X_POSITION, SHIELD_Y_POSITION);
    }

    @Override
    public void showInitialImages() {
        super.showInitialImages();
        root.getChildren().add(shieldImage);
    }

    public void showShield() {
        shieldImage.showShield();
    }

    public void hideShield() {
        shieldImage.hideShield();
    }
}
