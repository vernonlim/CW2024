package dev.vernonlim.cw2024game.elements.actors;

import dev.vernonlim.cw2024game.assets.AssetLoader;
import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;


public abstract class ActiveActorDestructible extends ActiveActor implements Destructible {
    private boolean isDestroyed;

    public ActiveActorDestructible(Pane root, ImageView imageView) {
        super(root, imageView);
        isDestroyed = false;
    }

    @Override
    public abstract void takeDamage(int damage);

    @Override
    public void destroy() {
        setDestroyed(true);
    }

    public boolean isDestroyed() {
        return isDestroyed;
    }

    protected void setDestroyed(boolean isDestroyed) {
        this.isDestroyed = isDestroyed;
    }

    public Bounds getCollisionBounds() {
        return getBoundsInParent();
    }
}
