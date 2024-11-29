package dev.vernonlim.cw2024game.elements.actors;

import javafx.scene.layout.Pane;

public abstract class ActiveActorDestructible extends ActiveActor implements Destructible {
    private boolean isDestroyed;

    public ActiveActorDestructible(Pane root, String imageName, int imageHeight) {
        super(root, imageName, imageHeight);
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
}
