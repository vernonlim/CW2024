package dev.vernonlim.cw2024game.elements;

import dev.vernonlim.cw2024game.Main;
import dev.vernonlim.cw2024game.configs.ElementConfig;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * An element consisting of a red rectangle with a time rendered on top of it.
 * This is used for the Countdown in the Countdown Levels.
 */
public class TimeDisplay extends ContainerElement {
    /**
     * The Text representing the remaining time.
     */
    private final Text text;

    /**
     * Constructs a TimeDisplay from the given params.
     *
     * @param config  the configuration object containing the necessary data to construct the TimeDisplay
     * @param seconds the amount of time initially displayed
     */
    public TimeDisplay(ElementConfig config, int seconds) {
        super(config);

        Rectangle rectangle = new Rectangle();
        rectangle.setStroke(Color.BLACK);
        rectangle.setFill(Color.RED);
        rectangle.setWidth(Main.SCREEN_WIDTH / 8.0f);
        rectangle.setHeight(Main.SCREEN_HEIGHT / 10.0f);

        this.container = new StackPane(rectangle);

        this.text = new Text(Integer.toString(seconds));
        text.setFont(Font.font(50));

        this.container.getChildren().add(text);

        this.node = container;

        setXFromRight(0);
        setY(getHalfHeight());
    }

    /**
     * Sets the time displayed to the given param.
     *
     * @param seconds the time to be displayed
     */
    public void setTime(int seconds) {
        this.text.setText(Integer.toString(seconds));
    }
}
