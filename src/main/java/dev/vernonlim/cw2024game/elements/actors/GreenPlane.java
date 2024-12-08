package dev.vernonlim.cw2024game.elements.actors;

import dev.vernonlim.cw2024game.configs.UserPlaneConfig;

public class GreenPlane extends UserPlane {
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
