package dev.vernonlim.cw2024game.elements.strategies;

/**
 * An abstract class implementing shared behaviour among all Updatable strategies.
 */
public abstract class UpdatableStrategy implements Updatable {
    /**
     * The difference in virtual time between the previous and current updates.
     */
    protected double deltaTime;

    /**
     * The current virtual time.
     */
    protected double currentTime;

    /**
     * Constructs an Updatable strategy.
     */
    public UpdatableStrategy() {
        this.deltaTime = 0;
        this.currentTime = 0;
    }

    @Override
    public void updateStrategyState(double deltaTime, double currentTime) {
        this.deltaTime = deltaTime;
        this.currentTime = currentTime;
    }
}
