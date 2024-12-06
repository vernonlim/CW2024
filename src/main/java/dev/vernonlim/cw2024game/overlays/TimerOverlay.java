package dev.vernonlim.cw2024game.overlays;

import dev.vernonlim.cw2024game.elements.TimeDisplay;
import dev.vernonlim.cw2024game.elements.configs.OverlayConfig;
import dev.vernonlim.cw2024game.factories.interfaces.OverlayFactory;

public class TimerOverlay extends FloatingOverlay {
    private final TimeDisplay timeDisplay;
    private final int duration;

    public TimerOverlay(OverlayConfig config, int duration) {
        super(config);

        this.duration = duration;

        OverlayFactory overlayElementFactory = config.getOverlayFactory().withNewRoot(container);

        this.timeDisplay = overlayElementFactory.createTimeDisplay(duration);
        this.timeDisplay.show();

        show();
    }

    public void update(double currentTime) {
        int time = (int) (duration - currentTime / 1000.0f);

        timeDisplay.setTime(time);
    }
}
