package dev.vernonlim.cw2024game.elements.actors;

import dev.vernonlim.cw2024game.elements.ProjectileListener;
import dev.vernonlim.cw2024game.factories.interfaces.ProjectileFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public abstract class FighterPlane extends ActiveActorDestructible {
    protected final ProjectileFactory projectileFactory;
    private final ProjectileListener projectileListener;
    protected double lastFireTime;
    private int health;
    protected boolean shouldFire;

    public FighterPlane(ProjectileFactory projectileFactory, Pane root, ProjectileListener projectileListener, ImageView imageView, int health) {
        super(root, imageView);
        this.health = health;

        this.projectileListener = projectileListener;
        this.projectileFactory = projectileFactory;

        this.lastFireTime = 0;
        this.shouldFire = false;
    }

    @Override
    public void updateActor(double deltaTime, double currentTime) {
        if (shouldFire) {
            projectileListener.onFire(createProjectile());
            shouldFire = false;
        }
    }

    public void fireProjectile() {
        this.shouldFire = true;
    }

    public abstract Projectile createProjectile();

    @Override
    public void takeDamage() {
        health--;
        if (healthAtZero()) {
            this.destroy();
        }
    }

    private boolean healthAtZero() {
        return health == 0;
    }

    public int getHealth() {
        return health;
    }
}
