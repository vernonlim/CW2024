package dev.vernonlim.cw2024game.elements.configs;

import dev.vernonlim.cw2024game.elements.ClickListener;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class TextBoxConfig extends ElementConfig {
    private ClickListener clickListener;
    private Text text;
    private double rightPercent;
    private int rows;

    public TextBoxConfig(Pane root) {
        super(root);

        this.rightPercent = 50;
        this.rows = 5;
    }

    public TextBoxConfig(Pane root, String text, double rightPercent, int rows) {
        this(root);

        this.setText(text);
        this.rightPercent = rightPercent;
        this.rows = rows;
    }

    public ClickListener getClickListener() {
        return clickListener;
    }

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public double getRightPercent() {
        return rightPercent;
    }

    public void setRightPercent(double rightPercent) {
        this.rightPercent = rightPercent;
    }

    public Text getText() {
        return text;
    }

    public void setText(String text) {
        this.text = new Text(text);
    }
}
