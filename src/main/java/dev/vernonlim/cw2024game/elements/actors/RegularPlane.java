package dev.vernonlim.cw2024game.elements.actors;

import dev.vernonlim.cw2024game.elements.ProjectileListener;
import dev.vernonlim.cw2024game.elements.strategies.PlaneStrategy;
import dev.vernonlim.cw2024game.factories.interfaces.ProjectileFactory;
import dev.vernonlim.cw2024game.managers.InputManager;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;

public class RegularPlane extends UserPlane {
    public RegularPlane(PlaneStrategy planeStrategy, ProjectileFactory projectileFactory, Pane root, ProjectileListener projectileListener, InputManager input, ImageView imageView, AudioClip fireSound, AudioClip damageSound, int initialHealth, double speed, double projectileYOffset) {
        super(planeStrategy, projectileFactory, root, projectileListener, input, imageView, fireSound, damageSound, initialHealth, speed, projectileYOffset);
    }
}
