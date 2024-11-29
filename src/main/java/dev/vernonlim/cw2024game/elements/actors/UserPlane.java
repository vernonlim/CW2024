package dev.vernonlim.cw2024game.elements.actors;

import dev.vernonlim.cw2024game.assets.AssetLoader;
import dev.vernonlim.cw2024game.elements.ProjectileListener;
import dev.vernonlim.cw2024game.elements.factories.ActorFactory;
import dev.vernonlim.cw2024game.input.Input;
import dev.vernonlim.cw2024game.Main;
import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
import javafx.scene.layout.Pane;

public class UserPlane extends FighterPlane {
    private static final String IMAGE_NAME = "userplane";
    private static final int IMAGE_HEIGHT = 40;
    private static final double SPEED = 24.0f;
    private static final double PROJECTILE_Y_OFFSET = 7.0f;
    private double fireRate = 10.0f;
    private double verticalVelocityMultiplier;
    private double horizontalVelocityMultiplier;
    private int numberOfKills;

    private final Input input;

    private int lastVerticalMultipler;
    private int lastHorizontalMultiplier;

    private double upperBound;
    private double lowerBound;
    private double leftBound;
    private double rightBound;

    public UserPlane(ActorFactory actorFactory, Pane root, AssetLoader loader, ProjectileListener projectileListener, Input input, int initialHealth) {
        super(actorFactory, root, loader, projectileListener, IMAGE_NAME, IMAGE_HEIGHT, initialHealth);

        setXFromLeft(5);
        setY(Main.SCREEN_HEIGHT / 2.0f);

        this.input = input;

        verticalVelocityMultiplier = 0;
        horizontalVelocityMultiplier = 0;

        lastVerticalMultipler = -1;
        lastHorizontalMultiplier = -1;

        upperBound = getHalfHeight();
        lowerBound = Main.SCREEN_HEIGHT - getHalfHeight();
        leftBound = getHalfWidth();
        rightBound = Main.SCREEN_WIDTH - getHalfWidth();

        show();
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
        boolean down = input.isDownPressed();
        boolean up = input.isUpPressed();
        boolean left = input.isLeftPressed();
        boolean right = input.isRightPressed();
        boolean focus = input.isFocusPressed();

        // null cancelling movement
        if (down && up) {
            verticalVelocityMultiplier = -lastVerticalMultipler;
        } else if (input.isUpPressed()) {
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

        if (input.isFirePressed() && (currentTime - lastFireTime) > (1000.0f / fireRate)) {
            lastFireTime = currentTime;

            fireProjectile();
        }

        updatePosition(deltaTime);
    }

    @Override
    public Projectile createProjectile() {
        return actorFactory.createUserProjectile(getX() + getHalfWidth(), getY() + PROJECTILE_Y_OFFSET);
    }

    @Override
    public Bounds getCollisionBounds() {
        return new BoundingBox(getX() - getHalfHeight(), getY() - getHalfHeight(), getHeight(), getHeight());
    }

    public int getNumberOfKills() {
        return numberOfKills;
    }

    public void incrementKillCount() {
        numberOfKills++;
    }
}
