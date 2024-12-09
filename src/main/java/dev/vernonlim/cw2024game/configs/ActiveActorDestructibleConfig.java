package dev.vernonlim.cw2024game.configs;

import dev.vernonlim.cw2024game.elements.strategies.Movement;
import javafx.scene.layout.Pane;

/**
 * A data class used for streamlining ActiveActorDestructible creation
 */
public class ActiveActorDestructibleConfig extends ImageElementConfig {
    /**
     * The Movement strategy of the Actor.
     */
    private Movement movement;

    /**
     * The speed of the Actor.
     */
    private double speed;

    /**
     * Indicates if the Actor should always be in bounds.
     */
    private boolean alwaysInBounds;

    /**
     * Constructs an ActiveActorDestructibleConfig with the given params.
     *
     * @param root the root Pane on which the ActiveActorDestructible is based
     */
    public ActiveActorDestructibleConfig(Pane root) {
        super(root);
        this.speed = 0;
        this.alwaysInBounds = true;
    }

    /**
     * Gets the Movement strategy.
     * 
     * @return the Movement strategy
     */
    public Movement getMovement() {
        return movement;
    }

    /**
     * Sets the Movement strategy.
     * 
     * @param movement the Movement strategy to set
     */
    public void setMovement(Movement movement) {
        this.movement = movement;
    }

    /**
     * Gets the speed.
     * 
     * @return the speed
     */
    public double getSpeed() {
        return speed;
    }

    /**
     * Sets the speed.
     * 
     * @param speed the speed to set
     */
    public void setSpeed(double speed) {
        this.speed = speed;
    }

    /**
     * Indicates if the Actor should always be in bounds.
     * 
     * @return true if always in bounds, false otherwise
     */
    public boolean isAlwaysInBounds() {
        return alwaysInBounds;
    }

    /**
     * Sets wheter the Actor should always be in bounds.
     *
     * @param alwaysInBounds true if always in bounds, false otherwise
     */
    public void setAlwaysInBounds(boolean alwaysInBounds) {
        this.alwaysInBounds = alwaysInBounds;
    }
}
