package dev.vernonlim.cw2024game.actors;

public abstract class FighterPlane extends ActiveActorDestructible {
    private int health;
    protected double lastFireTime;

    public FighterPlane(String imageName, int imageHeight, double initialXPos, double initialYPos, int health) {
        super(imageName, imageHeight, initialXPos, initialYPos);
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
        return getLayoutX() + getTranslateX() + xPositionOffset;
    }

    protected double getProjectileYPosition(double yPositionOffset) {
        return getLayoutY() + getTranslateY() + yPositionOffset;
    }

    private boolean healthAtZero() {
        return health == 0;
    }

    public int getHealth() {
        return health;
    }
}
