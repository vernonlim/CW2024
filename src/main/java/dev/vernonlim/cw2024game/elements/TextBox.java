package dev.vernonlim.cw2024game.elements;

import dev.vernonlim.cw2024game.Main;
import dev.vernonlim.cw2024game.elements.configs.ElementConfig;
import dev.vernonlim.cw2024game.elements.configs.TextBoxConfig;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class TextBox extends ContainerElement {
    public ClickListener clickListener;

    public TextBox(TextBoxConfig config) {
        super(config);

        Rectangle rectangle = new Rectangle();
        rectangle.setStroke(Color.BLACK);
        rectangle.setFill(Color.WHITE);
        rectangle.setWidth(Main.SCREEN_WIDTH * config.getRightPercent() / 100.0f);
        rectangle.setHeight(Main.SCREEN_HEIGHT / (double)config.getRows());

        this.container = new StackPane(rectangle);
        this.container.getChildren().add(config.getText());

        this.node = container;
        this.clickListener = config.getClickListener();
    }
}
