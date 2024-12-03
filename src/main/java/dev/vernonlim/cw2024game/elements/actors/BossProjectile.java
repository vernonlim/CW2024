package dev.vernonlim.cw2024game.elements.actors;

import dev.vernonlim.cw2024game.assets.AssetLoader;
import dev.vernonlim.cw2024game.elements.Vector;
import dev.vernonlim.cw2024game.elements.strategies.LinearProjectileStrategy;
import javafx.scene.layout.Pane;
import javafx.scene.image.ImageView;

public class BossProjectile extends Projectile {
    private static final int HORIZONTAL_VELOCITY = 15;

    public BossProjectile(Pane root, ImageView imageView) {
        super(root, imageView, 1, HORIZONTAL_VELOCITY);

        this.actorStrategy = new LinearProjectileStrategy(new Vector(-1, 0));
    }
}
