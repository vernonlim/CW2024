package dev.vernonlim.cw2024game.elements.actors;

import dev.vernonlim.cw2024game.configs.UserPlaneConfig;
import dev.vernonlim.cw2024game.managers.InputManager;
import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;

/**
 * An abstract class representing a FighterPlane controlled by the User.
 * <p>
 * This class is modified.
 * Original Code: <a href="https://github.com/kooitt/CW2024/blob/master/src/main/java/com/example/demo/UserPlane.java">GitHub</a>
 */
public abstract class UserPlane extends FighterPlane {
    /**
     * The InputManager handling user input.
     */
    protected final InputManager input;

    /**
     * The number of kills the user has achieved.
     */
    protected int numberOfKills;

    /**
     * The amount of damage to take next cycle (after a collision or enemy penetration).
     */
    protected int damageToTake;

    /**
     * The last time the UserPlane took damage.
     */
    protected double lastDamage;

    /**
     * Indicates if the UserPlane should take damage.
     */
    protected boolean takeDamage;

    /**
     * Constructs a UserPlane with the given params.
     *
     * @param config the configuration object containing the necessary data to construct the UserPlane
     */
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

    /**
     * Gets the number of kills the user has achieved.
     *
     * @return the number of kills the user has achieved
     */
    public int getNumberOfKills() {
        return numberOfKills;
    }

    /**
     * Increments the user's kill count by 1.
     */
    public void incrementKillCount() {
        numberOfKills++;
    }
}
