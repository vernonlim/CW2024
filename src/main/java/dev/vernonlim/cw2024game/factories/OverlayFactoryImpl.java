package dev.vernonlim.cw2024game.factories;

import dev.vernonlim.cw2024game.Main;
import dev.vernonlim.cw2024game.assets.AssetLoader;
import dev.vernonlim.cw2024game.configs.ImageElementConfig;
import dev.vernonlim.cw2024game.configs.OverlayConfig;
import dev.vernonlim.cw2024game.configs.TextBoxConfig;
import dev.vernonlim.cw2024game.elements.*;
import dev.vernonlim.cw2024game.elements.actors.UserPlaneCode;
import dev.vernonlim.cw2024game.factories.interfaces.OverlayFactory;
import dev.vernonlim.cw2024game.managers.InputManager;
import dev.vernonlim.cw2024game.overlays.*;
import dev.vernonlim.cw2024game.screens.ScreenChangeHandler;
import dev.vernonlim.cw2024game.screens.ScreenCode;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.scene.text.Font;

/**
 * The default implementation of OverlayFactory for CW2024Game.
 */
public class OverlayFactoryImpl extends Factory implements OverlayFactory {
    /**
     * The InputManager for this Factory. Meant for passing to Overlays.
     */
    private final InputManager input;

    /**
     * The ScreenChangeHandler for this Factory. Meant for passing to Overlays.
     */
    private final ScreenChangeHandler screenChangeHandler;

    /**
     * The UserPlaneCode for this Factory. Meant for passing to Overlays.
     */
    private final UserPlaneCode userPlaneCode;

    /**
     * Constructs an OverlayFactory from the given params.
     *
     * @param root                the root Pane this OverlayFactory should be based on
     * @param loader              the AssetLoader handling loading of assets
     * @param input               the InputManager
     * @param screenChangeHandler the ScreenChangeHandler
     * @param userPlaneCode       the UserPlaneCode that interactive Overlays will use
     */
    public OverlayFactoryImpl(Pane root, AssetLoader loader, InputManager input, ScreenChangeHandler screenChangeHandler, UserPlaneCode userPlaneCode) {
        super(root, loader);

        this.input = input;
        this.screenChangeHandler = screenChangeHandler;
        this.userPlaneCode = userPlaneCode;
    }

    @Override
    public OverlayFactory withNewRoot(Pane newRoot) {
        return new OverlayFactoryImpl(newRoot, loader, input, screenChangeHandler, userPlaneCode);
    }

    @Override
    public Element createGameOverImage(double x, double y) {
        ImageElementConfig config = new ImageElementConfig(root);
        config.setPosition(x, y);
        config.setImage(loadImage("gameover"));
        config.setFitHeight(Main.SCREEN_HEIGHT);

        return new ImageElement(config);
    }

    @Override
    public Element createHeart() {
        ImageElementConfig config = new ImageElementConfig(root);
        config.setImage(loadImage("heart"));
        config.setFitHeight(50.0);

        return new ImageElement(config);
    }

    @Override
    public Element createWinImage(double x, double y) {
        ImageElementConfig config = new ImageElementConfig(root);
        config.setPosition(x, y);
        config.setImage(loadImage("youwin"));
        config.setFitWidth(600);
        config.setFitHeight(500);

        return new ImageElement(config);
    }

    @Override
    public HeartDisplay createHeartDisplay(double x, double y, int heartsToDisplay) {
        OverlayConfig config = new OverlayConfig(root, this);
        config.setPosition(x, y);

        return new HeartDisplay(config, heartsToDisplay);
    }

    @Override
    public GameplayOverlay createGameplayOverlay(int heartsToDisplay) {
        OverlayConfig config = new OverlayConfig(root, this);

        return new GameplayOverlay(config, heartsToDisplay);
    }

    @Override
    public Element createMenuArrow() {
        ImageElementConfig config = new ImageElementConfig(root);
        config.setImage(loadImage("menuarrow"));
        config.setFitWidth(50);
        config.setFitHeight(50);

        return new ImageElement(config);
    }

    @Override
    public MenuOverlay createMainMenuOverlay() {
        OverlayConfig config = new OverlayConfig(root, this, input, screenChangeHandler);

        return new MainMenuOverlay(config);
    }

    @Override
    public MenuOverlay createCharacterSelectOverlay() {
        OverlayConfig config = new OverlayConfig(root, this, input, screenChangeHandler);

        return new CharacterSelectOverlay(config);
    }

    @Override
    public MenuOverlay createLevelSelectOverlay(UserPlaneCode userPlaneCode) {
        OverlayConfig config = new OverlayConfig(root, this, input, screenChangeHandler);
        config.setUserPlaneCode(userPlaneCode);

        return new LevelSelectOverlay(config);
    }

    @Override
    public MenuOverlay createPauseOverlay(ScreenCode currentScreen) {
        OverlayConfig config = new OverlayConfig(root, this, input, screenChangeHandler);
        config.setCurrentScreen(currentScreen);

        return new PauseOverlay(config);
    }

    @Override
    public TextBox createTextBox(String content, ScreenCode screenCode, UserPlaneCode newUserPlaneCode, double rightPercent, int rows) {
        TextBoxConfig config = new TextBoxConfig(root, content, rightPercent, rows);
        config.getText().setFont(Font.font(50));

        AudioClip sound = loadSound("select");
        ClickListener clickListener = () -> {
            sound.play();
            screenChangeHandler.changeScreen(screenCode, newUserPlaneCode);
        };

        config.setClickListener(clickListener);

        return new TextBox(config);
    }

    @Override
    public TextBox createTextBox(String content, ScreenCode screenCode, double rightPercent, int rows) {
        return createTextBox(content, screenCode, userPlaneCode, rightPercent, rows);
    }

    @Override
    public TimeDisplay createTimeDisplay(int seconds) {
        OverlayConfig config = new OverlayConfig(root);

        return new TimeDisplay(config, seconds);
    }

    @Override
    public TimerOverlay createTimerOverlay(int secondsRemaining) {
        OverlayConfig config = new OverlayConfig(root, this);

        return new TimerOverlay(config, secondsRemaining);
    }
}
