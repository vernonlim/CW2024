package dev.vernonlim.cw2024game.elements.actors;

import dev.vernonlim.cw2024game.InputManager;
import dev.vernonlim.cw2024game.Main;
import javafx.scene.layout.Pane;

public class UserPlane extends FighterPlane {
    private static final String IMAGE_NAME = "userplane.png";
    private static final int IMAGE_HEIGHT = 40;
    private static final double SPEED = 24.0f;
    private static final double PROJECTILE_Y_OFFSET = 7.0f;
    private double fireRate = 10.0f;
    private double verticalVelocityMultiplier;
    private double horizontalVelocityMultiplier;
    private int numberOfKills;

    InputManager inputManager;

    private int lastVerticalMultipler;
    private int lastHorizontalMultiplier;

    private double upperBound;
    private double lowerBound;
    private double leftBound;
    private double rightBound;

    public UserPlane(Pane root, int initialHealth, InputManager inputManager) {
        super(root, IMAGE_NAME, IMAGE_HEIGHT, initialHealth);

        setXFromLeft(5);
        setY(Main.SCREEN_HEIGHT / 2.0f);

        this.inputManager = inputManager;

        verticalVelocityMultiplier = 0;
        horizontalVelocityMultiplier = 0;

        lastVerticalMultipler = -1;
        lastHorizontalMultiplier = -1;

        upperBound = getHalfHeight();
        lowerBound = Main.SCREEN_HEIGHT - getHalfHeight();
        leftBound = getHalfWidth();
        rightBound = Main.SCREEN_WIDTH - getHalfWidth();

        System.out.println(upperBound);
    }

    @Override
    public void updatePosition(double deltaTime) {
        move(
                SPEED * horizontalVelocityMultiplier * (deltaTime / 50.0f),
                SPEED * verticalVelocityMultiplier * (deltaTime / 50.0f)
        );

        ensureInBounds();
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

            return new UserProjectile(root, getX() + getHalfWidth(), getY() + PROJECTILE_Y_OFFSET);
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
