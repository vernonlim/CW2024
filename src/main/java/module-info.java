/**
 * This module contains the 2D bullet hell shooter game CW2024Game.
 */
module dev.vernonlim.cw2024game {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.media;
    requires java.desktop;
    requires org.json;
    requires java.logging;

    opens dev.vernonlim.cw2024game to javafx.fxml;
    opens dev.vernonlim.cw2024game.screens to javafx.fxml;
    opens dev.vernonlim.cw2024game.elements.actors to javafx.fxml;
    opens dev.vernonlim.cw2024game.overlays to javafx.fxml;
    opens dev.vernonlim.cw2024game.elements to javafx.fxml;
    opens dev.vernonlim.cw2024game.managers to javafx.fxml;
    opens dev.vernonlim.cw2024game.elements.strategies to javafx.fxml;
}
