package dev.vernonlim.cw2024game.elements.actors;

import dev.vernonlim.cw2024game.assets.AssetLoader;
import dev.vernonlim.cw2024game.elements.Vector;
import dev.vernonlim.cw2024game.elements.strategies.LinearProjectileStrategy;
import javafx.scene.layout.Pane;
import javafx.scene.image.ImageView;

public class UserProjectile extends Projectile {
    private static final int HORIZONTAL_VELOCITY = 100;

    public UserProjectile(Pane root, ImageView imageView, Vector velocity) {
        super(root, imageView, 1, HORIZONTAL_VELOCITY);

        // the projectile has to be in front of the firer
        moveHorizontally(getHalfWidth());

        this.actorStrategy = new LinearProjectileStrategy(velocity);
    }
}
