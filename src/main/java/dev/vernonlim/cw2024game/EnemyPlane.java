package dev.vernonlim.cw2024game;

public class EnemyPlane extends FighterPlane {
    private static final String IMAGE_NAME = "enemyplane.png";
    private static final int IMAGE_HEIGHT = 54;
    private static final int HORIZONTAL_VELOCITY = -6;
    private static final double PROJECTILE_X_POSITION_OFFSET = -100.0;
    private static final double PROJECTILE_Y_POSITION_OFFSET = 50.0;
    private static final int INITIAL_HEALTH = 1;
    private static final double FIRE_RATE = 0.01;

    public EnemyPlane(double initialXPos, double initialYPos) {
        super(IMAGE_NAME, IMAGE_HEIGHT, initialXPos, initialYPos, INITIAL_HEALTH);
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
                double projectileXPosition = getProjectileXPosition(PROJECTILE_X_POSITION_OFFSET);
                double projectileYPosition = getProjectileYPosition(PROJECTILE_Y_POSITION_OFFSET);
                return new EnemyProjectile(projectileXPosition, projectileYPosition);
            }
        }

        return null;
    }

    @Override
    public void updateActor(double deltaTime, double currentTime) {
        updatePosition(deltaTime);
    }
}
