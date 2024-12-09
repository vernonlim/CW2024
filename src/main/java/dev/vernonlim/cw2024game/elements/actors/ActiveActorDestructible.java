package dev.vernonlim.cw2024game.elements.actors;

import dev.vernonlim.cw2024game.Vector;
import dev.vernonlim.cw2024game.configs.ActiveActorDestructibleConfig;
import dev.vernonlim.cw2024game.elements.strategies.Movement;
import javafx.geometry.Bounds;

/**
 * An abstract class representing an ActiveActor that can move and be destroyed.
 */
public abstract class ActiveActorDestructible extends ActiveActor implements Destructible {
    /**
     * The speed at which the Actor moves.
     */
    protected final double speed;

    /**
     * Indicates whether the Actor should be forced in-bounds.
     */
    protected final boolean alwaysInBounds;

    /**
     * The Movement strategy for the Actor determining how it moves.
     */
    protected final Movement movementStrategy;

    /**
     * Indicates if the Actor has been destroyed.
     */
    private boolean isDestroyed;

    /**
     * Constructs an ActiveActorDestructible from the given params.
     *
     * @param config the configuration object containing the necessary data to construct the ActiveActorDestructible
     */
    public ActiveActorDestructible(ActiveActorDestructibleConfig config) {
        super(config);

        this.isDestroyed = false;

        this.speed = config.getSpeed();
        this.alwaysInBounds = config.isAlwaysInBounds();
        this.movementStrategy = config.getMovement();
    }

    @Override
    public void updateActor(double deltaTime, double currentTime) {
        movementStrategy.updateStrategyState(deltaTime, currentTime);

        Vector movement = this.movementStrategy.getNextMovement();
        movement.scaleBy(speed);
        movement.scaleBy(deltaTime / 50.0f);

        move(movement);

        if (alwaysInBounds) {
            ensureInBounds();
        }
    }

    @Override
    public abstract void takeDamage(int damage);

    @Override
    public void destroy() {
        isDestroyed = true;
    }

    /**
     * Indicates if the Actor has been destroyed.
     *
     * @return true if the Actor has been destroyed, false otherwise
     */
    public boolean isDestroyed() {
        return isDestroyed;
    }

    /**
     * Gets the collision bounds for the Actor.
     *
     * @return the collision bounds for the Actor
     */
    public Bounds getCollisionBounds() {
        return getBoundsInParent();
    }
}
