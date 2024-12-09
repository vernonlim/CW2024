package dev.vernonlim.cw2024game.elements.strategies;

/**
 * An interface for objects (only "Strategies" in this project) that can be updated with the delta and current time.>
 */
public interface Updatable {
    /**
     * Updates the current state of the strategy.
     *
     * @param deltaTime the difference in virtual time between the previous and current update
     * @param currentTime the current virtual time
     */
    void updateStrategyState(double deltaTime, double currentTime);
}
