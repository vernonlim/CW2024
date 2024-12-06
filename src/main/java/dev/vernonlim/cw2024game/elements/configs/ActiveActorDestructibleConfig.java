package dev.vernonlim.cw2024game.elements.configs;

import dev.vernonlim.cw2024game.elements.strategies.ActorStrategy;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class ActiveActorDestructibleConfig extends ImageElementConfig {
    private ActorStrategy actorStrategy;
    private double speed;
    private boolean alwaysInBounds;

    public ActiveActorDestructibleConfig(Pane root) {
        super(root);
        this.speed = 0;
        this.alwaysInBounds = true;
    }

    public ActorStrategy getActorStrategy() {
        return actorStrategy;
    }

    public void setActorStrategy(ActorStrategy actorStrategy) {
        this.actorStrategy = actorStrategy;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public boolean isAlwaysInBounds() {
        return alwaysInBounds;
    }

    public void setAlwaysInBounds(boolean alwaysInBounds) {
        this.alwaysInBounds = alwaysInBounds;
    }
}
