package dev.vernonlim.cw2024game.assets;

import javafx.scene.image.Image;
import javafx.scene.media.Media;

import java.util.Map;

import static java.util.Map.entry;

public abstract class AssetLoader {
    protected static final Map<String, String> imagePaths = Map.ofEntries(
            entry("userplane", "/images/userplane.png"),
            entry("userfire", "/images/userfire.png"),
            entry("enemyplane", "/images/enemyplane.png"),
            entry("enemyFire", "/images/enemyFire.png"),
            entry("bossplane", "/images/bossplane.png"),
            entry("shield", "/images/shield.png"),
            entry("fireball", "/images/fireball.png"),
            entry("background1", "/images/background1.jpg"),
            entry("background2", "/images/background2.jpg"),
            entry("gameover", "/images/gameover.png"),
            entry("heart", "/images/heart.png"),
            entry("youwin", "/images/youwin.png"),
            entry("notfound", "/images/notfound.png")
    );

    protected static final Map<String, String> soundPaths = Map.ofEntries(
            entry("gunshot", "/audio/gunshot.mp3"),
            entry("error", "/audio/error.mp3")
    );

    public abstract Image loadImage(String name);

    public abstract Media loadSound(String name);
}
