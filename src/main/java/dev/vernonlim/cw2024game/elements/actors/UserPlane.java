package dev.vernonlim.cw2024game.elements.actors;

import dev.vernonlim.cw2024game.Main;
import dev.vernonlim.cw2024game.elements.strategies.PlaneStrategy;
import dev.vernonlim.cw2024game.factories.interfaces.ProjectileFactory;
import javafx.scene.image.ImageView;
import dev.vernonlim.cw2024game.elements.ProjectileListener;
import dev.vernonlim.cw2024game.managers.InputManager;
import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;

public abstract class UserPlane extends FighterPlane {
    private static final boolean FACING_RIGHT = true;
    private static final boolean ALWAYS_IN_BOUNDS = true;

    protected InputManager input;

    protected int numberOfKills;

    protected int damageToTake;
    protected double lastDamage;
    protected boolean takeDamage;

    public UserPlane(PlaneStrategy planeStrategy, ProjectileFactory projectileFactory, Pane root, ProjectileListener projectileListener, InputManager input, ImageView imageView, AudioClip fireSound, AudioClip damageSound, int initialHealth, double speed, double projectileYOffset) {
        super(planeStrategy, projectileFactory, root, projectileListener, imageView, fireSound, damageSound, initialHealth, speed, projectileYOffset, FACING_RIGHT, ALWAYS_IN_BOUNDS);

        setXFromLeft(5);
        setY(Main.SCREEN_HEIGHT / 2.0f);

        this.input = input;

        this.damageToTake = 1;
        this.lastDamage = 0;

        show();
    }

    @Override
    public void updateActor(double deltaTime, double currentTime) {
        super.updateActor(deltaTime, currentTime);

        if (takeDamage) {
            if (currentTime - lastDamage > 500.0f) {
                lastDamage = currentTime;

                super.takeDamage(damageToTake);
                deathSound.play();
            }

            takeDamage = false;
        }
    }

    @Override
    public void takeDamage(int damage) {
        takeDamage = true;
        damageToTake = damage;
    }

    @Override
    public void fireProjectile(ProjectileCode code) {
        super.fireProjectile(code);
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
