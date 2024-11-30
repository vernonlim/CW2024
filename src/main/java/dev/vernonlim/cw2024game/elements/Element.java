package dev.vernonlim.cw2024game.elements;

import dev.vernonlim.cw2024game.Main;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

public abstract class Element {
    protected Pane root;
    protected Node node;

    public Element(Pane root) {
        this.root = root;
    }

    public void show() {
        if (!root.getChildren().contains(node)) {
            root.getChildren().add(node);
        }
    }

    public void hide() {
        root.getChildren().remove(node);
    }

    public void setPosition(double xPos, double yPos) {
        setX(xPos);
        setY(yPos);
    }

    public Bounds getBoundsInParent() {
        return node.getBoundsInParent();
    }

    public double getX() {
        return node.getBoundsInParent().getCenterX();
    }

    public void setX(double xPos) {
        node.setLayoutX(xPos - getWidth() / 2);
    }

    public double getY() {
        return node.getBoundsInParent().getCenterY();
    }

    public void setY(double yPos) {
        node.setLayoutY(yPos - getHeight() / 2);
    }

    public double getWidth() {
        return node.getBoundsInParent().getWidth();
    }

    public double getHeight() {
        return node.getBoundsInParent().getHeight();
    }

    public double getHalfWidth() {
        return getWidth() / 2;
    }

    public double getHalfHeight() {
        return getHeight() / 2;
    }

    protected void moveHorizontally(double horizontalMove) {
        node.setLayoutX(node.getLayoutX() + horizontalMove);
    }

    protected void moveVertically(double verticalMove) {
        node.setLayoutY(node.getLayoutY() + verticalMove);
    }

    protected void move(double horizontalMove, double verticalMove) {
        moveHorizontally(horizontalMove);
        moveVertically(verticalMove);
    }

    protected void setXFromLeft(double xOffset) {
        setX(xOffset + getHalfWidth());
    }

    protected void setXFromRight(double xOffset) {
        setX(Main.SCREEN_WIDTH - (getHalfWidth() + xOffset));
    }

    protected void setYFromTop(double yOffset) {
        setY(yOffset + getHalfHeight());
    }

    protected void setYFromBottom(double yOffset) {
        setY(Main.SCREEN_HEIGHT - (yOffset + getHalfHeight()));
    }

    protected void ensureInBounds() {
        double upperBound = getHalfHeight();
        double lowerBound = Main.SCREEN_HEIGHT - getHalfHeight();
        double leftBound = getHalfWidth();
        double rightBound = Main.SCREEN_WIDTH - getHalfWidth();

        double x = getX();
        double y = getY();

        if (x < leftBound) {
            setX(leftBound);
        }
        if (x > rightBound) {
            setX(rightBound);
        }
        if (y < upperBound) {
            setY(upperBound);
        }
        if (y > lowerBound) {
            setY(lowerBound);
        }
    }
}
