package dev.vernonlim.cw2024game.elements.actors;

import dev.vernonlim.cw2024game.elements.Element;
import dev.vernonlim.cw2024game.elements.configs.BossConfig;
import dev.vernonlim.cw2024game.elements.strategies.BossStrategy;
import javafx.scene.media.AudioClip;

public class Boss extends FighterPlane {
    private final Element shieldImage;
    protected BossStrategy bossStrategy;
    protected AudioClip shieldSound;
    protected AudioClip damageSound;
    private boolean isShielded;

    public Boss(BossConfig config) {
        super(config);

        this.shieldSound = config.getShieldSound();
        this.damageSound = config.getDamageSound();

        this.bossStrategy = config.getBossStrategy();

        this.isShielded = false;

        this.shieldImage = config.getShieldImage();
    }

    @Override
    public void updateActor(double deltaTime, double currentTime) {
        super.updateActor(deltaTime, currentTime);

        boolean original = isShielded;
        isShielded = bossStrategy.isShielded();

        if (original != isShielded) {
            if (isShielded) {
                shieldImage.show();
            } else {
                shieldImage.hide();
            }
        }

        shieldImage.setPosition(getX() - getHalfWidth(), getY() - getHalfHeight());
    }

    @Override
    public void takeDamage(int damage) {
        if (!isShielded) {
            super.takeDamage(damage);

            damageSound.play();
        } else {
            shieldSound.play();
        }
    }
}
