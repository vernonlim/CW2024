package dev.vernonlim.cw2024game.elements.actors;

import dev.vernonlim.cw2024game.elements.ProjectileListener;
import dev.vernonlim.cw2024game.elements.strategies.UserPlaneStrategy;
import dev.vernonlim.cw2024game.factories.interfaces.ProjectileFactory;
import dev.vernonlim.cw2024game.managers.InputManager;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;

public class RegularPlane extends UserPlane {
    public RegularPlane(ProjectileFactory projectileFactory, Pane root, ProjectileListener projectileListener, InputManager input, ImageView imageView, AudioClip fireSound, int initialHealth, double speed, double projectileYOffset) {
        super(projectileFactory, root, projectileListener, input, imageView, fireSound, initialHealth, speed, projectileYOffset);
    }

    @Override
    public void initializeStrategy() {
        this.planeStrategy = new UserPlaneStrategy(this, input);
        this.actorStrategy = planeStrategy;
    }
}
