package dev.vernonlim.cw2024game.elements.strategies;

import dev.vernonlim.cw2024game.Vector;

public interface Movement extends Updatable {
    Vector getNextMovement();
}
