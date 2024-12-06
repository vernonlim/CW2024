package dev.vernonlim.cw2024game.overlays;

import dev.vernonlim.cw2024game.Controller;
import dev.vernonlim.cw2024game.Main;
import dev.vernonlim.cw2024game.elements.ContainerElement;
import dev.vernonlim.cw2024game.elements.Element;
import dev.vernonlim.cw2024game.elements.TextBox;
import dev.vernonlim.cw2024game.elements.configs.OverlayConfig;
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

public abstract class MenuOverlay extends ContainerElement {
    protected final GridPane gridPane;
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

    protected double rightPercent;

    public MenuOverlay(OverlayConfig config) {
        super(config);

        this.controller = config.getController();
        this.input = config.getInput();
        this.screenChangeHandler = config.getScreenChangeHandler();

        this.buttons = new ArrayList<>();

        this.startIndex = 3;
        this.currentButton = 0;
        this.rightPercent = 40.0f;
        this.totalRows = 9;

        this.gridPane = new GridPane();
        this.container = new Pane(gridPane);
        this.node = container;

        this.container.setMaxWidth(Main.SCREEN_WIDTH);
        this.container.setMaxHeight(Main.SCREEN_HEIGHT);

        this.gridPane.setMaxWidth(Main.SCREEN_WIDTH);
        this.gridPane.setMaxHeight(Main.SCREEN_HEIGHT);
        this.gridPane.setMinWidth(Main.SCREEN_WIDTH);
        this.gridPane.setMinHeight(Main.SCREEN_HEIGHT);
//        gridPane.setGridLinesVisible(true);

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

        this.overlayElementFactory = config.getOverlayFactory().withNewRoot(container);
        this.gridElementFactory = config.getOverlayFactory().withNewRoot(gridPane);

        this.menuArrow = this.overlayElementFactory.createMenuArrow();
        this.menuArrow.show();

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
        boolean triggered = input.isConfirmPressed();
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
            currentButton = getPreviousButton(currentButton);
        } else if (down) {
            currentButton = getNextButton(currentButton);
        }
    }

    protected int getNextButton(int currentButton) {
        if (currentButton + 1 >= totalButtons) {
            return 0;
        }

        return currentButton + 1;
    }

    protected int getPreviousButton(int currentButton) {
        if (currentButton - 1 < 0) {
            return totalButtons - 1;
        }

        return currentButton - 1;
    }

    protected double getYAt(int row) {
        double height = Main.SCREEN_HEIGHT / (double)totalRows;

        return (height * row) - (height / 2);
    }

    protected double getXLeftAt(int row) {
        double width = Main.SCREEN_WIDTH * ((100 - rightPercent) / 100.0f);

        return width - menuArrow.getHalfWidth();
    }
}
