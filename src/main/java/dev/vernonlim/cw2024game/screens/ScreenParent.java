package dev.vernonlim.cw2024game.screens;

import dev.vernonlim.cw2024game.Controller;
import dev.vernonlim.cw2024game.Main;
import dev.vernonlim.cw2024game.assets.AssetLoader;
import dev.vernonlim.cw2024game.configs.ScreenConfig;
import dev.vernonlim.cw2024game.elements.Element;
import dev.vernonlim.cw2024game.elements.actors.UserPlaneCode;
import dev.vernonlim.cw2024game.factories.ElementFactoryImpl;
import dev.vernonlim.cw2024game.factories.OverlayFactoryImpl;
import dev.vernonlim.cw2024game.factories.interfaces.ElementFactory;
import dev.vernonlim.cw2024game.factories.interfaces.OverlayFactory;
import dev.vernonlim.cw2024game.managers.InputManager;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

/**
 * An abstract class containing common logic for all screens
 */
public abstract class ScreenParent implements Screen {
    /**
     * The corresponding code for this screen instance.
     */
    protected final ScreenCode currentScreen;
    /**
     * The InputManager associated with this Screen. Meant to be passed to Actors.
     */
    protected final InputManager inputManager;
    /**
     * The AssetLoader associated with this Screen. Meant to be passed to Factories.
     */
    protected final AssetLoader loader;
    /**
     * The background used for this Screen.
     */
    protected final Element background;
    /**
     * The ElementFactory associated with this Screen.
     */
    protected final ElementFactory elementFactory;
    /**
     * The OverlayFactory associated with this Screen.
     */
    protected final OverlayFactory overlayFactory;
    /**
     * The Timeline for this Screen, which controls the animations played.
     */
    protected final Timeline timeline;
    /**
     * The current UserPlane code for the Screen.
     */
    protected final UserPlaneCode userPlaneCode;
    /**
     * The root node where this screen is drawn.
     */
    protected Pane root;
    /**
     * The StackPane used to wrap the root node to handle node layering and alignment.
     */
    protected StackPane stackPane;
    /**
     * The Scene associated with this Screen.
     */
    protected Scene scene;

    /**
     * Constructs a Screen with no content on its Timeline.
     *
     * @param config the configuration object containing the necessary data to construct the Screen
     */
    public ScreenParent(ScreenConfig config) {
        this.currentScreen = config.getCurrentScreenCode();
        this.userPlaneCode = config.getUserPlaneCode();

        initializeNodes();

        this.loader = config.getAssetLoader();
        this.inputManager = new InputManager(scene, config.getKeybindStore());
        this.elementFactory = new ElementFactoryImpl(root, loader);
        this.overlayFactory = new OverlayFactoryImpl(stackPane, loader, inputManager, new ScreenChangeHandler() {
            @Override
            public void changeScreen(ScreenCode code, UserPlaneCode userPlaneCode) {
                goToScreen(code, userPlaneCode);
            }
        }, userPlaneCode);

        // background
        this.background = elementFactory.createBackground(config.getBackgroundImageName());

        // activity
        this.timeline = new Timeline(Main.FRAME_RATE);

        initializeTimeline();
    }

    /**
     * Initializes the nodes of this Screen.
     * <p>
     * The root node where most content is drawn is initialized to a Pane, which is then added as a child to a StackPane.
     * <p>
     * Other nodes added to the StackPane are drawn above the root.
     * <p>
     * Finally, sets up letterboxing for the nodes, keeping a fixed aspect ratio and coordinate system when the window is rescaled.
     */
    private void initializeNodes() {
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
    }

    /**
     * Initializes this Screen's Timeline
     */
    protected void initializeTimeline() {
        timeline.setCycleCount(Timeline.INDEFINITE);
        KeyFrame gameLoop = new KeyFrame(Duration.millis(1000f / Main.FRAME_RATE), e -> updateScene());
        timeline.getKeyFrames().add(gameLoop);
    }

    @Override
    public Scene getScene() {
        return scene;
    }

    /**
     * Updates the scene with any changes that need to happen.
     * <p>
     * This is usually called every frame.
     */
    protected abstract void updateScene();

    /**
     * Loads a screen with a specific UserPlane after pausing the Screen's Timeline.
     *
     * @param screenCode    the code of the screen to load
     * @param userPlaneCode the code of the user's plane
     */
    public void goToScreen(ScreenCode screenCode, UserPlaneCode userPlaneCode) {
        timeline.pause();
        Platform.runLater(() -> {
            Controller.getController().goToScreen(screenCode, userPlaneCode);
        });
    }

    /**
     * Requests focus for the background and starts the Screen's Timeline, making it begin rendering the game.
     */
    @Override
    public void start() {
        background.node.requestFocus();
        timeline.play();
    }
}
