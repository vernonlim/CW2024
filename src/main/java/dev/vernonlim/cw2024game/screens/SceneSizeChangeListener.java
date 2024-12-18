package dev.vernonlim.cw2024game.screens;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Scale;

// Source:

/**
 * Handles the letterboxing of JavaFX nodes.
 * <p>
 * This class is based off of non-original code.
 * Credit: <a href="https://stackoverflow.com/questions/16606162/javafx-fullscreen-resizing-elements-based-upon-screen-size">StackOverflow</a>
 */
public class SceneSizeChangeListener implements ChangeListener<Number> {
    /**
     * The Scene associated with this instance
     */
    private final Scene scene;
    /**
     * The ratio to be kept
     */
    private final double ratio;
    /**
     * The initial height
     */
    private final double initHeight;
    /**
     * The initial width
     */
    private final double initWidth;
    /**
     * The pane containing the main content
     */
    private final Pane contentPane;

    /**
     * Constructs a SceneChangeSizeListener with the given params
     *
     * @param scene       the scene to be letterboxed
     * @param ratio       the ratio to be kept
     * @param initHeight  the initial height
     * @param initWidth   the initial width
     * @param contentPane the pane the main content is drawn on
     */
    public SceneSizeChangeListener(Scene scene, double ratio, double initHeight, double initWidth, Pane contentPane) {
        this.scene = scene;
        this.ratio = ratio;
        this.initHeight = initHeight;
        this.initWidth = initWidth;
        this.contentPane = contentPane;
    }

    /**
     * Keeps the aspect ratio of the pane's content constant by transforming it when the scene updates
     *
     * @param scene       the scene to be letterboxed
     * @param contentPane the pane the main content is drawn on
     * @param initHeight  the initial height
     * @param initWidth   the initial width
     */
    public static void letterbox(Scene scene, Pane contentPane, double initWidth, double initHeight) {
        final double ratio = initWidth / initHeight;

        SceneSizeChangeListener sizeListener = new SceneSizeChangeListener(scene, ratio, initHeight, initWidth, contentPane);
        scene.widthProperty().addListener(sizeListener);
        scene.heightProperty().addListener(sizeListener);
    }

    /**
     * The method that handles the pane's transformations on scene change
     *
     * @param observableValue The {@code ObservableValue} which value changed
     * @param oldValue        The old value
     * @param newValue        The new value
     */
    @Override
    public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
        final double newWidth = scene.getWidth();
        final double newHeight = scene.getHeight();

        double scaleFactor =
                newWidth / newHeight > ratio
                        ? newHeight / initHeight
                        : newWidth / initWidth;

        Scale scale = new Scale(scaleFactor, scaleFactor);
        scale.setPivotX(0);
        scale.setPivotY(0);
        scene.getRoot().getTransforms().setAll(scale);

        contentPane.setPrefWidth(newWidth / scaleFactor);
        contentPane.setPrefHeight(newHeight / scaleFactor);
    }
}
