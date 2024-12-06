package dev.vernonlim.cw2024game.elements.actors;

import dev.vernonlim.cw2024game.elements.configs.UserPlaneConfig;
import dev.vernonlim.cw2024game.managers.InputManager;
import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;

public abstract class UserPlane extends FighterPlane {
    protected InputManager input;

    protected int numberOfKills;

    protected int damageToTake;
    protected double lastDamage;
    protected boolean takeDamage;

    public UserPlane(UserPlaneConfig config) {
        super(config);

        this.input = config.getInput();

        this.damageToTake = 1;
        this.lastDamage = 0;

        show();
    }

    @Override
    public void updateActor(double deltaTime, double currentTime) {
        super.updateActor(deltaTime, currentTime);

        if (takeDamage) {
            if (currentTime - lastDamage > 500.0f) {
                lastDamage = currentTime;

                super.takeDamage(damageToTake);
                deathSound.play();
            }

            takeDamage = false;
        }
    }

    @Override
    public void takeDamage(int damage) {
        takeDamage = true;
        damageToTake = damage;
    }

    @Override
    public Bounds getCollisionBounds() {
        return new BoundingBox(
                getX() - getHalfHeight(),
                getY() - getHalfHeight(),
                getHeight(),
                getHeight()
        );
    }

    public int getNumberOfKills() {
        return numberOfKills;
    }

    public void incrementKillCount() {
        numberOfKills++;
    }
}
