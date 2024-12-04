package dev.vernonlim.cw2024game.elements.actors;

import dev.vernonlim.cw2024game.elements.ProjectileListener;
import dev.vernonlim.cw2024game.elements.strategies.PlaneStrategy;
import dev.vernonlim.cw2024game.factories.interfaces.ProjectileFactory;
import javafx.scene.layout.Pane;
import javafx.scene.image.ImageView;
import javafx.scene.media.AudioClip;

public class EnemyPlane extends FighterPlane {
    private static final int INITIAL_HEALTH = 10;
    private static final double PROJECTILE_Y_OFFSET = 7.0f;
    private static final boolean FACING_RIGHT = false;
    private static final boolean ALWAYS_IN_BOUNDS = false;

    public EnemyPlane(PlaneStrategy planeStrategy, ProjectileFactory projectileFactory, Pane root, ProjectileListener projectileListener, ImageView imageView, double speed, AudioClip fireSound, AudioClip deathSound) {
        super(planeStrategy, projectileFactory, root, projectileListener, imageView, fireSound, deathSound, INITIAL_HEALTH, speed, PROJECTILE_Y_OFFSET, FACING_RIGHT, ALWAYS_IN_BOUNDS);
    }
}
