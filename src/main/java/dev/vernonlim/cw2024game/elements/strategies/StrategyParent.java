package dev.vernonlim.cw2024game.elements.strategies;

public abstract class StrategyParent implements PlaneStrategy {
    protected double deltaTime;
    protected double currentTime;

    public StrategyParent() {
        this.deltaTime = 0;
        this.currentTime = 0;
    }

    @Override
    public void updateStrategyState(double deltaTime, double currentTime) {
        this.deltaTime = deltaTime;
        this.currentTime = currentTime;
    }
}
