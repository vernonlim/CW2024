package dev.vernonlim.cw2024game.elements.actors;

import dev.vernonlim.cw2024game.elements.ProjectileCode;
import dev.vernonlim.cw2024game.elements.ProjectileListener;
import dev.vernonlim.cw2024game.elements.Vector;
import dev.vernonlim.cw2024game.elements.strategies.EnemyPlaneStrategy;
import dev.vernonlim.cw2024game.elements.strategies.PlaneStrategy;
import dev.vernonlim.cw2024game.factories.interfaces.ProjectileFactory;
import javafx.scene.layout.Pane;
import javafx.scene.image.ImageView;

public class EnemyPlane extends FighterPlane {
    private static final int INITIAL_HEALTH = 1;
    private static final double SPEED = 5.0f;
    private static final double PROJECTILE_Y_OFFSET = 7.0f;
    private static final boolean FACING_RIGHT = false;
    private static final boolean ALWAYS_IN_BOUNDS = false;

    public EnemyPlane(ProjectileFactory projectileFactory, Pane root, ProjectileListener projectileListener, ImageView imageView) {
        super(projectileFactory, root, projectileListener, imageView, INITIAL_HEALTH, SPEED, PROJECTILE_Y_OFFSET, FACING_RIGHT, ALWAYS_IN_BOUNDS);

        this.planeStrategy = new EnemyPlaneStrategy(this);
    }
}
