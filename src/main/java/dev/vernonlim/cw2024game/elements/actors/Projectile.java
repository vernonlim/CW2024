package dev.vernonlim.cw2024game.elements.actors;

import dev.vernonlim.cw2024game.Main;
import dev.vernonlim.cw2024game.assets.AssetLoader;
import javafx.scene.layout.Pane;

public abstract class Projectile extends ActiveActorDestructible {
    public Projectile(Pane root, AssetLoader loader, String imageName, int imageHeight, double initialXPos, double initialYPos) {
        super(root, loader, imageName, imageHeight);

        setPosition(initialXPos, initialYPos);
    }

    @Override
    public void takeDamage() {
        this.destroy();
    }

    @Override
    public void updateActor(double deltaTime, double currentTime) {
        double xPosition = view.getBoundsInParent().getCenterX();

        // The inaccuracy with the right side doesn't matter
        if (xPosition < 0 || xPosition > Main.SCREEN_WIDTH) {
            destroy();
        }
    }
}
