package dev.vernonlim.cw2024game.elements.actors;

import dev.vernonlim.cw2024game.assets.AssetLoader;
import javafx.scene.layout.Pane;
import javafx.scene.image.ImageView;

public class BossProjectile extends Projectile {
    private static final int HORIZONTAL_VELOCITY = -15;

    public BossProjectile(Pane root, ImageView imageView) {
        super(root, imageView);
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
