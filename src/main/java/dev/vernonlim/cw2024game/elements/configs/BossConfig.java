package dev.vernonlim.cw2024game.elements.configs;

import dev.vernonlim.cw2024game.elements.Element;
import dev.vernonlim.cw2024game.elements.ProjectileListener;
import dev.vernonlim.cw2024game.elements.strategies.Shielding;
import dev.vernonlim.cw2024game.factories.interfaces.ProjectileFactory;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;

public class BossConfig extends FighterPlaneConfig {
    private Shielding shielding;
    private Element shieldImage;
    private AudioClip shieldSound;
    private AudioClip damageSound;

    public BossConfig(Pane root, ProjectileFactory projectileFactory, ProjectileListener projectileListener) {
        super(root, projectileFactory, projectileListener);
    }

    public Shielding getShielding() {
        return shielding;
    }

    public void setShielding(Shielding shielding) {
        this.shielding = shielding;
    }

    public AudioClip getDamageSound() {
        return damageSound;
    }

    public void setDamageSound(AudioClip damageSound) {
        this.damageSound = damageSound;
    }

    public Element getShieldImage() {
        return shieldImage;
    }

    public void setShieldImage(Element shieldImage) {
        this.shieldImage = shieldImage;
    }

    public AudioClip getShieldSound() {
        return shieldSound;
    }

    public void setShieldSound(AudioClip shieldSound) {
        this.shieldSound = shieldSound;
    }
}
