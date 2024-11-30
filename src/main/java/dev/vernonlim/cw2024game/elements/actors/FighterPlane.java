package dev.vernonlim.cw2024game.elements.actors;

import dev.vernonlim.cw2024game.elements.ProjectileListener;
import dev.vernonlim.cw2024game.elements.factories.ElementFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public abstract class FighterPlane extends ActiveActorDestructible {
    protected final ElementFactory elementFactory;
    private final ProjectileListener projectileListener;
    protected double lastFireTime;
    private int health;

    public FighterPlane(ElementFactory elementFactory, Pane root, ProjectileListener projectileListener, ImageView imageView, int health) {
        super(root, imageView);
        this.health = health;

        this.projectileListener = projectileListener;
        this.elementFactory = elementFactory;

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
