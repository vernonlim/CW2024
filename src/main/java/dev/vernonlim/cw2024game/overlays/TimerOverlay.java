package dev.vernonlim.cw2024game.overlays;

import dev.vernonlim.cw2024game.Main;
import dev.vernonlim.cw2024game.elements.Element;
import dev.vernonlim.cw2024game.elements.TimeDisplay;
import dev.vernonlim.cw2024game.factories.interfaces.OverlayFactory;
import javafx.scene.layout.Pane;

public class TimerOverlay extends FloatingOverlay {
    private final OverlayFactory overlayElementFactory;
    protected final TimeDisplay timeDisplay;
    protected int secondsRemaining;

    public TimerOverlay(OverlayFactory overlayFactory, Pane root, int secondsRemaining) {
        super(root);

        this.secondsRemaining = secondsRemaining;

        this.overlayElementFactory = overlayFactory.withNewRoot(pane);

        this.timeDisplay = overlayElementFactory.createTimeDisplay(secondsRemaining);
        timeDisplay.show();

        show();
    }

    public void update(double currentTime) {
        int time = (int)(secondsRemaining - currentTime / 1000.0f);

        timeDisplay.setTime(time);
    }
}
