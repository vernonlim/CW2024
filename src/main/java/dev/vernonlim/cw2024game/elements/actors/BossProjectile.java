package dev.vernonlim.cw2024game.elements.actors;

import dev.vernonlim.cw2024game.assets.AssetLoader;
import dev.vernonlim.cw2024game.elements.Vector;
import dev.vernonlim.cw2024game.elements.strategies.ActorStrategy;
import dev.vernonlim.cw2024game.elements.strategies.LinearProjectileStrategy;
import javafx.scene.layout.Pane;
import javafx.scene.image.ImageView;

public class BossProjectile extends Projectile {
    private static final int HORIZONTAL_VELOCITY = 15;

    public BossProjectile(ActorStrategy actorStrategy, Pane root, ImageView imageView, int damage) {
        super(actorStrategy, root, imageView, damage, HORIZONTAL_VELOCITY);
    }
}
