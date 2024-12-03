package dev.vernonlim.cw2024game.elements.actors;

import dev.vernonlim.cw2024game.elements.Vector;
import dev.vernonlim.cw2024game.elements.strategies.ActorStrategy;
import javafx.geometry.Bounds;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;


public abstract class ActiveActorDestructible extends ActiveActor implements Destructible {
    protected final double speed;
    protected final boolean alwaysInBounds;

    private boolean isDestroyed;

    protected ActorStrategy actorStrategy;

    public ActiveActorDestructible(ActorStrategy actorStrategy, Pane root, ImageView imageView, double speed, boolean alwaysInBounds) {
        super(root, imageView);
        isDestroyed = false;

        this.speed = speed;
        this.alwaysInBounds = alwaysInBounds;

        this.actorStrategy = actorStrategy;
    }

    @Override
    public void updateActor(double deltaTime, double currentTime) {
        actorStrategy.updateStrategyState(deltaTime, currentTime);

        Vector movement = actorStrategy.getNextMovement();
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
