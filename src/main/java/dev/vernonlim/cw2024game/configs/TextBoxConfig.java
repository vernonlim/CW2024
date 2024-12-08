package dev.vernonlim.cw2024game.configs;

import dev.vernonlim.cw2024game.elements.ClickListener;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

/**
 * A data class used for streamlining TextBox creation
 */
public class TextBoxConfig extends ElementConfig {
    /**
     * A handler determining what to do when a TextBox is triggered.
     */
    private ClickListener clickListener;

    /**
     * The content of the TextBox.
     */
    private Text text;

    /**
     * The amount of space the TextBox takes up horizontally.
     */
    private double rightPercent;

    /**
     * The number of rows in the GridPane the TextBox is based on.
     */
    private int rows;

    /**
     * Constructs a TextBoxConfig with the given params.
     *
     * @param root the root Pane on which the TextBox is based
     */
    public TextBoxConfig(Pane root) {
        super(root);

        this.rightPercent = 50;
        this.rows = 5;
    }

    /**
     * Constructs a TextBoxConfig with the given params.
     *
     * @param root the root Pane on which this is based on
     * @param text the content of the TextBox
     * @param rightPercent the amount of space the TextBox takes up horizontally
     * @param rows the number of rows in the GridPane the TextBox is based on
     */
    public TextBoxConfig(Pane root, String text, double rightPercent, int rows) {
        this(root);

        this.setText(text);
        this.rightPercent = rightPercent;
        this.rows = rows;
    }

    /**
     * Gets the ClickListener.
     * 
     * @return the ClickListener
     */
    public ClickListener getClickListener() {
        return clickListener;
    }

    /**
     * Sets the ClickListener.
     * 
     * @param clickListener the ClickListener to set
     */
    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    /**
     * Gets the Text of the TextBox.
     * 
     * @return the Text of the TextBox
     */
    public Text getText() {
        return text;
    }

    /**
     * Sets the Text of the TextBox.
     * 
     * @param text the Text to set
     */
    public void setText(String text) {
        this.text = new Text(text);
    }

    /**
     * Gets the amount of space the TextBox takes up horizontally.
     * 
     * @return the amount of space the TextBox takes up in percent
     */
    public double getRightPercent() {
        return rightPercent;
    }

    /**
     * Sets the amount of space the TextBox takes up horizontally.
     *
     * @param rightPercent the amount of space the TextBox should take up
     */
    public void setRightPercent(double rightPercent) {
        this.rightPercent = rightPercent;
    }

    /**
     * Gets the number of rows.
     * 
     * @return the number of rows
     */
    public int getRows() {
        return rows;
    }

    /**
     * Sets the number of rows.
     * 
     * @param rows the number of rows to set
     */
    public void setRows(int rows) {
        this.rows = rows;
    }
}
