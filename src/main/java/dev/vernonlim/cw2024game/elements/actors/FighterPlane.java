package dev.vernonlim.cw2024game.elements.actors;

import dev.vernonlim.cw2024game.configs.FighterPlaneConfig;
import dev.vernonlim.cw2024game.elements.ProjectileListener;
import dev.vernonlim.cw2024game.elements.strategies.Firing;
import dev.vernonlim.cw2024game.factories.interfaces.ProjectileFactory;
import javafx.scene.media.AudioClip;

/**
 * An abstract class representing an ActiveActorDestructible that represents a Fighter Plane.
 */
public abstract class FighterPlane extends ActiveActorDestructible {
    /**
     * The ProjectileFactory for this FighterPlane used for creating its projectiles.
     */
    protected final ProjectileFactory projectileFactory;

    /**
     * The ProjectileListener for this FighterPlane used for firing its projectiles.
     */
    protected final ProjectileListener projectileListener;

    /**
     * The Y offset of the projectiles to be fired.
     */
    protected final double projectileYOffset;

    /**
     * Indicates whether the Fighter Plane is facing right.
     */
    protected final boolean facingRight;

    /**
     * The Firing strategy for this FighterPlane.
     */
    protected Firing firingStrategy;

    /**
     * The sound to play after firing a projectile.
     */
    protected final AudioClip fireSound;

    /**
     * The sound to play on destruction.
     */
    protected final AudioClip deathSound;

    /**
     * The health of the FighterPlane.
     */
    protected int health;

    /**
     * Constructs a FighterPlane with the given params.
     *
     * @param config the configuration object containing the necessary data to construct the FighterPlane
     */
    public FighterPlane(FighterPlaneConfig config) {
        super(config);

        this.health = config.getHealth();

        this.fireSound = config.getFireSound();
        this.deathSound = config.getDeathSound();

        this.projectileListener = config.getProjectileListener();
        this.projectileFactory = config.getProjectileFactory();
        this.projectileYOffset = config.getProjectileYOffset();

        this.facingRight = config.isFacingRight();

        this.firingStrategy = config.getFiring();
    }

    /**
     * Sets the Firing strategy of this FighterPlane.
     *
     * @param firingStrategy the Firing strategy to be set
     */
    public void setFiringStrategy(Firing firingStrategy) {
        this.firingStrategy = firingStrategy;
    }

    @Override
    public void updateActor(double deltaTime, double currentTime) {
        super.updateActor(deltaTime, currentTime);

        firingStrategy.updateStrategyState(deltaTime, currentTime);

        boolean shouldFire = firingStrategy.shouldFire();
        if (shouldFire) {
            fireProjectile(firingStrategy.getProjectileCode());
        }
    }

    /**
     * Fires a projectile with the given code.
     *
     * @param code the ProjectileCode of the projectile to be fired
     */
    public void fireProjectile(ProjectileCode code) {
        projectileListener.onFire(createProjectile(code));

        fireSound.play();
    }

    /**
     * Creates a projectile with the given code at the right position.
     *
     * @param code the ProjectileCode of the projectile to be fired
     * @return a Projectile of the right type in the right position
     */
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

    /**
     * Indicates whether the FighterPlane's health is below zero.
     *
     * @return true if the FighterPlane's health is below zero, false otherwise
     */
    public boolean healthBelowZero() {
        return health <= 0;
    }

    /**
     * Gets the FighterPlane's remaining health.
     *
     * @return the remaining health of the FighterPlane
     */
    public int getHealth() {
        return health;
    }
}
