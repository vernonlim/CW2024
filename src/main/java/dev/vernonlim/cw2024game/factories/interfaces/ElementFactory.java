package dev.vernonlim.cw2024game.factories.interfaces;

import dev.vernonlim.cw2024game.elements.Element;

/**
 * An interface defining a Factory for creating Elements.
 */
public interface ElementFactory {
    /**
     * Creates a shield image for use by the Level Two Boss.
     *
     * @return the shield image
     */
    Element createShieldImage();

    /**
     * Creates a background meant for Screens from a given image name defined in the AssetLoader.
     *
     * @param imageName the image name
     * @return the background
     */
    Element createBackground(String imageName);
}
