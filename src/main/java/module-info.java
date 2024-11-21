module dev.vernonlim.cw2024game {
    requires javafx.controls;
    requires javafx.fxml;


    opens dev.vernonlim.cw2024game to javafx.fxml;
    exports dev.vernonlim.cw2024game.controller;
    opens dev.vernonlim.cw2024game.levels to javafx.fxml;
}
