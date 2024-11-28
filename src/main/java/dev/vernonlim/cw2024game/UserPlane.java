package dev.vernonlim.cw2024game;

public class UserPlane extends FighterPlane {
    private static final String IMAGE_NAME = "userplane.png";
    private static final double Y_UPPER_BOUND = 0;
    private static final double Y_LOWER_BOUND = 680;
    private static final double INITIAL_X_POSITION = 5.0;
    private static final double INITIAL_Y_POSITION = 300.0;
    private static final int IMAGE_HEIGHT = 40;
    private static final int VERTICAL_VELOCITY = 8;
    private static final int PROJECTILE_X_POSITION = 110;
    private static final int PROJECTILE_Y_POSITION_OFFSET = 20;
    private static final double FIRE_RATE = 10.0f;
    private int velocityMultiplier;
    private int numberOfKills;

    public boolean shouldMoveUp;
    public boolean shouldMoveDown;
    public boolean shouldFire;

    public UserPlane(int initialHealth) {
        super(IMAGE_NAME, IMAGE_HEIGHT, INITIAL_X_POSITION, INITIAL_Y_POSITION, initialHealth);
        velocityMultiplier = 0;
    }

    @Override
    public void updatePosition(double deltaTime) {
        double initialTranslateY = getTranslateY();
        this.moveVertically(VERTICAL_VELOCITY * velocityMultiplier * (deltaTime / 50.0f));
        double newPosition = getLayoutY() + getTranslateY();
        if (newPosition < Y_UPPER_BOUND || newPosition > Y_LOWER_BOUND) {
            this.setTranslateY(initialTranslateY);
        }
    }

    @Override
    public void updateActor(double deltaTime, double currentTime) {
        velocityMultiplier = shouldMoveUp ? -1 : (shouldMoveDown ? 1 : 0);

        updatePosition(deltaTime);
    }

    @Override
    public ActiveActorDestructible fireProjectile(double currentTime) {
        if (shouldFire && (currentTime - lastFireTime) > (1000.0f / FIRE_RATE)) {
            lastFireTime = currentTime;

            return new UserProjectile(PROJECTILE_X_POSITION, getProjectileYPosition(PROJECTILE_Y_POSITION_OFFSET));
        }

        return null;
    }

    public int getNumberOfKills() {
        return numberOfKills;
    }

    public void incrementKillCount() {
        numberOfKills++;
    }
}
