package dev.vernonlim.cw2024game.elements.strategies;

/**
 * An interface for determining when an Actor should have a shield.
 */
public interface Shielding extends Updatable {
    /**
     * Indicates whether the Actor should be shielded.
     *
     * @return true if the Actor should be shielded, false otherwise
     */
    boolean isShielded();
}
