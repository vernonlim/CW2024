package dev.vernonlim.cw2024game.configs;

import dev.vernonlim.cw2024game.elements.Element;
import dev.vernonlim.cw2024game.elements.ProjectileListener;
import dev.vernonlim.cw2024game.elements.strategies.Shielding;
import dev.vernonlim.cw2024game.factories.interfaces.ProjectileFactory;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;

/**
 * A data class used for streamlining Boss creation
 */
public class BossConfig extends FighterPlaneConfig {
    /**
     * The Shielding strategy used by the Boss.
     */
    private Shielding shielding;

    /**
     * The image representing the Boss's shield.
     */
    private Element shieldImage;

    /**
     * The sound played upon collision with the shield.
     */
    private AudioClip shieldSound;

    /**
     * The sound played when the boss takes damage.
     */
    private AudioClip damageSound;

    /**
     * Constructs a BossConfig with the given params.
     *
     * @param root the root Pane on which the Boss is based
     * @param projectileFactory the ProjectileFactory for the Boss
     * @param projectileListener the ProjectileListener for the Boss
     */
    public BossConfig(Pane root, ProjectileFactory projectileFactory, ProjectileListener projectileListener) {
        super(root, projectileFactory, projectileListener);
    }

    /**
     * Gets the Shielding strategy.
     * 
     * @return the Shielding strategy
     */
    public Shielding getShielding() {
        return shielding;
    }

    /**
     * Sets the Shielding strategy.
     * 
     * @param shielding the Shielding strategy to set
     */
    public void setShielding(Shielding shielding) {
        this.shielding = shielding;
    }

    /**
     * Gets the shield image.
     * 
     * @return the shield image
     */
    public Element getShieldImage() {
        return shieldImage;
    }

    /**
     * Sets the shield image.
     * 
     * @param shieldImage the shield image to set
     */
    public void setShieldImage(Element shieldImage) {
        this.shieldImage = shieldImage;
    }

    /**
     * Gets the shield sound.
     * 
     * @return the shield sound
     */
    public AudioClip getShieldSound() {
        return shieldSound;
    }

    /**
     * Sets the shield sound.
     * 
     * @param shieldSound the shield sound to set
     */
    public void setShieldSound(AudioClip shieldSound) {
        this.shieldSound = shieldSound;
    }

    /**
     * Gets the damage sound.
     * 
     * @return the damage sound
     */
    public AudioClip getDamageSound() {
        return damageSound;
    }

    /**
     * Sets the damage sound.
     * 
     * @param damageSound the damage sound to set
     */
    public void setDamageSound(AudioClip damageSound) {
        this.damageSound = damageSound;
    }
}
