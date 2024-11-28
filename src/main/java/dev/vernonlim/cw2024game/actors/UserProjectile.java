package dev.vernonlim.cw2024game.actors;

public class UserProjectile extends Projectile {
    private static final String IMAGE_NAME = "userfire.png";
    private static final int IMAGE_HEIGHT = 12;
    private static final int HORIZONTAL_VELOCITY = 100;

    public UserProjectile(double initialXPos, double initialYPos) {
        super(IMAGE_NAME, IMAGE_HEIGHT, initialXPos, initialYPos);
    }

    @Override
    public void updatePosition(double deltaTime) {
        moveHorizontally(HORIZONTAL_VELOCITY * (deltaTime / 50.0f));
    }

    @Override
    public void updateActor(double deltaTime, double currentTime) {
        updatePosition(deltaTime);
    }
}
