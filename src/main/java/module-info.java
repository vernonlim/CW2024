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
    exports dev.vernonlim.cw2024game.screens;
    exports dev.vernonlim.cw2024game;
    exports dev.vernonlim.cw2024game.overlays;
    opens dev.vernonlim.cw2024game.overlays to javafx.fxml;
    opens dev.vernonlim.cw2024game.elements to javafx.fxml;
    exports dev.vernonlim.cw2024game.managers;
    opens dev.vernonlim.cw2024game.managers to javafx.fxml;
    exports dev.vernonlim.cw2024game.elements;
    opens dev.vernonlim.cw2024game.elements.strategies to javafx.fxml;
    exports dev.vernonlim.cw2024game.elements.actors;
}
