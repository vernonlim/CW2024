package dev.vernonlim.cw2024game.elements.actors;

import dev.vernonlim.cw2024game.elements.ProjectileCode;
import dev.vernonlim.cw2024game.elements.ProjectileListener;
import dev.vernonlim.cw2024game.factories.interfaces.ProjectileFactory;
import javafx.scene.layout.Pane;
import javafx.scene.image.ImageView;

public class EnemyPlane extends FighterPlane {
    private static final int HORIZONTAL_VELOCITY = -6;
    private static final int INITIAL_HEALTH = 1;
    private static final double FIRE_RATE = 0.01;
    private static final double PROJECTILE_Y_OFFSET = 7.0f;

    public EnemyPlane(ProjectileFactory projectileFactory, Pane root, ProjectileListener projectileListener, ImageView imageView) {
        super(projectileFactory, root, projectileListener, imageView, INITIAL_HEALTH);
    }

    @Override
    public void updatePosition(double deltaTime) {
        moveHorizontally(HORIZONTAL_VELOCITY * (deltaTime / 50.0f));
    }

    @Override
    public void updateActor(double deltaTime, double currentTime) {
        super.updateActor(deltaTime, currentTime);

        updatePosition(deltaTime);

        if ((currentTime - lastFireTime) > 50.0f) {
            lastFireTime = currentTime;

            boolean shouldFire = Math.random() < FIRE_RATE;

            if (shouldFire) {
                fireProjectile();
            }
        }
    }

    @Override
    public Projectile createProjectile() {
        return projectileFactory.createProjectile(ProjectileCode.ENEMY, getX() - getHalfWidth(), getY() + PROJECTILE_Y_OFFSET);
    }
}
