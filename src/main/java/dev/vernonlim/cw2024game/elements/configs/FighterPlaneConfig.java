package dev.vernonlim.cw2024game.elements.configs;

import dev.vernonlim.cw2024game.elements.ProjectileListener;
import dev.vernonlim.cw2024game.elements.strategies.PlaneStrategy;
import dev.vernonlim.cw2024game.factories.interfaces.ProjectileFactory;
import javafx.scene.layout.Pane;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;

public class FighterPlaneConfig extends ActiveActorDestructibleConfig {
    private PlaneStrategy planeStrategy;
    private ProjectileFactory projectileFactory;
    private ProjectileListener projectileListener;
    private AudioClip fireSound;
    private AudioClip deathSound;
    private int health;
    private double projectileYOffset;
    private boolean isFacingRight;

    public FighterPlaneConfig(Pane root, ProjectileFactory projectileFactory, ProjectileListener projectileListener) {
        super(root);

        this.projectileFactory = projectileFactory;
        this.projectileListener = projectileListener;

        this.health = 5;
        this.projectileYOffset = 0.0f;
        this.isFacingRight = true;
    }

    public PlaneStrategy getPlaneStrategy() {
        return planeStrategy;
    }

    public void setPlaneStrategy(PlaneStrategy planeStrategy) {
        this.planeStrategy = planeStrategy;
        this.setActorStrategy(this.planeStrategy);
    }

    public ProjectileFactory getProjectileFactory() {
        return projectileFactory;
    }

    public void setProjectileFactory(ProjectileFactory projectileFactory) {
        this.projectileFactory = projectileFactory;
    }

    public ProjectileListener getProjectileListener() {
        return projectileListener;
    }

    public void setProjectileListener(ProjectileListener projectileListener) {
        this.projectileListener = projectileListener;
    }

    public AudioClip getFireSound() {
        return fireSound;
    }

    public void setFireSound(AudioClip fireSound) {
        this.fireSound = fireSound;
    }

    public AudioClip getDeathSound() {
        return deathSound;
    }

    public void setDeathSound(AudioClip deathSound) {
        this.deathSound = deathSound;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public double getProjectileYOffset() {
        return projectileYOffset;
    }

    public void setProjectileYOffset(double projectileYOffset) {
        this.projectileYOffset = projectileYOffset;
    }

    public boolean isFacingRight() {
        return isFacingRight;
    }

    public void setFacingRight(boolean facingRight) {
        isFacingRight = facingRight;
    }
}
