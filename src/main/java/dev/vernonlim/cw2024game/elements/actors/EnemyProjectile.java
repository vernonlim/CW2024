package dev.vernonlim.cw2024game.elements.actors;

import dev.vernonlim.cw2024game.assets.AssetLoader;
import dev.vernonlim.cw2024game.elements.Vector;
import dev.vernonlim.cw2024game.elements.strategies.ActorStrategy;
import dev.vernonlim.cw2024game.elements.strategies.LinearProjectileStrategy;
import javafx.scene.layout.Pane;
import javafx.scene.image.ImageView;

public class EnemyProjectile extends Projectile {
    private static final int HORIZONTAL_VELOCITY = 10;

    public EnemyProjectile(ActorStrategy actorStrategy, Pane root, ImageView imageView) {
        super(actorStrategy, root, imageView, 1, HORIZONTAL_VELOCITY);

        // to be in front of the enemy plane
        moveHorizontally(-getHalfWidth());
    }
}
