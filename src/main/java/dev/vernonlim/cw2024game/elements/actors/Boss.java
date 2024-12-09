package dev.vernonlim.cw2024game.elements.actors;

import dev.vernonlim.cw2024game.configs.BossConfig;
import dev.vernonlim.cw2024game.elements.Element;
import dev.vernonlim.cw2024game.elements.strategies.Shielding;
import javafx.scene.media.AudioClip;

/**
 * An Enemy Boss Plane.
 */
public class Boss extends FighterPlane {
    /**
     * The image representing the Boss's shield.
     */
    protected final Element shieldImage;

    /**
     * The Shielding strategy used by the Boss.
     */
    protected Shielding shieldingStrategy;

    /**
     * The sound played upon collision with the shield.
     */
    protected AudioClip shieldSound;

    /**
     * The sound played when the boss takes damage.
     */
    protected AudioClip damageSound;

    /**
     * Indicates whether the boss is currently shielded.
     */
    protected boolean isShielded;

    /**
     * Constructs an Enemy Boss Plane with the given params.
     *
     * @param config the configuration object containing the necessary data to construct the Boss
     */
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
