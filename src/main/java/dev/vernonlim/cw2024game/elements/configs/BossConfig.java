package dev.vernonlim.cw2024game.elements.configs;

import dev.vernonlim.cw2024game.elements.Element;
import dev.vernonlim.cw2024game.elements.ProjectileListener;
import dev.vernonlim.cw2024game.elements.strategies.BossStrategy;
import dev.vernonlim.cw2024game.factories.interfaces.ProjectileFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;

public class BossConfig extends FighterPlaneConfig {
    private BossStrategy bossStrategy;
    private Element shieldImage;
    private AudioClip shieldSound;
    private AudioClip damageSound;

    public BossConfig(Pane root, ProjectileFactory projectileFactory, ProjectileListener projectileListener) {
        super(root, projectileFactory, projectileListener);
    }

    public BossStrategy getBossStrategy() {
        return bossStrategy;
    }

    public void setBossStrategy(BossStrategy bossStrategy) {
        this.bossStrategy = bossStrategy;
        this.setPlaneStrategy(this.bossStrategy);
        this.setActorStrategy(this.bossStrategy);
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
