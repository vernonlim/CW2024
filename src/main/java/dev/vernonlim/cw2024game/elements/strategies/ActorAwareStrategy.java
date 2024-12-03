package dev.vernonlim.cw2024game.elements.strategies;

import dev.vernonlim.cw2024game.elements.actors.ActiveActor;

public abstract class ActorAwareStrategy implements PlaneStrategy {
    protected ActiveActor actor;
    protected double deltaTime;
    protected double currentTime;

    public ActorAwareStrategy(ActiveActor actor) {
        this.actor = actor;
        this.deltaTime = 0;
        this.currentTime = 0;
    }

    @Override
    public void updateStrategyState(double deltaTime, double currentTime) {
        this.deltaTime = deltaTime;
        this.currentTime = currentTime;
    }
}
