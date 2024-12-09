package dev.vernonlim.cw2024game.overlays;

import dev.vernonlim.cw2024game.Main;
import dev.vernonlim.cw2024game.configs.OverlayConfig;
import dev.vernonlim.cw2024game.elements.ContainerElement;
import dev.vernonlim.cw2024game.elements.Element;
import dev.vernonlim.cw2024game.elements.TextBox;
import dev.vernonlim.cw2024game.factories.interfaces.OverlayFactory;
import dev.vernonlim.cw2024game.managers.InputManager;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;

import java.util.ArrayList;

/**
 * An abstract class representing a Menu Overlay - an overlay consisting of interactive buttons.
 */
public abstract class MenuOverlay extends ContainerElement {

    /**
     * The OverlayFactory for constructing Elements within a grid.
     */
    private final OverlayFactory gridElementFactory;

    /**
     * The InputManager for this Overlay.
     */
    private final InputManager input;

    /**
     * The Element used to indicate the current selected button.
     */
    private final Element menuArrow;

    /**
     * The list of buttons for this Overlay
     */
    private final ArrayList<TextBox> buttons;

    /**
     * A node that organizes children with a grid. Used for displaying buttons.
     */
    private GridPane gridPane;

    /**
     * The total number of rows in the Grid.
     */
    private int totalRows;

    /**
     * The starting row for the top button.
     */
    private int startIndex;

    /**
     * The current button selected.
     */
    private int currentButton;

    /**
     * The total number of buttons.
     */
    private int totalButtons;

    /**
     * The last time the movement for this Overlay was updated.
     */
    private double lastMovementUpdate;

    /**
     * The percentage of screen space the buttons should take up.
     */
    private double rightPercent;

    /**
     * Constructs a MenuOverlay.
     *
     * @param config the configuration object containing the necessary data to construct the Overlay
     */
    public MenuOverlay(OverlayConfig config) {
        super(config);

        this.input = config.getInput();

        this.buttons = new ArrayList<>();

        initializeValues();
        initializeNodes();

        OverlayFactory overlayElementFactory = config.getOverlayFactory().withNewRoot(container);
        this.gridElementFactory = config.getOverlayFactory().withNewRoot(gridPane);

        this.menuArrow = overlayElementFactory.createMenuArrow();
        this.menuArrow.show();
    }

    /**
     * Initializes the values for this Overlay.
     */
    private void initializeValues() {
        startIndex = 3;
        currentButton = 0;
        rightPercent = 40.0f;
        totalRows = 9;

        lastMovementUpdate = System.currentTimeMillis();
    }

    /**
     * Initializes the Nodes for this Overlay.
     */
    private void initializeNodes() {
        this.gridPane = new GridPane();
        this.container = new Pane(gridPane);
        this.node = container;

        this.container.setMaxWidth(Main.SCREEN_WIDTH);
        this.container.setMaxHeight(Main.SCREEN_HEIGHT);

        this.gridPane.setMaxWidth(Main.SCREEN_WIDTH);
        this.gridPane.setMaxHeight(Main.SCREEN_HEIGHT);
        this.gridPane.setMinWidth(Main.SCREEN_WIDTH);
        this.gridPane.setMinHeight(Main.SCREEN_HEIGHT);

        ColumnConstraints colConst = new ColumnConstraints();
        colConst.setPercentWidth(100.0f - rightPercent);
        this.gridPane.getColumnConstraints().add(colConst);

        ColumnConstraints colConst2 = new ColumnConstraints();
        colConst2.setPercentWidth(rightPercent);
        this.gridPane.getColumnConstraints().add(colConst2);

        for (int i = 0; i < totalRows; i++) {
            RowConstraints rowConst = new RowConstraints();
            rowConst.setPercentHeight(100.0 / totalRows);
            this.gridPane.getRowConstraints().add(rowConst);
        }
    }

    /**
     * Adds a button to the Overlay grid.
     *
     * @param box the TextBox representing the button
     */
    protected void addButton(TextBox box) {
        gridPane.add(box.node, 1, startIndex + totalButtons);
        GridPane.setHalignment(box.node, HPos.LEFT);
        GridPane.setValignment(box.node, VPos.CENTER);

        buttons.add(box);

        totalButtons += 1;
    }

    /**
     * Updates the Overlay.
     *
     * @param currentTime the current virtual time
     */
    public void update(double currentTime) {
        if (currentTime - lastMovementUpdate > 100.0f) {
            updateInput(currentTime);
        }

        int row = currentButton + startIndex + 1;

        menuArrow.setPosition(getLeftX(), getYAt(row));
    }

    /**
     * Updates the current state of input.
     *
     * @param currentTime the current virtual time
     */
    protected void updateInput(double currentTime) {
        boolean triggered = input.isConfirmPressed();
        boolean down = input.isDownPressed();
        boolean up = input.isUpPressed();
        boolean both = down && up;

        if (triggered || up || down) {
            lastMovementUpdate = currentTime;
        }

        if (triggered) {
            buttons.get(currentButton).getClickListener().onClick();
        }

        if (up && !both) {
            currentButton = getPreviousButton(currentButton);
        } else if (down && !both) {
            currentButton = getNextButton(currentButton);
        }
    }

    /**
     * Gets the next button in sequence.
     *
     * @param currentButton the current button in sequence
     * @return the next button in sequence
     */
    protected int getNextButton(int currentButton) {
        if (currentButton + 1 >= totalButtons) {
            return 0;
        }

        return currentButton + 1;
    }

    /**
     * Gets the previous button in sequence.
     *
     * @param currentButton the current button in sequence
     * @return the previous button in sequence
     */
    protected int getPreviousButton(int currentButton) {
        if (currentButton - 1 < 0) {
            return totalButtons - 1;
        }

        return currentButton - 1;
    }

    /**
     * Gets the Y position at a certain row.
     *
     * @param row the row
     * @return the Y position
     */
    protected double getYAt(int row) {
        double height = Main.SCREEN_HEIGHT / (double) totalRows;

        return (height * row) - (height / 2);
    }

    /**
     * Gets the top-left X position of a button.
     *
     * @return the X position
     */
    protected double getLeftX() {
        double width = Main.SCREEN_WIDTH * ((100 - rightPercent) / 100.0f);

        return width - menuArrow.getHalfWidth();
    }

    /**
     * Gets the OverlayFactory for this Overlay for production of grid elements.
     *
     * @return the OverlayFactory
     */
    public OverlayFactory getGridElementFactory() {
        return gridElementFactory;
    }

    /**
     * Gets the percentage of space buttons take up.
     *
     * @return the percentage of space buttons take up
     */
    public double getRightPercent() {
        return rightPercent;
    }

    /**
     * Gets the total number of rows in the grid.
     *
     * @return the total number of rows in the grid
     */
    public int getTotalRows() {
        return totalRows;
    }
}
