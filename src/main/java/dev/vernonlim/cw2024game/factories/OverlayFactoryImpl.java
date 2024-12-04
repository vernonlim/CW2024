package dev.vernonlim.cw2024game.factories;

import dev.vernonlim.cw2024game.Controller;
import dev.vernonlim.cw2024game.ScreenCode;
import dev.vernonlim.cw2024game.assets.AssetLoader;
import dev.vernonlim.cw2024game.elements.*;
import dev.vernonlim.cw2024game.elements.actors.ActiveActor;
import dev.vernonlim.cw2024game.elements.actors.UserPlane;
import dev.vernonlim.cw2024game.elements.actors.UserPlaneCode;
import dev.vernonlim.cw2024game.factories.interfaces.OverlayFactory;
import dev.vernonlim.cw2024game.managers.InputManager;
import dev.vernonlim.cw2024game.overlays.*;
import dev.vernonlim.cw2024game.screens.ScreenChangeHandler;
import javafx.scene.image.ImageView;
import javafx.scene.media.AudioClip;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.layout.Pane;

public class OverlayFactoryImpl extends FactoryParent implements OverlayFactory {
    protected final InputManager input;
    protected final Controller controller;
    protected final ScreenChangeHandler screenChangeHandler;
    protected UserPlaneCode userPlaneCode;
    
    public OverlayFactoryImpl(Pane root, AssetLoader loader, InputManager input, Controller controller, ScreenChangeHandler screenChangeHandler, UserPlaneCode userPlaneCode) {
        super(root, loader);
        
        this.input = input;
        this.controller = controller;
        this.screenChangeHandler = screenChangeHandler;
        this.userPlaneCode = userPlaneCode;
    }

    public void changeUserPlane(UserPlaneCode userPlaneCode) {
        this.userPlaneCode = userPlaneCode;
    }

    public OverlayFactory withNewRoot(Pane newRoot) {
        return new OverlayFactoryImpl(newRoot, loader, input, controller, screenChangeHandler, userPlaneCode);
    }

    public Element createGameOverImage(double xPosition, double yPosition) {
        ImageView imageView = makeView("gameover");

        GameOverImage image = new GameOverImage(root, imageView);
        image.setPosition(xPosition, yPosition);

        return image;
    }

    public Element createHeart() {
        ImageView imageView = makeView("heart");
        imageView.setFitHeight(50);

        return new Heart(root, imageView);
    }

    public Element createWinImage(double xPosition, double yPosition) {
        ImageView imageView = makeView("youwin");
        imageView.setFitHeight(500);
        imageView.setFitWidth(600);

        WinImage winImage = new WinImage(root, imageView);
        winImage.setPosition(xPosition, yPosition);

        return winImage;
    }

    public HeartDisplay createHeartDisplay(double xPosition, double yPosition, int heartsToDisplay) {
        return new HeartDisplay(this, root, xPosition, yPosition, heartsToDisplay);
    }

    public GameplayOverlay createGameplayOverlay(int heartsToDisplay) {
        return new GameplayOverlay(this, root, heartsToDisplay);
    }

    public Element createMenuArrow() {
        ImageView imageView = makeView("menuarrow");
        imageView.setFitHeight(50);
        imageView.setFitWidth(50);

        return new ImageElement(root, imageView);
    }

    public MenuOverlay createMainMenuOverlay() {
        return new MainMenuOverlay(controller, this, root, input, screenChangeHandler);
    }

    public MenuOverlay createCharacterSelectOverlay() {
        return new CharacterSelectOverlay(controller, this, root, input, screenChangeHandler);
    }

    public MenuOverlay createPauseOverlay(ScreenCode currentScreen) {
        return new PauseOverlay(controller, this, root, input, screenChangeHandler, currentScreen);
    }

    public TextBox createTextBox(String content, ScreenCode screenCode, UserPlaneCode userPlaneCode, double rightPercent, int rows) {
        Text text = new Text(content);
        text.setFont(Font.font(50));

        AudioClip sound = loader.loadSound("select");

        ClickListener clickListener = new ClickListener() {
            @Override
            public void onClick() {
                sound.play();
                screenChangeHandler.changeScreen(screenCode, userPlaneCode);
            }
        };

        return new TextBox(root, text, clickListener, rightPercent, rows);
    }

    public TextBox createTextBox(String content, ScreenCode screenCode, double rightPercent, int rows) {
        return createTextBox(content, screenCode, userPlaneCode, rightPercent, rows);
    }
}
