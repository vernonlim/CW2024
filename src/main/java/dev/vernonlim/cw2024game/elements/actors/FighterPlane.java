package dev.vernonlim.cw2024game.elements.actors;

import dev.vernonlim.cw2024game.elements.ProjectileListener;
import dev.vernonlim.cw2024game.elements.strategies.PlaneStrategy;
import dev.vernonlim.cw2024game.factories.interfaces.ProjectileFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;

public abstract class FighterPlane extends ActiveActorDestructible {
    protected final ProjectileFactory projectileFactory;
    protected final ProjectileListener projectileListener;
    private int health;

    protected final double projectileYOffset;
    protected final boolean facingRight;

    protected PlaneStrategy planeStrategy;

    protected AudioClip fireSound;
    protected AudioClip deathSound;

    public FighterPlane(PlaneStrategy planeStrategy, ProjectileFactory projectileFactory, Pane root, ProjectileListener projectileListener, ImageView imageView, AudioClip fireSound, AudioClip deathSound, int health, double speed, double projectileYOffset, boolean facingRight, boolean alwaysInBounds) {
        super(planeStrategy, root, imageView, speed, alwaysInBounds);

        this.fireSound = fireSound;
        this.deathSound = deathSound;

        this.health = health;

        this.projectileListener = projectileListener;
        this.projectileFactory = projectileFactory;

        this.projectileYOffset = projectileYOffset;
        this.facingRight = facingRight;

        this.planeStrategy = planeStrategy;
    }

    public void setPlaneStrategy(PlaneStrategy strategy) {
        this.planeStrategy = strategy;
        this.actorStrategy = strategy;
    }

    @Override
    public void updateActor(double deltaTime, double currentTime) {
        super.updateActor(deltaTime, currentTime);

        boolean shouldFire = planeStrategy.shouldFire();
        if (shouldFire) {
            fireProjectile(planeStrategy.getProjectileCode());
        }
    }

    public void fireProjectile(ProjectileCode code) {
        projectileListener.onFire(createProjectile(code));

        fireSound.play();
    }

    public Projectile createProjectile(ProjectileCode code) {
        double x = getX();
        x += facingRight ? getHalfWidth() : -getHalfWidth();

        return projectileFactory.createProjectile(code, x, getY() + projectileYOffset);
    }

    @Override
    public void takeDamage(int damage) {
        health -= damage;
        if (healthBelowZero()) {
            this.destroy();
        }
    }

    @Override
    public void destroy() {
        super.destroy();

        deathSound.play();
    }

    public boolean healthBelowZero() {
        return health <= 0;
    }

    public int getHealth() {
        return health;
    }
}
