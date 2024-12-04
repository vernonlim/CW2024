package dev.vernonlim.cw2024game.elements;

import dev.vernonlim.cw2024game.Main;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class TimeDisplay extends Element {
    public Text text;

    public TimeDisplay(Pane root, int seconds) {
        super(root);

        Rectangle rectangle = new Rectangle();
        rectangle.setStroke(Color.BLACK);
        rectangle.setFill(Color.RED);
        rectangle.setWidth(Main.SCREEN_WIDTH / 8.0f);
        rectangle.setHeight(Main.SCREEN_HEIGHT / 10.0f);

        StackPane pane = new StackPane(rectangle);

        this.text = new Text(Integer.toString(seconds));
        text.setFont(Font.font(50));

        pane.getChildren().add(text);

        this.node = pane;

        setXFromRight(0);
        setY(getHalfHeight());
    }

    public void setTime(int seconds) {
        this.text.setText(Integer.toString(seconds));
    }
}
