package dev.vernonlim.cw2024game.factories;

import dev.vernonlim.cw2024game.Main;
import dev.vernonlim.cw2024game.assets.AssetLoader;
import dev.vernonlim.cw2024game.elements.*;
import dev.vernonlim.cw2024game.elements.actors.UserPlaneCode;
import dev.vernonlim.cw2024game.elements.configs.ImageElementConfig;
import dev.vernonlim.cw2024game.elements.configs.OverlayConfig;
import dev.vernonlim.cw2024game.elements.configs.TextBoxConfig;
import dev.vernonlim.cw2024game.factories.interfaces.OverlayFactory;
import dev.vernonlim.cw2024game.managers.InputManager;
import dev.vernonlim.cw2024game.overlays.*;
import dev.vernonlim.cw2024game.screens.ScreenChangeHandler;
import dev.vernonlim.cw2024game.screens.ScreenCode;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.scene.text.Font;

public class OverlayFactoryImpl extends FactoryParent implements OverlayFactory {
    protected final InputManager input;
    protected final ScreenChangeHandler screenChangeHandler;
    protected UserPlaneCode userPlaneCode;

    public OverlayFactoryImpl(Pane root, AssetLoader loader, InputManager input, ScreenChangeHandler screenChangeHandler, UserPlaneCode userPlaneCode) {
        super(root, loader);

        this.input = input;
        this.screenChangeHandler = screenChangeHandler;
        this.userPlaneCode = userPlaneCode;
    }

    public OverlayFactory withNewRoot(Pane newRoot) {
        return new OverlayFactoryImpl(newRoot, loader, input, screenChangeHandler, userPlaneCode);
    }

    public Element createGameOverImage(double x, double y) {
        ImageElementConfig config = new ImageElementConfig(root);
        config.setPosition(x, y);
        config.setImage(loadImage("gameover"));
        config.setFitHeight(Main.SCREEN_HEIGHT);

        return new ImageElement(config);
    }

    public Element createHeart() {
        ImageElementConfig config = new ImageElementConfig(root);
        config.setImage(loadImage("heart"));
        config.setFitHeight(50.0);

        return new ImageElement(config);
    }

    public Element createWinImage(double x, double y) {
        ImageElementConfig config = new ImageElementConfig(root);
        config.setPosition(x, y);
        config.setImage(loadImage("youwin"));
        config.setFitWidth(600);
        config.setFitHeight(500);

        return new ImageElement(config);
    }

    public HeartDisplay createHeartDisplay(double x, double y, int heartsToDisplay) {
        OverlayConfig config = new OverlayConfig(root, this);
        config.setPosition(x, y);

        return new HeartDisplay(config, heartsToDisplay);
    }

    public GameplayOverlay createGameplayOverlay(int heartsToDisplay) {
        OverlayConfig config = new OverlayConfig(root, this);

        return new GameplayOverlay(config, heartsToDisplay);
    }

    public Element createMenuArrow() {
        ImageElementConfig config = new ImageElementConfig(root);
        config.setImage(loadImage("menuarrow"));
        config.setFitWidth(50);
        config.setFitHeight(50);

        return new ImageElement(config);
    }

    public MenuOverlay createMainMenuOverlay() {
        OverlayConfig config = new OverlayConfig(root, this, input, screenChangeHandler);

        return new MainMenuOverlay(config);
    }

    public MenuOverlay createCharacterSelectOverlay() {
        OverlayConfig config = new OverlayConfig(root, this, input, screenChangeHandler);

        return new CharacterSelectOverlay(config);
    }

    public MenuOverlay createLevelSelectOverlay(UserPlaneCode userPlaneCode) {
        OverlayConfig config = new OverlayConfig(root, this, input, screenChangeHandler);
        config.setUserPlaneCode(userPlaneCode);

        return new LevelSelectOverlay(config);
    }

    public MenuOverlay createPauseOverlay(ScreenCode currentScreen) {
        OverlayConfig config = new OverlayConfig(root, this, input, screenChangeHandler);
        config.setCurrentScreen(currentScreen);

        return new PauseOverlay(config);
    }

    public TextBox createTextBox(String content, ScreenCode screenCode, UserPlaneCode newUserPlaneCode, double rightPercent, int rows) {
        TextBoxConfig config = new TextBoxConfig(root, content, rightPercent, rows);
        config.getText().setFont(Font.font(50));

        AudioClip sound = loadSound("select");
        ClickListener clickListener = new ClickListener() {
            @Override
            public void onClick() {
                sound.play();
                screenChangeHandler.changeScreen(screenCode, newUserPlaneCode);
            }
        };

        config.setClickListener(clickListener);

        return new TextBox(config);
    }

    public TextBox createTextBox(String content, ScreenCode screenCode, double rightPercent, int rows) {
        return createTextBox(content, screenCode, userPlaneCode, rightPercent, rows);
    }

    public TimeDisplay createTimeDisplay(int seconds) {
        OverlayConfig config = new OverlayConfig(root);

        return new TimeDisplay(config, seconds);
    }

    public TimerOverlay createTimerOverlay(int secondsRemaining) {
        OverlayConfig config = new OverlayConfig(root, this);

        return new TimerOverlay(config, secondsRemaining);
    }
}
