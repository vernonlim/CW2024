package dev.vernonlim.cw2024game.elements.actors;

import javafx.scene.layout.Pane;

public class EnemyPlane extends FighterPlane {
    private static final String IMAGE_NAME = "enemyplane.png";
    private static final int IMAGE_HEIGHT = 54;
    private static final int HORIZONTAL_VELOCITY = -6;
    private static final int INITIAL_HEALTH = 1;
    private static final double FIRE_RATE = 0.01;
    private static final double PROJECTILE_Y_OFFSET = 7.0f;

    public EnemyPlane(Pane root, double initialXPos, double initialYPos) {
        super(root, IMAGE_NAME, IMAGE_HEIGHT, INITIAL_HEALTH);

        setPosition(initialXPos, initialYPos);
    }

    @Override
    public void updatePosition(double deltaTime) {
        moveHorizontally(HORIZONTAL_VELOCITY * (deltaTime / 50.0f));
    }

    @Override
    public ActiveActorDestructible fireProjectile(double currentTime) {
        if ((currentTime - lastFireTime) > 50.0f) {
            lastFireTime = currentTime;

            boolean shouldFire = Math.random() < FIRE_RATE;

            if (shouldFire) {
                return new EnemyProjectile(root, getX() - getHalfWidth(), getY() + PROJECTILE_Y_OFFSET);
            }
        }

        return null;
    }

    @Override
    public void updateActor(double deltaTime, double currentTime) {
        updatePosition(deltaTime);
    }
}
