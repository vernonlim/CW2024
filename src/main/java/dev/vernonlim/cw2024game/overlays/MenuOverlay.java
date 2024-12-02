package dev.vernonlim.cw2024game.overlays;

import dev.vernonlim.cw2024game.Controller;
import dev.vernonlim.cw2024game.Main;
import dev.vernonlim.cw2024game.elements.Element;
import dev.vernonlim.cw2024game.elements.TextBox;
import dev.vernonlim.cw2024game.factories.interfaces.OverlayFactory;
import dev.vernonlim.cw2024game.managers.InputManager;
import dev.vernonlim.cw2024game.screens.ScreenChangeHandler;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public abstract class MenuOverlay extends Element {
    protected final GridPane gridPane;
    protected final Pane pane;
    protected final OverlayFactory overlayElementFactory;
    protected final OverlayFactory gridElementFactory;
    protected final InputManager input;
    protected final Controller controller;
    protected final ScreenChangeHandler screenChangeHandler;

    protected final Element menuArrow;

    protected final ArrayList<TextBox> buttons;

    protected int startIndex;
    protected int currentButton;
    protected int totalButtons;
    protected final int totalRows;

    protected double lastUpdate;
    protected double lastMovementUpdate;

    protected double leftPercent;

    public MenuOverlay(Controller controller, OverlayFactory overlayFactory, Pane root, InputManager input, ScreenChangeHandler screenChangeHandler) {
        super(root);

        this.controller = controller;
        this.input = input;
        this.screenChangeHandler = screenChangeHandler;

        this.buttons = new ArrayList<>();

        this.startIndex = 3;
        this.currentButton = 0;
        this.leftPercent = 40.0f;
        this.totalRows = 8;

        this.gridPane = new GridPane();
        this.pane = new Pane(gridPane);
        this.node = pane;

        pane.setMaxWidth(Main.SCREEN_WIDTH);
        pane.setMaxHeight(Main.SCREEN_HEIGHT);

        gridPane.setMaxWidth(Main.SCREEN_WIDTH);
        gridPane.setMaxHeight(Main.SCREEN_HEIGHT);
        gridPane.setMinWidth(Main.SCREEN_WIDTH);
        gridPane.setMinHeight(Main.SCREEN_HEIGHT);
        gridPane.setGridLinesVisible(true);

        ColumnConstraints colConst = new ColumnConstraints();
        colConst.setPercentWidth(100.0f - leftPercent);
        gridPane.getColumnConstraints().add(colConst);

        ColumnConstraints colConst2 = new ColumnConstraints();
        colConst2.setPercentWidth(leftPercent);
        gridPane.getColumnConstraints().add(colConst2);

        for (int i = 0; i < totalRows; i++) {
            RowConstraints rowConst = new RowConstraints();
            rowConst.setPercentHeight(100.0 / totalRows);
            gridPane.getRowConstraints().add(rowConst);
        }

        this.overlayElementFactory = overlayFactory.withNewRoot(pane);
        this.gridElementFactory = overlayFactory.withNewRoot(gridPane);

        this.menuArrow = overlayElementFactory.createMenuArrow();
        menuArrow.show();

        this.lastUpdate = System.currentTimeMillis();
        this.lastMovementUpdate = System.currentTimeMillis();
    }

    protected void addButton(TextBox box) {
        gridPane.add(box.node, 1, startIndex + totalButtons);
        GridPane.setHalignment(box.node, HPos.LEFT);
        GridPane.setValignment(box.node, VPos.CENTER);

        buttons.add(box);

        totalButtons += 1;
    }

    public void update(double currentTime) {
        lastUpdate = currentTime;

        if (currentTime - lastMovementUpdate > 100.0f) {
            updateInput(currentTime);
        }

        int row = currentButton + startIndex + 1;

        menuArrow.setPosition(getXLeftAt(row), getYAt(row));
    }

    protected void updateInput(double currentTime) {
        boolean triggered = input.isFirePressed();
        boolean down = input.isDownPressed();
        boolean up = input.isUpPressed();

        if (triggered || up || down) {
            lastMovementUpdate = currentTime;
        }

        if (triggered) {
            buttons.get(currentButton).clickListener.onClick();
        }

        if (up && down) {
            // do nothing
        } else if (up) {
            currentButton = mod(currentButton - 1, totalButtons);
        } else if (down) {
            currentButton = mod(currentButton + 1, totalButtons);
        }
    }

    protected int mod(int num, int n) {
        if (num < 0) {
            int remainder = (-num) % n;
            return n - remainder - 1;
        } else {
            return num % n;
        }
    }

    protected double getYAt(int row) {
        double height = Main.SCREEN_HEIGHT / (double)totalRows;

        return (height * row) - (height / 2);
    }

    protected double getXLeftAt(int row) {
        double width = Main.SCREEN_WIDTH * ((100 - leftPercent) / 100.0f);

        return width - menuArrow.getHalfWidth();
    }
}
