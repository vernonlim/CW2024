package dev.vernonlim.cw2024game.actors;

import dev.vernonlim.cw2024game.InputManager;

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

    InputManager inputManager;

    private int lastVerticalMultipler;
    private int lastHorizontalMultiplier;

    public UserPlane(int initialHealth, InputManager inputManager) {
        super(IMAGE_NAME, IMAGE_HEIGHT, INITIAL_X_POSITION, INITIAL_Y_POSITION, initialHealth);

        this.inputManager = inputManager;

        verticalVelocityMultiplier = 0;
        horizontalVelocityMultiplier = 0;

        lastVerticalMultipler = -1;
        lastHorizontalMultiplier = -1;
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
        boolean down = inputManager.isDownPressed();
        boolean up = inputManager.isUpPressed();
        boolean left = inputManager.isLeftPressed();
        boolean right = inputManager.isRightPressed();
        boolean focus = inputManager.isFocusPressed();

        // null cancelling movement
        if (down && up) {
            verticalVelocityMultiplier = -lastVerticalMultipler;
        } else if (inputManager.isUpPressed()) {
            lastVerticalMultipler = -1;
            verticalVelocityMultiplier = -1;
        } else if (down) {
            lastVerticalMultipler = 1;
            verticalVelocityMultiplier = 1;
        } else {
            verticalVelocityMultiplier = 0;
        }
        
        if (left && right) {
            horizontalVelocityMultiplier = -lastHorizontalMultiplier;
        } else if (left) {
            lastHorizontalMultiplier = -1;
            horizontalVelocityMultiplier = -1;
        } else if (right) {
            lastHorizontalMultiplier = 1;
            horizontalVelocityMultiplier = 1;
        } else {
            horizontalVelocityMultiplier = 0;
        }

        fireRate = 10.0f;

        if (focus) {
            verticalVelocityMultiplier *= 0.6;
            horizontalVelocityMultiplier *= 0.6;
            fireRate *= 2.0;
        }

        updatePosition(deltaTime);
    }

    @Override
    public ActiveActorDestructible fireProjectile(double currentTime) {
        if (inputManager.isFirePressed() && (currentTime - lastFireTime) > (1000.0f / fireRate)) {
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
