package dev.vernonlim.cw2024game.elements.actors;

import dev.vernonlim.cw2024game.assets.AssetLoader;
import dev.vernonlim.cw2024game.elements.ProjectileListener;
import dev.vernonlim.cw2024game.elements.factories.ActorFactory;
import javafx.scene.layout.Pane;

public abstract class FighterPlane extends ActiveActorDestructible {
    protected final ActorFactory actorFactory;
    private final ProjectileListener projectileListener;
    protected double lastFireTime;
    private int health;

    public FighterPlane(ActorFactory actorFactory, Pane root, AssetLoader loader, ProjectileListener projectileListener, String imageName, int imageHeight, int health) {
        super(root, loader, imageName, imageHeight);
        this.health = health;

        this.projectileListener = projectileListener;
        this.actorFactory = actorFactory;

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
