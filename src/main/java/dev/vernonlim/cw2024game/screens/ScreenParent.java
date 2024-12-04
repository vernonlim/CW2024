package dev.vernonlim.cw2024game.screens;

import dev.vernonlim.cw2024game.Controller;
import dev.vernonlim.cw2024game.Main;
import dev.vernonlim.cw2024game.assets.AssetLoader;
import dev.vernonlim.cw2024game.elements.Element;
import dev.vernonlim.cw2024game.elements.actors.UserPlaneCode;
import dev.vernonlim.cw2024game.factories.ElementFactoryImpl;
import dev.vernonlim.cw2024game.factories.OverlayFactoryImpl;
import dev.vernonlim.cw2024game.factories.interfaces.ElementFactory;
import dev.vernonlim.cw2024game.factories.interfaces.OverlayFactory;
import dev.vernonlim.cw2024game.managers.InputManager;
import dev.vernonlim.cw2024game.managers.KeybindStore;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public abstract class ScreenParent implements Screen {
    protected UserPlaneCode userPlaneCode;

    protected final ScreenCode currentScreen;

    protected final Pane root;
    protected final StackPane stackPane;
    protected final Scene scene;
    protected final Controller controller;
    protected final InputManager inputManager;
    protected final AssetLoader loader;
    protected final Element background;
    protected final ElementFactory elementFactory;
    protected final OverlayFactory overlayFactory;
    protected final Timeline timeline;

    public ScreenParent(Controller controller, AssetLoader loader, KeybindStore keybinds, String backgroundImagePath, ScreenCode currentScreen, UserPlaneCode userPlaneCode) {
        this.currentScreen = currentScreen;
        this.userPlaneCode = userPlaneCode;

        // initializing the main nodes
        this.root = new Pane();

        // VERY important - limits the size of the Pane Node used for drawing
        // This allows the StackPane to properly align it
        root.setMaxHeight(Main.SCREEN_HEIGHT);
        root.setMaxWidth(Main.SCREEN_WIDTH);
        root.setClip(new Rectangle(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT));

        // To align the root pane above
        this.stackPane = new StackPane(root);
        stackPane.setStyle("-fx-background-color: black;");

        // Keeps the "camera" of sorts fixed in place
        this.scene = new Scene(new Group(stackPane));

        // letterboxing
        SceneSizeChangeListener.letterbox(scene, stackPane, Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);

        this.inputManager = new InputManager(scene, keybinds);
        this.controller = controller;
        this.loader = loader;

        this.elementFactory = new ElementFactoryImpl(root, loader);
        this.overlayFactory = new OverlayFactoryImpl(stackPane, loader, inputManager, controller, new ScreenChangeHandler() {
            @Override
            public void changeScreen(ScreenCode code, UserPlaneCode userPlaneCode) {
                goToScreen(code, userPlaneCode);
            }
        }, userPlaneCode);

        // background
        this.background = elementFactory.createBackground(backgroundImagePath);

        // activity
        this.timeline = new Timeline(Main.FRAME_RATE);

        initializeTimeline();
    }

    protected abstract void updateScene();

    public Scene getScene() {
        return scene;
    }

    public void goToScreen(ScreenCode screen, UserPlaneCode userPlaneCode) {
        timeline.pause();
        Platform.runLater(() -> {
            controller.goToScreen(screen, userPlaneCode);
        });
    }

    public void start() {
        background.node.requestFocus();
        timeline.play();
    }

    protected void initializeTimeline() {
        timeline.setCycleCount(Timeline.INDEFINITE);
        KeyFrame gameLoop = new KeyFrame(Duration.millis(1000f / Main.FRAME_RATE), e -> updateScene());
        timeline.getKeyFrames().add(gameLoop);
    }
}
