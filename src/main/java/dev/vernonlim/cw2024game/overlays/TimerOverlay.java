package dev.vernonlim.cw2024game.overlays;

import dev.vernonlim.cw2024game.configs.OverlayConfig;
import dev.vernonlim.cw2024game.elements.TimeDisplay;
import dev.vernonlim.cw2024game.factories.interfaces.OverlayFactory;

/**
 * An overlay for the Countdown Levels displaying a Countdown Timer.
 */
public class TimerOverlay extends FloatingOverlay {
    /**
     * The TimeDisplay for this Overlay to be displayed at the top right.
     */
    private final TimeDisplay timeDisplay;

    /**
     * The duration of the TimeDisplay.
     */
    private final int duration;

    /**
     * Constructs a Timer Overlay.
     *
     * @param config   the configuration object containing the necessary data to construct the Overlay
     * @param duration the duration to count down from
     */
    public TimerOverlay(OverlayConfig config, int duration) {
        super(config);

        this.duration = duration;

        OverlayFactory overlayElementFactory = config.getOverlayFactory().withNewRoot(container);

        this.timeDisplay = overlayElementFactory.createTimeDisplay(duration);
        this.timeDisplay.show();

        show();
    }

    /**
     * Updates this Overlay.
     *
     * @param currentTime the current virtual time
     */
    public void update(double currentTime) {
        int time = (int) (duration - currentTime / 1000.0f);

        timeDisplay.setTime(time);
    }
}
