package dev.vernonlim.cw2024game.elements.actors;

import dev.vernonlim.cw2024game.configs.UserPlaneConfig;

/**
 * A Green User Plane.
 */
public class GreenPlane extends UserPlane {
    /**
     * Constructs a Green (User) Plane with the given params.
     *
     * @param config the configuration object containing the necessary data to construct the GreenPlane
     */
    public GreenPlane(UserPlaneConfig config) {
        super(config);
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
