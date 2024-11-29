package dev.vernonlim.cw2024game.elements.actors;

import javafx.scene.layout.Pane;

public class BossProjectile extends Projectile {
    private static final String IMAGE_NAME = "fireball.png";
    private static final int IMAGE_HEIGHT = 75;
    private static final int HORIZONTAL_VELOCITY = -15;
    private static final int INITIAL_X_POSITION = 950;

    public BossProjectile(Pane root, double initialYPos) {
        super(root, IMAGE_NAME, IMAGE_HEIGHT, INITIAL_X_POSITION, initialYPos);
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
