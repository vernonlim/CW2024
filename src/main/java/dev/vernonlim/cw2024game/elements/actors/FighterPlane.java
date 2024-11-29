package dev.vernonlim.cw2024game.elements.actors;

import javafx.scene.layout.Pane;

public abstract class FighterPlane extends ActiveActorDestructible {
    private int health;
    protected double lastFireTime;

    public FighterPlane(Pane root, String imageName, int imageHeight, int health) {
        super(root, imageName, imageHeight);
        this.health = health;

        this.lastFireTime = -99999;
    }

    public abstract ActiveActorDestructible fireProjectile(double currentTime);

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
