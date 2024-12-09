package dev.vernonlim.cw2024game.elements;

import dev.vernonlim.cw2024game.Main;
import dev.vernonlim.cw2024game.Vector;
import dev.vernonlim.cw2024game.configs.ImageElementConfig;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import static org.junit.jupiter.api.Assertions.*;

public class ElementTest extends ApplicationTest {
    final double EPSILON = 0.001;
    Pane root;
    ImageElement element;

    @Override
    public void start(Stage stage) {
        Pane root = new Pane();
        this.root = root;
        ImageElementConfig config = new ImageElementConfig(root);

        Image image = new Image(getClass().getResource("/images/background1.jpg").toString());
        config.setImage(image);

        config.setFitHeight(50.0);
        config.setFitWidth(60.0);

        element = new ImageElement(config);
    }

    @Test
    @DisplayName("Should add and remove the node from the root")
    void showAndHide() {
        element.show();

        assertTrue(root.getChildren().contains(element.node));

        element.hide();

        assertFalse(root.getChildren().contains(element.node));
    }

    @Test
    @DisplayName("Should set and get the position based on the center X/Y, not top left")
    void setAndGetPosition() {
        element.setPosition(new Vector(3.0, 5.0));

        element.show();

        assertEquals(3.0, root.getChildren().getFirst().getBoundsInParent().getCenterX(), EPSILON);
        assertEquals(5.0, root.getChildren().getFirst().getBoundsInParent().getCenterY(), EPSILON);

        Vector pos = element.getPosition();
        assertEquals(3.0, pos.x, EPSILON);
        assertEquals(5.0, pos.y, EPSILON);
    }

    @Test
    @DisplayName("Should return the width of the image within its parent")
    void getWidthHeight() {
        element.view.setPreserveRatio(false);

        // smaller than the image
        element.view.setFitWidth(4.0);
        element.view.setFitHeight(2.0);

        assertEquals(4.0, element.getWidth(), EPSILON);
        assertEquals(2.0, element.getHeight(), EPSILON);
        assertEquals(2.0, element.getHalfWidth(), EPSILON);
        assertEquals(1.0, element.getHalfHeight(), EPSILON);
    }

    @Test
    @DisplayName("Should set the new position relative to its current")
    void move() {
        element.setPosition(new Vector(5.0, 10.0));
        element.move(1.0, 2.0);
        assertEquals(6.0, element.getPosition().x, EPSILON);
        assertEquals(12.0, element.getPosition().y, EPSILON);

        element.moveHorizontally(-1.0);
        element.moveVertically(-1.0);

        assertEquals(5.0, element.getPosition().x, EPSILON);
        assertEquals(11.0, element.getPosition().y, EPSILON);
    }

    @Test
    @DisplayName("Should set the position of the element from edges of the screen based on its width")
    void setXFromLeftRight() {
        element.setXFromLeft(5.0);

        assertEquals(5.0, element.getX() - element.getHalfWidth(), EPSILON);

        element.setXFromRight(5.0);

        assertEquals(Main.SCREEN_WIDTH - 5.0, element.getX() + element.getHalfWidth(), EPSILON);
    }

    @Test
    @DisplayName("Should ensure the element is in bounds including its width/height")
    void ensureInBounds() {
        element.setPosition(-20.0, -30.0);
        element.ensureInBounds();

        assertEquals(0.0, element.getPosition().x - element.getHalfWidth(), EPSILON);
        assertEquals(0.0, element.getPosition().y - element.getHalfHeight(), EPSILON);
    }
}