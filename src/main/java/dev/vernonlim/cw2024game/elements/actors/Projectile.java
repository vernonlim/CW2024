package dev.vernonlim.cw2024game.elements.actors;

import dev.vernonlim.cw2024game.Main;
import dev.vernonlim.cw2024game.Vector;
import dev.vernonlim.cw2024game.elements.configs.ProjectileConfig;

public class Projectile extends ActiveActorDestructible {
    protected final int damage;
    protected final boolean isUserProjectile;

    public Projectile(ProjectileConfig config) {
        super(config);

        this.damage = config.getDamage();
        this.isUserProjectile = config.isUserProjectile();

        if (isUserProjectile) {
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

    public int getDamage() {
        return damage;
    }

    public boolean isUserProjectile() {
        return isUserProjectile;
    }
}
