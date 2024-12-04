package dev.vernonlim.cw2024game.elements.actors;

import dev.vernonlim.cw2024game.elements.ProjectileCode;
import dev.vernonlim.cw2024game.elements.ProjectileListener;
import dev.vernonlim.cw2024game.elements.strategies.PlaneStrategy;
import dev.vernonlim.cw2024game.factories.interfaces.ProjectileFactory;
import dev.vernonlim.cw2024game.managers.InputManager;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;

public class GreenPlane extends UserPlane {
    public GreenPlane(PlaneStrategy planeStrategy, ProjectileFactory projectileFactory, Pane root, ProjectileListener projectileListener, InputManager input, ImageView imageView, AudioClip fireSound, AudioClip damageSound, int initialHealth, double speed, double projectileYOffset) {
        super(planeStrategy, projectileFactory, root, projectileListener, input, imageView, fireSound, damageSound, initialHealth, speed, projectileYOffset);
    }

    @Override
    public void fireProjectile(ProjectileCode code) {
        switch (code) {
            case USER_ROUND_GREEN: {
                projectileListener.onFire(createProjectile(code));
                break;
            }
            case USER_ROUND: {
                projectileListener.onFire(createProjectile(code));
                projectileListener.onFire(createProjectile(ProjectileCode.USER_ROUND_UP));
                projectileListener.onFire(createProjectile(ProjectileCode.USER_ROUND_DOWN));
                break;
            }
        }

        fireSound.play();
    }
}
