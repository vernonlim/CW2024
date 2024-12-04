package dev.vernonlim.cw2024game.elements.actors;

import dev.vernonlim.cw2024game.Main;
import dev.vernonlim.cw2024game.assets.AssetLoader;
import dev.vernonlim.cw2024game.elements.Vector;
import dev.vernonlim.cw2024game.elements.strategies.ActorStrategy;
import javafx.scene.layout.Pane;
import javafx.scene.image.ImageView;

public class Projectile extends ActiveActorDestructible {
    public final int damage;
    public final boolean userProjectile;

    public Projectile(ActorStrategy actorStrategy, Pane root, ImageView imageView, int damage, double speed, boolean userProjectile) {
        super(actorStrategy, root, imageView, speed, false);

        this.damage = damage;
        this.userProjectile = userProjectile;

        if (userProjectile) {
            moveHorizontally(getHalfWidth());
        } else {
            moveHorizontally(-getHalfWidth());
        }
    }

    @Override
    public void takeDamage(int damage) {
        this.destroy();
    }

    @Override
    public void updateActor(double deltaTime, double currentTime) {
        super.updateActor(deltaTime, currentTime);

        Vector position = getPosition();

        if (position.isOutside(0, Main.SCREEN_WIDTH, 0, Main.SCREEN_HEIGHT)) {
            destroy();
        }
    }
}
