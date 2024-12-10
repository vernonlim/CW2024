package dev.vernonlim.cw2024game.configs;

import dev.vernonlim.cw2024game.Vector;
import dev.vernonlim.cw2024game.elements.ImageElement;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import static org.junit.jupiter.api.Assertions.*;

public class ImageElementConfigTest extends ApplicationTest {
    final double EPSILON = 0.001;
    ImageElementConfig config;

    @Override
    public void start(Stage stage) {
        Pane root = new Pane();
        config = new ImageElementConfig(root);
    }

    @Test
    @DisplayName("Getting/setting position")
    void setAndGetPosition() {
        config.setPosition(10.0, 10.0);

        Vector vec = config.getPosition();
        assertEquals(vec.x, 10.0);
        assertEquals(vec.y, 10.0);
    }

    @Test
    @DisplayName("Should be true only if setPosition was run and false otherwise")
    void shouldSetPosition() {
        assertFalse(config.shouldSetPosition());

        config.setPosition(10.0, 10.0);

        assertTrue(config.shouldSetPosition());
    }

    @Test
    @DisplayName("Should be able to pass an Image and get back an Image View")
    void setAndGetImage() {
        Image image = new Image(getClass().getResource("/images/background1.jpg").toString());
        config.setImage(image);

        ImageView view = config.getImageView();
        Image image2 = view.imageProperty().get();

        assertEquals(image, image2);
    }

    @Test
    @DisplayName("Set and get fitHeight/fitWidth")
    void setAndGetFitHeightWidth() {
        Image image = new Image(getClass().getResource("/images/background1.jpg").toString());
        config.setImage(image);

        ImageView view = config.getImageView();

        config.setFitHeight(50.0);

        assertEquals(50.0, view.getFitHeight(), EPSILON);

        config.setFitWidth(50.0);

        assertEquals(50.0, view.getFitWidth(), EPSILON);
    }
}