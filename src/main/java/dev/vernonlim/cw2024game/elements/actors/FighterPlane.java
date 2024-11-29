package dev.vernonlim.cw2024game.elements.actors;

import javafx.scene.layout.Pane;

public abstract class FighterPlane extends ActiveActorDestructible {
    private int health;
    protected double lastFireTime;

    public FighterPlane(Pane root, String imageName, int imageHeight, double initialXPos, double initialYPos, int health) {
        super(root, imageName, imageHeight, initialXPos, initialYPos);
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

    protected double getProjectileXPosition(double xPositionOffset) {
        return view.getLayoutX() + view.getTranslateX() + xPositionOffset;
    }

    protected double getProjectileYPosition(double yPositionOffset) {
        return view.getLayoutY() + view.getTranslateY() + yPositionOffset;
    }

    private boolean healthAtZero() {
        return health == 0;
    }

    public int getHealth() {
        return health;
    }
}
