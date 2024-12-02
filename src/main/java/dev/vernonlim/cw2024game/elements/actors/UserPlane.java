package dev.vernonlim.cw2024game.elements.actors;

import dev.vernonlim.cw2024game.Main;
import javafx.scene.image.ImageView;
import dev.vernonlim.cw2024game.elements.ProjectileListener;
import dev.vernonlim.cw2024game.factories.ElementFactory;
import dev.vernonlim.cw2024game.managers.InputManager;
import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;

public class UserPlane extends FighterPlane {
    private static final double SPEED = 24.0f;
    private static final double PROJECTILE_Y_OFFSET = 7.0f;
    private final InputManager inputManager;
    private double fireRate = 10.0f;
    private double verticalVelocityMultiplier;
    private double horizontalVelocityMultiplier;
    private int numberOfKills;
    private int lastVerticalMultipler;
    private int lastHorizontalMultiplier;

    private AudioClip fireSound;

    public UserPlane(ElementFactory elementFactory, Pane root, ProjectileListener projectileListener, InputManager inputManager, ImageView imageView, AudioClip fireSound, int initialHealth) {
        super(elementFactory, root, projectileListener, imageView, initialHealth);

        this.fireSound = fireSound;
        fireSound.setVolume(0.4);

        setXFromLeft(5);
        setY(Main.SCREEN_HEIGHT / 2.0f);

        this.inputManager = inputManager;

        verticalVelocityMultiplier = 0;
        horizontalVelocityMultiplier = 0;

        lastVerticalMultipler = -1;
        lastHorizontalMultiplier = -1;

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

        if (inputManager.isFirePressed() && (currentTime - lastFireTime) > (1000.0f / fireRate)) {
            lastFireTime = currentTime;

            fireProjectile();
        }

        updatePosition(deltaTime);
    }

    @Override
    public void fireProjectile() {
        super.fireProjectile();

        fireSound.play();
    }

    @Override
    public Projectile createProjectile() {
        return elementFactory.createUserProjectile(getX() + getHalfWidth(), getY() + PROJECTILE_Y_OFFSET);
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
