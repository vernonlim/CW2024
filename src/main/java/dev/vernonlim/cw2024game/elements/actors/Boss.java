package dev.vernonlim.cw2024game.elements.actors;

import dev.vernonlim.cw2024game.Main;
import dev.vernonlim.cw2024game.overlays.LevelView;
import javafx.scene.layout.Pane;

import java.util.*;

public class Boss extends FighterPlane {
    private static final String IMAGE_NAME = "bossplane.png";
    private static final double BOSS_FIRE_RATE = .04;
    private static final double BOSS_SHIELD_PROBABILITY = 0.002;
    private static final int IMAGE_HEIGHT = 56;
    private static final int VERTICAL_VELOCITY = 8;
    private static final int HEALTH = 100;
    private static final int MOVE_FREQUENCY_PER_CYCLE = 5;
    private static final double MAX_TIME_WITH_SAME_MOVE = 10 * 50.0f;
    private static final double MAX_TIME_WITH_SHIELD = 500 * 50.0f;
    private static final double PROJECTILE_Y_OFFSET = 0.0f;
    private final List<Integer> movePattern;
    private boolean isShielded;
    private double timeMovingInSameDirection;
    private int indexOfCurrentMove;
    private double lastShieldActivation;
    private double timeWithShieldActivated;
    private LevelView levelView;

    private ShieldImage shieldImage;

    private double upperBound;
    private double lowerBound;

    public Boss(Pane root, LevelView levelView) {
        super(root, IMAGE_NAME, IMAGE_HEIGHT, HEALTH);

        setXFromRight(5.0f);
        setY(Main.SCREEN_HEIGHT / 2.0f);

        upperBound = getHalfHeight();
        lowerBound = Main.SCREEN_HEIGHT - getHalfHeight();

        movePattern = new ArrayList<>();
        timeMovingInSameDirection = 0;
        indexOfCurrentMove = 0;
        timeWithShieldActivated = 0;
        lastShieldActivation = -99999;
        isShielded = false;
        initializeMovePattern();

        this.shieldImage = new ShieldImage(root);

        this.levelView = levelView;
    }

    @Override
    public void updatePosition(double deltaTime) {
        moveVertically(getNextMove(deltaTime) * (deltaTime / 50.0f));

        ensureInBounds();
    }

    @Override
    public void updateActor(double deltaTime, double currentTime) {
        updatePosition(deltaTime);
        updateShield(deltaTime, currentTime);
    }

    @Override
    public ActiveActorDestructible fireProjectile(double currentTime) {
        return bossFiresInCurrentFrame(currentTime)
                ? new BossProjectile(root, getX() - getHalfWidth(), getY() + PROJECTILE_Y_OFFSET)
                : null;
    }

    @Override
    public void takeDamage() {
        if (!isShielded) {
            super.takeDamage();
        }
    }

    private void initializeMovePattern() {
        for (int i = 0; i < MOVE_FREQUENCY_PER_CYCLE; i++) {
            movePattern.add(VERTICAL_VELOCITY);
            movePattern.add(-VERTICAL_VELOCITY);
            movePattern.add(0);
        }
        Collections.shuffle(movePattern);
    }

    private void updateShield(double deltaTime, double currentTime) {
        shieldImage.setPosition(getX() - getHalfWidth(), getY() - getHalfHeight());

        if (isShielded) {
            timeWithShieldActivated += deltaTime;
            lastShieldActivation = currentTime;
        } else if (shieldShouldBeActivated(currentTime)) {
            activateShield();
        }

        if (shieldExhausted()) {
            deactivateShield();
        }
    }

    private int getNextMove(double deltaTime) {
        int currentMove = movePattern.get(indexOfCurrentMove);

        timeMovingInSameDirection += deltaTime;
        if (timeMovingInSameDirection >= MAX_TIME_WITH_SAME_MOVE) {
            Collections.shuffle(movePattern);
            timeMovingInSameDirection = 0;
            indexOfCurrentMove++;
        }
        if (indexOfCurrentMove == movePattern.size()) {
            indexOfCurrentMove = 0;
        }

        return currentMove;
    }

    private boolean bossFiresInCurrentFrame(double currentTime) {
        if ((currentTime - lastFireTime) > 50.0f) {
            lastFireTime = currentTime;
            return Math.random() < BOSS_FIRE_RATE;
        }

        return false;
    }

    private boolean shieldShouldBeActivated(double currentTime) {
        if (currentTime - lastShieldActivation > 50.0f) {
            lastShieldActivation = currentTime;

            return Math.random() < BOSS_SHIELD_PROBABILITY;
        }

        return false;
    }

    private boolean shieldExhausted() {
        return timeWithShieldActivated > MAX_TIME_WITH_SHIELD;
    }

    private void activateShield() {
        isShielded = true;
        shieldImage.show();
    }

    private void deactivateShield() {
        isShielded = false;
        timeWithShieldActivated = 0;
        shieldImage.hide();
    }
}
