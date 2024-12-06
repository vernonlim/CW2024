package dev.vernonlim.cw2024game.elements.configs;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class ProjectileConfig extends ActiveActorDestructibleConfig {
    private int damage;
    private boolean isUserProjectile;

    public ProjectileConfig(Pane root) {
        super(root);

        this.damage = 1;
        this.isUserProjectile = false;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public boolean isUserProjectile() {
        return isUserProjectile;
    }

    public void setUserProjectile(boolean userProjectile) {
        this.isUserProjectile = userProjectile;
    }
}
