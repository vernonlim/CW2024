package dev.vernonlim.cw2024game.elements.actors;

import dev.vernonlim.cw2024game.assets.AssetLoader;
import javafx.scene.layout.Pane;
import javafx.scene.image.ImageView;

public class EnemyProjectile extends Projectile {
    private static final int HORIZONTAL_VELOCITY = -10;

    public EnemyProjectile(Pane root, ImageView imageView) {
        super(root, imageView, 1);

        // to be in front of the enemy plane
        moveHorizontally(-getHalfWidth());
    }

    @Override
    public void updateActor(double deltaTime, double currentTime) {
        super.updateActor(deltaTime, currentTime);

        moveHorizontally(HORIZONTAL_VELOCITY * (deltaTime / 50.0f));
    }
}
