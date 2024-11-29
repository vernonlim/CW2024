package dev.vernonlim.cw2024game.actors;

import dev.vernonlim.cw2024game.Main;
import dev.vernonlim.cw2024game.overlays.LevelViewLevelTwo;

import java.util.*;

public class Boss extends FighterPlane {
    private static final String IMAGE_NAME = "bossplane.png";
    private static final double INITIAL_X_POSITION = Main.SCREEN_WIDTH - 300;
    private static final double INITIAL_Y_POSITION = 400;
    private static final double PROJECTILE_Y_POSITION_OFFSET = 75.0;
    private static final double BOSS_FIRE_RATE = .04;
    private static final double BOSS_SHIELD_PROBABILITY = 0.002;
    private static final int IMAGE_HEIGHT = 56;
    private static final int VERTICAL_VELOCITY = 8;
    private static final int HEALTH = 100;
    private static final int MOVE_FREQUENCY_PER_CYCLE = 5;
    private static final int ZERO = 0;
    private static final double MAX_TIME_WITH_SAME_MOVE = 10 * 50.0f;
    private static final int Y_POSITION_UPPER_BOUND = 0;
    private static final int Y_POSITION_LOWER_BOUND = 720-56;
    private static final double MAX_TIME_WITH_SHIELD = 500 * 50.0f;
    private final List<Integer> movePattern;
    private boolean isShielded;
    private double timeMovingInSameDirection;
    private int indexOfCurrentMove;
    private double lastShieldActivation;
    private double timeWithShieldActivated;
    private LevelViewLevelTwo levelView;

    public Boss(LevelViewLevelTwo levelView) {
        super(IMAGE_NAME, IMAGE_HEIGHT, INITIAL_X_POSITION, INITIAL_Y_POSITION, HEALTH);
        System.out.println(this.getLayoutBounds().getWidth());
        movePattern = new ArrayList<>();
        timeMovingInSameDirection = 0;
        indexOfCurrentMove = 0;
        timeWithShieldActivated = 0;
        lastShieldActivation = -99999;
        isShielded = false;
        initializeMovePattern();

        this.levelView = levelView;
    }

    @Override
    public void updatePosition(double deltaTime) {
        double initialTranslateY = getTranslateY();
        moveVertically(getNextMove(deltaTime) * (deltaTime / 50.0f));
        double currentPosition = getLayoutY() + getTranslateY();
        if (currentPosition < Y_POSITION_UPPER_BOUND || currentPosition > Y_POSITION_LOWER_BOUND) {
            setTranslateY(initialTranslateY);
        }
    }

    @Override
    public void updateActor(double deltaTime, double currentTime) {
        updatePosition(deltaTime);
        updateShield(deltaTime, currentTime);
    }

    @Override
    public ActiveActorDestructible fireProjectile(double currentTime) {
        return bossFiresInCurrentFrame(currentTime) ? new BossProjectile(getProjectileInitialPosition()) : null;
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
            movePattern.add(ZERO);
        }
        Collections.shuffle(movePattern);
    }

    private void updateShield(double deltaTime, double currentTime) {
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

    private double getProjectileInitialPosition() {
        return getLayoutY() + getTranslateY() + PROJECTILE_Y_POSITION_OFFSET;
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
        levelView.showShield();
    }

    private void deactivateShield() {
        isShielded = false;
        timeWithShieldActivated = 0;
        levelView.hideShield();
    }
}
