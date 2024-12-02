package dev.vernonlim.cw2024game.elements.actors;

import dev.vernonlim.cw2024game.Main;
import dev.vernonlim.cw2024game.elements.Element;
import dev.vernonlim.cw2024game.elements.ProjectileCode;
import dev.vernonlim.cw2024game.elements.ProjectileListener;
import dev.vernonlim.cw2024game.factories.interfaces.ElementFactory;
import dev.vernonlim.cw2024game.factories.interfaces.ProjectileFactory;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.Collections;
import javafx.scene.image.ImageView;
import java.util.List;

public class Boss extends FighterPlane {
    private static final double BOSS_FIRE_RATE = .04;
    private static final double BOSS_SHIELD_PROBABILITY = 0.002;
    private static final int VERTICAL_VELOCITY = 8;
    private static final int HEALTH = 100;
    private static final int MOVE_FREQUENCY_PER_CYCLE = 5;
    private static final double MAX_TIME_WITH_SAME_MOVE = 10 * 50.0f;
    private static final double MAX_TIME_WITH_SHIELD = 500 * 50.0f;
    private static final double PROJECTILE_Y_OFFSET = 0.0f;
    private final List<Integer> movePattern;
    private final Element shieldImage;
    private boolean isShielded;
    private double timeMovingInSameDirection;
    private int indexOfCurrentMove;
    private double lastShieldActivation;
    private double timeWithShieldActivated;

    protected ElementFactory elementFactory;

    public Boss(ElementFactory elementFactory, ProjectileFactory projectileFactory, Pane root, ProjectileListener projectileListener, ImageView imageView) {
        super(projectileFactory, root, projectileListener, imageView, HEALTH);

        this.elementFactory = elementFactory;

        setXFromRight(5.0f);
        setY(Main.SCREEN_HEIGHT / 2.0f);

        movePattern = new ArrayList<>();
        timeMovingInSameDirection = 0;
        indexOfCurrentMove = 0;
        timeWithShieldActivated = 0;
        lastShieldActivation = -99999;
        isShielded = false;
        initializeMovePattern();

        this.shieldImage = elementFactory.createShieldImage();
    }

    @Override
    public void updatePosition(double deltaTime) {
        moveVertically(getNextMove(deltaTime) * (deltaTime / 50.0f));

        ensureInBounds();
    }

    @Override
    public void updateActor(double deltaTime, double currentTime) {
        super.updateActor(deltaTime, currentTime);

        updatePosition(deltaTime);
        updateShield(deltaTime, currentTime);

        if (bossFiresInCurrentFrame(currentTime)) {
            fireProjectile();
        }
    }

    @Override
    public Projectile createProjectile() {
        return projectileFactory.createProjectile(ProjectileCode.BOSS,getX() - getHalfWidth(), getY() + PROJECTILE_Y_OFFSET);
    }

    @Override
    public void takeDamage(int damage) {
        if (!isShielded) {
            super.takeDamage(damage);
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
