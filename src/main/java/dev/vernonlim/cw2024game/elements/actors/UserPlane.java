package dev.vernonlim.cw2024game.elements.actors;

import dev.vernonlim.cw2024game.Main;
import dev.vernonlim.cw2024game.elements.ProjectileCode;
import dev.vernonlim.cw2024game.elements.Vector;
import dev.vernonlim.cw2024game.elements.strategies.PlaneStrategy;
import dev.vernonlim.cw2024game.elements.strategies.UserPlaneStrategy;
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

    protected AudioClip fireSound;

    public UserPlane(ProjectileFactory projectileFactory, Pane root, ProjectileListener projectileListener, InputManager input, ImageView imageView, AudioClip fireSound, int initialHealth, double speed, double projectileYOffset) {
        super(projectileFactory, root, projectileListener, imageView, initialHealth, speed, projectileYOffset, FACING_RIGHT, ALWAYS_IN_BOUNDS);

        this.fireSound = fireSound;
        fireSound.setVolume(0.4);

        setXFromLeft(5);
        setY(Main.SCREEN_HEIGHT / 2.0f);

        this.input = input;

        initializeStrategy();

        show();
    }

    protected abstract void initializeStrategy();

    @Override
    public void fireProjectile(ProjectileCode code) {
        super.fireProjectile(code);

        fireSound.play();
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
