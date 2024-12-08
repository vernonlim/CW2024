package dev.vernonlim.cw2024game.screens;

import dev.vernonlim.cw2024game.configs.ScreenConfig;
import dev.vernonlim.cw2024game.overlays.TimerOverlay;

public abstract class CountdownLevel extends Level {
    private final int countdownTime;
    private final TimerOverlay timerOverlay;

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

    public int getCountdownTime() {
        return countdownTime;
    }
}
