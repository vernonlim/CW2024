package dev.vernonlim.cw2024game.elements.actors;

import dev.vernonlim.cw2024game.elements.ImageElement;
import dev.vernonlim.cw2024game.configs.ImageElementConfig;

/**
 * An abstract class representing an ImageElement that can be updated.
 */
public abstract class ActiveActor extends ImageElement {
    /**
     * Constructs an ActiveActor from the given params.
     *
     * @param config the configuration object containing the necessary data to construct the ActiveActor
     */
    public ActiveActor(ImageElementConfig config) {
        super(config);
    }

    public abstract void updateActor(double deltaTime, double currentTime);
}
