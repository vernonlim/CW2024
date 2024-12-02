package dev.vernonlim.cw2024game.elements;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class TextBox extends Element {
    public ClickListener clickListener;

    public TextBox(Pane root, Text text, ClickListener clickListener) {
        super(root);

        this.node = text;
        this.clickListener = clickListener;
    }
}
