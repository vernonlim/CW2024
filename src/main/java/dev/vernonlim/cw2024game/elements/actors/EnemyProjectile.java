package dev.vernonlim.cw2024game.elements.actors;

import javafx.scene.layout.Pane;

public class EnemyProjectile extends Projectile {
    private static final String IMAGE_NAME = "enemyFire.png";
    private static final int IMAGE_HEIGHT = 32;
    private static final int HORIZONTAL_VELOCITY = -10;

    public EnemyProjectile(Pane root, double initialXPos, double initialYPos) {
        super(root, IMAGE_NAME, IMAGE_HEIGHT, initialXPos, initialYPos);

        // to be in front of the enemy plane
        moveHorizontally(-getHalfWidth());
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
