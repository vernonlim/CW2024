package dev.vernonlim.cw2024game.screens;

import dev.vernonlim.cw2024game.configs.ScreenConfig;
import dev.vernonlim.cw2024game.overlays.TimerOverlay;

/**
 * An abstract class representing a Level with a Countdown timer present,
 * with the Level being won upon it running out.
 */
public abstract class CountdownLevel extends Level {
    /**
     * The amount of time to count down from.
     */
    private final int countdownTime;

    /**
     * The TimerOverlay associated with this Level.
     */
    private final TimerOverlay timerOverlay;

    /**
     * Constructs a Level with a Countdown Timer at the top right.
     *
     * @param config the configuration object containing the necessary data to construct the Level
     */
    public CountdownLevel(ScreenConfig config) {
        super(config);

        countdownTime = config.getCountdownTime();

        this.timerOverlay = overlayFactory.createTimerOverlay(countdownTime);
    }

    @Override
    protected void updateOverlays(double currentTime) {
        super.updateOverlays(currentTime);

        timerOverlay.update(currentTime);
    }

    /**
     * Get the amount of time to count down from.
     *
     * @return the amount of time to count down from
     */
    public int getCountdownTime() {
        return countdownTime;
    }
}
