package dev.vernonlim.cw2024game;

public class UserPlane extends FighterPlane {
    private static final String IMAGE_NAME = "userplane.png";
    private static final double Y_UPPER_BOUND = 0;
    private static final double Y_LOWER_BOUND = 680;
    private static final double X_LEFT_BOUND = 0;
    private static final double X_LOWER_BOUND = 1128;
    private static final double INITIAL_X_POSITION = 5.0;
    private static final double INITIAL_Y_POSITION = 300.0;
    private static final int IMAGE_HEIGHT = 40;
    private static final int VERTICAL_VELOCITY = 24;
    private static final int HORIZONTAL_VELOCITY = 24;
    private static final int PROJECTILE_X_POSITION = 154;
    private static final int PROJECTILE_Y_POSITION_OFFSET = 20;
    private double fireRate = 10.0f;
    private double verticalVelocityMultiplier;
    private double horizontalVelocityMultiplier;
    private int numberOfKills;

    public boolean shouldMoveUp;
    public boolean shouldMoveDown;
    public boolean shouldMoveLeft;
    public boolean shouldMoveRight;
    public boolean shouldFire;
    public boolean shouldFocus;

    public UserPlane(int initialHealth) {
        super(IMAGE_NAME, IMAGE_HEIGHT, INITIAL_X_POSITION, INITIAL_Y_POSITION, initialHealth);
        verticalVelocityMultiplier = 0;
        horizontalVelocityMultiplier = 0;
    }

    @Override
    public void updatePosition(double deltaTime) {
        double initialTranslateY = getTranslateY();
        this.moveVertically(VERTICAL_VELOCITY * verticalVelocityMultiplier * (deltaTime / 50.0f));
        double newPositionY = getLayoutY() + getTranslateY();
        if (newPositionY < Y_UPPER_BOUND || newPositionY > Y_LOWER_BOUND) {
            this.setTranslateY(initialTranslateY);
        }

        double initialTranslateX = getTranslateX();
        this.moveHorizontally(HORIZONTAL_VELOCITY * horizontalVelocityMultiplier * (deltaTime / 50.0f));
        double newPositionX = getLayoutX() + getTranslateX();
        if (newPositionX < X_LEFT_BOUND || newPositionX > X_LOWER_BOUND) {
            this.setTranslateX(initialTranslateX);
        }
    }

    @Override
    public void updateActor(double deltaTime, double currentTime) {
        // null cancelling movement
        if (shouldMoveUp && shouldMoveDown) {
            if (verticalVelocityMultiplier < 0) {
                shouldMoveUp = false;
                verticalVelocityMultiplier = 1;
            } else if (verticalVelocityMultiplier > 0) {
                shouldMoveDown = false;
                verticalVelocityMultiplier = -1;
            } else {
                shouldMoveUp = false;
                verticalVelocityMultiplier = 1;
            }
        } else if (shouldMoveUp) {
            verticalVelocityMultiplier = -1;
        } else if (shouldMoveDown) {
            verticalVelocityMultiplier = 1;
        } else {
            verticalVelocityMultiplier = 0;
        }
        
        if (shouldMoveLeft && shouldMoveRight) {
            if (horizontalVelocityMultiplier < 0) {
                shouldMoveLeft = false;
                horizontalVelocityMultiplier = 1;
            } else if (horizontalVelocityMultiplier > 0) {
                shouldMoveRight = false;
                horizontalVelocityMultiplier = -1;
            } else {
                shouldMoveLeft = false;
                horizontalVelocityMultiplier = 1;
            }
        } else if (shouldMoveLeft) {
            horizontalVelocityMultiplier = -1;
        } else if (shouldMoveRight) {
            horizontalVelocityMultiplier = 1;
        } else {
            horizontalVelocityMultiplier = 0;
        }

        fireRate = 10.0f;

        if (shouldFocus) {
            verticalVelocityMultiplier *= 0.6;
            horizontalVelocityMultiplier *= 0.6;
            fireRate *= 2.0;
        }

        updatePosition(deltaTime);
    }

    @Override
    public ActiveActorDestructible fireProjectile(double currentTime) {
        if (shouldFire && (currentTime - lastFireTime) > (1000.0f / fireRate)) {
            lastFireTime = currentTime;

            return new UserProjectile(PROJECTILE_X_POSITION + getTranslateX(), getProjectileYPosition(PROJECTILE_Y_POSITION_OFFSET));
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
