package dev.vernonlim.cw2024game.elements.actors;

import dev.vernonlim.cw2024game.elements.Element;
import dev.vernonlim.cw2024game.configs.BossConfig;
import dev.vernonlim.cw2024game.elements.strategies.Shielding;
import javafx.scene.media.AudioClip;

public class Boss extends FighterPlane {
    protected final Element shieldImage;
    protected Shielding shieldingStrategy;
    protected AudioClip shieldSound;
    protected AudioClip damageSound;
    protected boolean isShielded;

    public Boss(BossConfig config) {
        super(config);

        this.shieldSound = config.getShieldSound();
        this.damageSound = config.getDamageSound();

        this.shieldingStrategy = config.getShielding();

        this.isShielded = false;

        this.shieldImage = config.getShieldImage();
    }

    @Override
    public void updateActor(double deltaTime, double currentTime) {
        super.updateActor(deltaTime, currentTime);

        shieldingStrategy.updateStrategyState(deltaTime, currentTime);

        boolean original = isShielded;
        isShielded = shieldingStrategy.isShielded();

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
