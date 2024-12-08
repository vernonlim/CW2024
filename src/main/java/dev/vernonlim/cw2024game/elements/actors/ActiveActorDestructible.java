package dev.vernonlim.cw2024game.elements.actors;

import dev.vernonlim.cw2024game.Vector;
import dev.vernonlim.cw2024game.configs.ActiveActorDestructibleConfig;
import dev.vernonlim.cw2024game.elements.strategies.Movement;
import javafx.geometry.Bounds;


public abstract class ActiveActorDestructible extends ActiveActor implements Destructible {
    protected final double speed;
    protected final boolean alwaysInBounds;
    protected Movement movementStrategy;
    private boolean isDestroyed;

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
        setDestroyed(true);
    }

    public boolean isDestroyed() {
        return isDestroyed;
    }

    protected void setDestroyed(boolean isDestroyed) {
        this.isDestroyed = isDestroyed;
    }

    public Bounds getCollisionBounds() {
        return getBoundsInParent();
    }
}
