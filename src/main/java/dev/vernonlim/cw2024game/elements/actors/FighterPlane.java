package dev.vernonlim.cw2024game.elements.actors;

import dev.vernonlim.cw2024game.elements.ProjectileListener;
import dev.vernonlim.cw2024game.elements.configs.FighterPlaneConfig;
import dev.vernonlim.cw2024game.elements.strategies.PlaneStrategy;
import dev.vernonlim.cw2024game.factories.interfaces.ProjectileFactory;
import javafx.scene.media.AudioClip;

public abstract class FighterPlane extends ActiveActorDestructible {
    protected final ProjectileFactory projectileFactory;
    protected final ProjectileListener projectileListener;
    protected final double projectileYOffset;
    protected final boolean facingRight;
    protected PlaneStrategy planeStrategy;
    protected AudioClip fireSound;
    protected AudioClip deathSound;
    private int health;

    public FighterPlane(FighterPlaneConfig config) {
        super(config);

        this.health = config.getHealth();

        this.fireSound = config.getFireSound();
        this.deathSound = config.getDeathSound();

        this.projectileListener = config.getProjectileListener();
        this.projectileFactory = config.getProjectileFactory();
        this.projectileYOffset = config.getProjectileYOffset();

        this.facingRight = config.isFacingRight();

        this.planeStrategy = config.getPlaneStrategy();
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
