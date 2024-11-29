module dev.vernonlim.cw2024game {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    opens dev.vernonlim.cw2024game to javafx.fxml;
    opens dev.vernonlim.cw2024game.screens to javafx.fxml;
    opens dev.vernonlim.cw2024game.actors to javafx.fxml;
    exports dev.vernonlim.cw2024game.screens;
    exports dev.vernonlim.cw2024game;
    exports dev.vernonlim.cw2024game.overlays;
    opens dev.vernonlim.cw2024game.overlays to javafx.fxml;
    opens dev.vernonlim.cw2024game.overlays.elements to javafx.fxml;
}
