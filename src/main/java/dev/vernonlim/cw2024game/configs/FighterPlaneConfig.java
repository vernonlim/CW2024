package dev.vernonlim.cw2024game.configs;

import dev.vernonlim.cw2024game.elements.ProjectileListener;
import dev.vernonlim.cw2024game.elements.strategies.Firing;
import dev.vernonlim.cw2024game.factories.interfaces.ProjectileFactory;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;

/**
 * A data class used for streamlining FighterPlane creation
 */
public class FighterPlaneConfig extends ActiveActorDestructibleConfig {
    /**
     * The Firing strategy for the FighterPlane.
     */
    private Firing firing;

    /**
     * The ProjectileFactory for the FighterPlane.
     */
    private ProjectileFactory projectileFactory;

    /**
     * The ProjectileListener for the FighterPlane.
     */
    private ProjectileListener projectileListener;

    /**
     * The sound the FighterPlane plays on fire.
     */
    private AudioClip fireSound;

    /**
     * The sound the FighterPlane plays on destruction.
     */
    private AudioClip deathSound;

    /**
     * The FighterPlane's health
     */
    private int health;

    /**
     * The FighterPlane's Projectile Y Offset
     */
    private double projectileYOffset;

    /**
     * Indicates if the FighterPlane is facing right.
     */
    private boolean isFacingRight;

    /**
     * Constructs a FighterPlaneConfig with the given params.
     *
     * @param root               the root Pane on which the FighterPlane is based
     * @param projectileFactory  the ProjectileFactory for the FighterPLane
     * @param projectileListener the ProjectileListener for the FighterPlane
     */
    public FighterPlaneConfig(Pane root, ProjectileFactory projectileFactory, ProjectileListener projectileListener) {
        super(root);

        this.projectileFactory = projectileFactory;
        this.projectileListener = projectileListener;

        this.health = 5;
        this.projectileYOffset = 0.0f;
        this.isFacingRight = true;
    }

    /**
     * Gets the Firing strategy.
     *
     * @return the Firing strategy
     */
    public Firing getFiring() {
        return firing;
    }

    /**
     * Sets the Firing strategy.
     *
     * @param firing the Firing strategy to set
     */
    public void setFiring(Firing firing) {
        this.firing = firing;
    }

    /**
     * Gets the ProjectileFactory.
     *
     * @return the ProjectileFactory
     */
    public ProjectileFactory getProjectileFactory() {
        return projectileFactory;
    }

    /**
     * Sets the ProjectileFactory.
     *
     * @param projectileFactory the ProjectileFactory to set
     */
    public void setProjectileFactory(ProjectileFactory projectileFactory) {
        this.projectileFactory = projectileFactory;
    }

    /**
     * Gets the ProjectileListener.
     *
     * @return the ProjectileListener
     */
    public ProjectileListener getProjectileListener() {
        return projectileListener;
    }

    /**
     * Sets the ProjectileListener.
     *
     * @param projectileListener the ProjectileListener to set
     */
    public void setProjectileListener(ProjectileListener projectileListener) {
        this.projectileListener = projectileListener;
    }

    /**
     * Gets the fire sound.
     *
     * @return the fire sound
     */
    public AudioClip getFireSound() {
        return fireSound;
    }

    /**
     * Sets the fire sound.
     *
     * @param fireSound the fire sound to set
     */
    public void setFireSound(AudioClip fireSound) {
        this.fireSound = fireSound;
    }

    /**
     * Gets the death sound.
     *
     * @return the death sound
     */
    public AudioClip getDeathSound() {
        return deathSound;
    }

    /**
     * Sets the death sound.
     *
     * @param deathSound the death sound to set
     */
    public void setDeathSound(AudioClip deathSound) {
        this.deathSound = deathSound;
    }

    /**
     * Gets the health.
     *
     * @return the health
     */
    public int getHealth() {
        return health;
    }

    /**
     * Sets the health.
     *
     * @param health the health to set
     */
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * Gets the projectile Y offset.
     *
     * @return the projectile Y offset
     */
    public double getProjectileYOffset() {
        return projectileYOffset;
    }

    /**
     * Sets the projectile Y offset.
     *
     * @param projectileYOffset the projectile Y offset to set
     */
    public void setProjectileYOffset(double projectileYOffset) {
        this.projectileYOffset = projectileYOffset;
    }

    /**
     * Indicates if the plane is facing right.
     *
     * @return true if the plane is facing right, false otherwise
     */
    public boolean isFacingRight() {
        return isFacingRight;
    }

    /**
     * Sets whether the plane is facing right.
     *
     * @param facingRight true if the plane is facing right, false otherwise
     */
    public void setFacingRight(boolean facingRight) {
        isFacingRight = facingRight;
    }
}
