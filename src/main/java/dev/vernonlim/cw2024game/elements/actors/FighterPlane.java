package dev.vernonlim.cw2024game.elements.actors;

import dev.vernonlim.cw2024game.assets.AssetLoader;
import dev.vernonlim.cw2024game.elements.ProjectileListener;
import javafx.scene.layout.Pane;

public abstract class FighterPlane extends ActiveActorDestructible {
    private int health;
    protected double lastFireTime;

    private ProjectileListener projectileListener;

    public FighterPlane(Pane root, AssetLoader loader, ProjectileListener projectileListener, String imageName, int imageHeight, int health) {
        super(root, loader, imageName, imageHeight);
        this.health = health;

        this.projectileListener = projectileListener;

        this.lastFireTime = -99999;
    }

    public void fireProjectile() {
        projectileListener.onFire(createProjectile());
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
