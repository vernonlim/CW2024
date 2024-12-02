package dev.vernonlim.cw2024game.elements.actors;

import dev.vernonlim.cw2024game.Main;
import dev.vernonlim.cw2024game.assets.AssetLoader;
import javafx.scene.layout.Pane;
import javafx.scene.image.ImageView;

public abstract class Projectile extends ActiveActorDestructible {
    public final int damage;

    public Projectile(Pane root, ImageView imageView, int damage) {
        super(root, imageView);

        this.damage = damage;
    }

    @Override
    public void takeDamage(int damage) {
        this.destroy();
    }

    @Override
    public void updateActor(double deltaTime, double currentTime) {
        double xPosition = view.getBoundsInParent().getCenterX();

        // The inaccuracy with the right side doesn't matter
        if (xPosition < 0 || xPosition > Main.SCREEN_WIDTH) {
            destroy();
        }
    }
}
