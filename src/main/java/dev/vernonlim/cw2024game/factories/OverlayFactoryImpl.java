package dev.vernonlim.cw2024game.factories;

import dev.vernonlim.cw2024game.assets.AssetLoader;
import dev.vernonlim.cw2024game.elements.*;
import dev.vernonlim.cw2024game.factories.interfaces.OverlayFactory;
import dev.vernonlim.cw2024game.overlays.GameplayOverlay;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class OverlayFactoryImpl extends SharedFactoryImpl implements OverlayFactory {
    public OverlayFactoryImpl(Pane root, AssetLoader loader) {
        super(root, loader);
    }

    public OverlayFactory withNewRoot(Pane newRoot) {
        return new OverlayFactoryImpl(newRoot, loader);
    }

    public Element createGameOverImage(double xPosition, double yPosition) {
        ImageView imageView = makeView("gameover");

        GameOverImage image = new GameOverImage(root, imageView);
        image.setPosition(xPosition, yPosition);

        return image;
    }

    public Element createHeart() {
        ImageView imageView = makeView("heart");
        imageView.setFitHeight(50);

        return new Heart(root, imageView);
    }

    public Element createWinImage(double xPosition, double yPosition) {
        ImageView imageView = makeView("youwin");
        imageView.setFitHeight(500);
        imageView.setFitWidth(600);

        WinImage winImage = new WinImage(root, imageView);
        winImage.setPosition(xPosition, yPosition);

        return winImage;
    }

    public HeartDisplay createHeartDisplay(double xPosition, double yPosition, int heartsToDisplay) {
        return new HeartDisplay(this, root, xPosition, yPosition, heartsToDisplay);
    }

    public GameplayOverlay createGameplayOverlay(int heartsToDisplay) {
        return new GameplayOverlay(this, root, heartsToDisplay);
    }
}
