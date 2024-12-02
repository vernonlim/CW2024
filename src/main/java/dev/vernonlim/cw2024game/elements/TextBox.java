package dev.vernonlim.cw2024game.elements;

import dev.vernonlim.cw2024game.Main;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class TextBox extends Element {
    public ClickListener clickListener;

    public TextBox(Pane root, Text text, ClickListener clickListener, double rightPercent, int rows) {
        super(root);

        Rectangle rectangle = new Rectangle();
        rectangle.setStroke(Color.BLACK);
        rectangle.setFill(Color.WHITE);
        rectangle.setWidth(Main.SCREEN_WIDTH * rightPercent / 100.0f);
        rectangle.setHeight(Main.SCREEN_HEIGHT / (double)rows);

        StackPane pane = new StackPane(rectangle);

        pane.getChildren().add(text);

        this.node = pane;
        this.clickListener = clickListener;
    }
}
