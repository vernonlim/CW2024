package dev.vernonlim.cw2024game.elements.actors;

import dev.vernonlim.cw2024game.assets.AssetLoader;
import javafx.scene.layout.Pane;

public class UserProjectile extends Projectile {
    private static final String IMAGE_NAME = "userfire";
    private static final int IMAGE_HEIGHT = 12;
    private static final int HORIZONTAL_VELOCITY = 100;

    public UserProjectile(Pane root, AssetLoader loader, double initialXPos, double initialYPos) {
        super(root, loader, IMAGE_NAME, IMAGE_HEIGHT, initialXPos, initialYPos);

        // the projectile has to be in front of the firer
        moveHorizontally(getHalfWidth());
    }

    @Override
    public void updatePosition(double deltaTime) {
        moveHorizontally(HORIZONTAL_VELOCITY * (deltaTime / 50.0f));
    }

    @Override
    public void updateActor(double deltaTime, double currentTime) {
        super.updateActor(deltaTime, currentTime);

        updatePosition(deltaTime);
    }
}
