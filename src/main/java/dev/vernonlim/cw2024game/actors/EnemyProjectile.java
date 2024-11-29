package dev.vernonlim.cw2024game.actors;

public class EnemyProjectile extends Projectile {
    private static final String IMAGE_NAME = "enemyFire.png";
    private static final int IMAGE_HEIGHT = 32;
    private static final int HORIZONTAL_VELOCITY = -10;

    public EnemyProjectile(double initialXPos, double initialYPos) {
        super(IMAGE_NAME, IMAGE_HEIGHT, initialXPos, initialYPos);
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
