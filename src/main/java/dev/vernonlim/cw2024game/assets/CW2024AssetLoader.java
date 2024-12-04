package dev.vernonlim.cw2024game.assets;

import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.AudioClip;

import java.util.Map;

import static java.util.Map.entry;

abstract class CW2024AssetLoader implements AssetLoader {
    protected static final Map<String, String> imagePaths = Map.ofEntries(
            entry("userplane", "/images/userplane.png"),
            entry("userplane2", "/images/userplane2.png"),
            entry("userfire", "/images/userfire.png"),
            entry("enemyplane", "/images/enemyplane.png"),
            entry("enemyred", "/images/enemyred.png"),
            entry("enemyblue", "/images/enemyblue.png"),
            entry("enemyFire", "/images/enemyFire.png"),
            entry("bossplane", "/images/bossplane.png"),
            entry("finalboss", "/images/finalboss.png"),
            entry("shield", "/images/shield.png"),
            entry("fireball", "/images/fireball.png"),
            entry("mainmenu", "/images/mainmenu.png"),
            entry("background1", "/images/background1.jpg"),
            entry("background2", "/images/background2.jpg"),
            entry("background3", "/images/background3.png"),
            entry("background4", "/images/background4.png"),
            entry("background5", "/images/background5.png"),
            entry("gameover", "/images/gameover.png"),
            entry("heart", "/images/heart.png"),
            entry("youwin", "/images/youwin.png"),
            entry("notfound", "/images/notfound.png"),
            entry("circlebullet", "/images/circlebullet.png"),
            entry("ballofdeath", "/images/ballofdeath.png"),
            entry("circlebulletgreen", "/images/circlebulletgreen.png"),
            entry("circlebulletblue", "/images/circlebulletblue.png"),
            entry("menuarrow", "/images/menuarrow.png")
    );

    protected static final Map<String, String> soundPaths = Map.ofEntries(
            entry("gunshot", "/audio/gunshot.wav"),
            entry("laser", "/audio/laser.wav"),
            entry("missile", "/audio/missile.wav"),
            entry("metalhit", "/audio/metalhit.wav"),
            entry("gamestart", "/audio/gamestart.wav"),
            entry("fireball", "/audio/fireball.wav"),
            entry("explosion", "/audio/explosion.wav"),
            entry("select", "/audio/select.wav"),
            entry("pichuun", "/audio/pichuun.wav"),
            entry("click", "/audio/click.wav"),
            entry("error", "/audio/error.wav")
    );

    public abstract Image loadImage(String name);

    public abstract AudioClip loadSound(String name);
}
