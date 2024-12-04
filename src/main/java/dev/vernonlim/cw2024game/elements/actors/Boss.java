package dev.vernonlim.cw2024game.elements.actors;

import dev.vernonlim.cw2024game.Main;
import dev.vernonlim.cw2024game.elements.Element;
import dev.vernonlim.cw2024game.elements.ProjectileCode;
import dev.vernonlim.cw2024game.elements.ProjectileListener;
import dev.vernonlim.cw2024game.elements.Vector;
import dev.vernonlim.cw2024game.elements.strategies.BossStrategy;
import dev.vernonlim.cw2024game.elements.strategies.BossStrategyImpl;
import dev.vernonlim.cw2024game.factories.interfaces.ElementFactory;
import dev.vernonlim.cw2024game.factories.interfaces.ProjectileFactory;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

import javafx.scene.image.ImageView;
import javafx.scene.media.AudioClip;

public class Boss extends FighterPlane {
    private static final int HEALTH = 1000;
    private static final double SPEED = 8.0f;
    private static final double PROJECTILE_Y_OFFSET = 0.0f;
    private static final boolean FACING_RIGHT = false;
    private static final boolean ALWAYS_IN_BOUNDS = true;
    private boolean isShielded;

    private final Element shieldImage;

    protected BossStrategy bossStrategy;

    protected AudioClip shieldSound;
    protected AudioClip damageSound;

    public Boss(BossStrategy bossStrategy, ElementFactory elementFactory, ProjectileFactory projectileFactory, Pane root, ProjectileListener projectileListener, ImageView imageView, AudioClip fireSound, AudioClip deathSound, AudioClip shieldSound, AudioClip damageSound) {
        super(bossStrategy, projectileFactory, root, projectileListener, imageView, fireSound, deathSound, HEALTH, SPEED, PROJECTILE_Y_OFFSET, FACING_RIGHT, ALWAYS_IN_BOUNDS);

        this.shieldSound = shieldSound;
        this.damageSound = damageSound;

        this.bossStrategy = bossStrategy;

        setXFromRight(5.0f);
        setY(Main.SCREEN_HEIGHT / 2.0f);

        this.isShielded = false;

        this.shieldImage = elementFactory.createShieldImage();
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
