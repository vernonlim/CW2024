package dev.vernonlim.cw2024game.elements.actors;

import javafx.scene.layout.Pane;

public abstract class Projectile extends ActiveActorDestructible {
    public Projectile(Pane root, String imageName, int imageHeight, double initialXPos, double initialYPos) {
        super(root, imageName, imageHeight, initialXPos, initialYPos);
    }

    @Override
    public void takeDamage() {
        this.destroy();
    }

    @Override
    public void updateActor(double deltaTime, double currentTime) {
        double xPosition = view.getLayoutX() + view.getTranslateX();

        // The inaccuracy with the right side doesn't matter
        if (xPosition < 0 || xPosition > 1280) {
            destroy();
        }
    }
}
