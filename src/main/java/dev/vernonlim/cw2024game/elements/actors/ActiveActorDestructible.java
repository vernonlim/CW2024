package dev.vernonlim.cw2024game.elements.actors;

import dev.vernonlim.cw2024game.assets.AssetLoader;
import javafx.geometry.Bounds;
import javafx.scene.layout.Pane;

public abstract class ActiveActorDestructible extends ActiveActor implements Destructible {
    private boolean isDestroyed;

    public ActiveActorDestructible(Pane root, AssetLoader loader, String imageName, int imageHeight) {
        super(root, loader, imageName, imageHeight);
        isDestroyed = false;
    }

    @Override
    public abstract void takeDamage();

    @Override
    public void destroy() {
        setDestroyed(true);
    }

    protected void setDestroyed(boolean isDestroyed) {
        this.isDestroyed = isDestroyed;
    }

    public boolean isDestroyed() {
        return isDestroyed;
    }

    public void collideWith(ActiveActorDestructible other) {
        takeDamage();
        other.takeDamage();
    }

    public Bounds getCollisionBounds() {
        return getBoundsInParent();
    }
}
