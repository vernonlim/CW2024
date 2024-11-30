package dev.vernonlim.cw2024game.elements.factories;

import dev.vernonlim.cw2024game.Main;
import dev.vernonlim.cw2024game.assets.AssetLoader;
import dev.vernonlim.cw2024game.elements.*;
import dev.vernonlim.cw2024game.elements.actors.*;
import dev.vernonlim.cw2024game.managers.InputManager;
import dev.vernonlim.cw2024game.overlays.GameplayOverlay;
import javafx.scene.layout.Pane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ElementFactory {
    private Pane root;
    private final AssetLoader loader;
    private final InputManager inputManager;
    private final ProjectileListener projectileListener;

    public ElementFactory(Pane root, AssetLoader loader, InputManager inputManager, ProjectileListener projectileListener) {
        this.root = root;
        this.loader = loader;
        this.inputManager = inputManager;
        this.projectileListener = projectileListener;
    }

    public ElementFactory withNewRoot(Pane newPane) {
        return new ElementFactory(newPane, loader, inputManager, projectileListener);
    }

    private ImageView makeView(String imageName) {
        Image image = loader.loadImage(imageName);
        ImageView imageView = new ImageView(image);
        imageView.setPreserveRatio(true);

        return imageView;
    }

    public UserPlane createUserPlane(int initialHealth) {
        ImageView imageView = makeView("userplane");
        imageView.setFitHeight(40);

        return new UserPlane(this, root, projectileListener, inputManager, imageView, initialHealth);
    }

    public EnemyPlane createEnemyPlane(double initialXPos, double initialYPos) {
        ImageView imageView = makeView("enemyplane");
        imageView.setFitHeight(54);

        EnemyPlane plane = new EnemyPlane(this, root, projectileListener, imageView);
        plane.setPosition(initialXPos, initialYPos);

        return plane;
    }

    public Boss createBoss() {
        ImageView imageView = makeView("bossplane");
        imageView.setFitHeight(56);

        return new Boss(this, root, projectileListener, imageView);
    }

    public UserProjectile createUserProjectile(double initialXPos, double initialYPos) {
        ImageView imageView = makeView("userfire");
        imageView.setFitHeight(12);

        UserProjectile projectile = new UserProjectile(root, imageView);
        projectile.setPosition(initialXPos, initialYPos);

        return projectile;
    }

    public EnemyProjectile createEnemyProjectile(double initialXPos, double initialYPos) {
        ImageView imageView = makeView("enemyFire");
        imageView.setFitHeight(34);

        EnemyProjectile projectile = new EnemyProjectile(root, imageView);
        projectile.setPosition(initialXPos, initialYPos);

        return projectile;
    }

    public BossProjectile createBossProjectile(double initialXPos, double initialYPos) {
        ImageView imageView = makeView("fireball");
        imageView.setFitHeight(75);

        BossProjectile projectile = new BossProjectile(root, imageView);
        projectile.setPosition(initialXPos, initialYPos);

        return projectile;
    }

    public ShieldImage createShieldImage() {
        ImageView imageView = makeView("shield");
        imageView.setFitHeight(68);

        return new ShieldImage(root, imageView);
    }

    public Background createBackground(String imageName) {
        ImageView imageView = makeView(imageName);

        imageView.setFocusTraversable(true);
        imageView.setFitHeight(Main.SCREEN_HEIGHT);
        imageView.setFitWidth(Main.SCREEN_WIDTH);
        imageView.setPreserveRatio(false);

        return new Background(root, imageView);
    }

    public GameOverImage createGameOverImage(double xPosition, double yPosition) {
        ImageView imageView = makeView("gameover");

        GameOverImage image = new GameOverImage(root, imageView);
        image.setPosition(xPosition, yPosition);

        return image;
    }

    public Heart createHeart(Pane container) {
        ImageView imageView = makeView("heart");
        imageView.setFitHeight(50);

        return new Heart(container, imageView);
    }

    public HeartDisplay createHeartDisplay(double xPosition, double yPosition, int heartsToDisplay) {
        return new HeartDisplay(this, root, xPosition, yPosition, heartsToDisplay);
    }

    public WinImage createWinImage(double xPosition, double yPosition) {
        ImageView imageView = makeView("youwin");
        imageView.setFitHeight(500);
        imageView.setFitWidth(600);

        WinImage winImage = new WinImage(root, imageView);
        winImage.setPosition(xPosition, yPosition);

        return winImage;
    }

    public GameplayOverlay createGameplayOverlay(int heartsToDisplay) {
        return new GameplayOverlay(this, root, heartsToDisplay);
    }
}
