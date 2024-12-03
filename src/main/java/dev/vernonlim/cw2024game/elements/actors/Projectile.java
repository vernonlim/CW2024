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
        double x = getX();
        double y = getY();

        if (x < 0 || x > Main.SCREEN_WIDTH || y < 0 || y > Main.SCREEN_HEIGHT) {
            destroy();
        }
    }
}
