package dev.vernonlim.cw2024game.elements.actors;

import dev.vernonlim.cw2024game.configs.ImageElementConfig;
import dev.vernonlim.cw2024game.elements.ImageElement;

/**
 * An abstract class representing an ImageElement that can be updated.
 * <p>
 * This class is modified.
 * Original Code: <a href="https://github.com/kooitt/CW2024/blob/master/src/main/java/com/example/demo/ActiveActor.java">GitHub</a>
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

    /**
     * Updates the Actor.
     *
     * @param deltaTime   the difference in virtual time between the previous and current update
     * @param currentTime the current virtual time
     */
    public abstract void updateActor(double deltaTime, double currentTime);
}
