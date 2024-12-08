package dev.vernonlim.cw2024game.elements;

import dev.vernonlim.cw2024game.Main;
import dev.vernonlim.cw2024game.configs.TextBoxConfig;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * An element that consists of a white rectangle with text that renders on top.
 * This is used for the "Buttons" in the game.
 */
public class TextBox extends ContainerElement {
    /**
     * The ClickListener that controls what each TextBox does when triggered.
     */
    private final ClickListener clickListener;

    /**
     * Constructs a TextBox from the given params.
     *
     * @param config the configuration object containing the necessary data to construct the TextBox
     */
    public TextBox(TextBoxConfig config) {
        super(config);

        Rectangle rectangle = new Rectangle();
        rectangle.setStroke(Color.BLACK);
        rectangle.setFill(Color.WHITE);
        rectangle.setWidth(Main.SCREEN_WIDTH * config.getRightPercent() / 100.0f);
        rectangle.setHeight(Main.SCREEN_HEIGHT / (double) config.getRows());

        this.container = new StackPane(rectangle);
        this.container.getChildren().add(config.getText());

        this.node = container;
        this.clickListener = config.getClickListener();
    }

    /**
     * Gets the ClickListener for this TextBox.
     *
     * @return the ClickListener for this TextBox
     */
    public ClickListener getClickListener() {
        return clickListener;
    }
}
