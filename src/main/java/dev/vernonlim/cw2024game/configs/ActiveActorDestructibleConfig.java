package dev.vernonlim.cw2024game.configs;

import dev.vernonlim.cw2024game.elements.strategies.Movement;
import javafx.scene.layout.Pane;

public class ActiveActorDestructibleConfig extends ImageElementConfig {
    private Movement movement;
    private double speed;
    private boolean alwaysInBounds;

    public ActiveActorDestructibleConfig(Pane root) {
        super(root);
        this.speed = 0;
        this.alwaysInBounds = true;
    }

    public Movement getMovement() {
        return movement;
    }

    public void setMovement(Movement movement) {
        this.movement = movement;
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
