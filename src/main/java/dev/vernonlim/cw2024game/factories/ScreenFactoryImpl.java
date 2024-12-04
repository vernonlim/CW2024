package dev.vernonlim.cw2024game.factories;

import dev.vernonlim.cw2024game.Controller;
import dev.vernonlim.cw2024game.screens.ScreenCode;
import dev.vernonlim.cw2024game.assets.AssetLoader;
import dev.vernonlim.cw2024game.elements.actors.UserPlaneCode;
import dev.vernonlim.cw2024game.factories.interfaces.ScreenFactory;
import dev.vernonlim.cw2024game.managers.KeybindStore;
import dev.vernonlim.cw2024game.screens.*;
import javafx.stage.Stage;

public class ScreenFactoryImpl implements ScreenFactory {
    protected final Stage stage;
    protected final Controller controller;
    protected final AssetLoader loader;
    protected final KeybindStore keybinds;

    public ScreenFactoryImpl(Stage stage, Controller controller, AssetLoader loader, KeybindStore keybinds) {
        this.stage = stage;
        this.controller = controller;
        this.loader = loader;
        this.keybinds = keybinds;
    }

    public Screen createScreen(ScreenCode screenCode, UserPlaneCode userPlaneCode) {
        Screen screen = null;

        switch (screenCode) {
            case ScreenCode.MAIN_MENU: {
                String backgroundImageName = "mainmenu";
                screen = new MainMenu(controller, loader, keybinds, backgroundImageName, ScreenCode.MAIN_MENU, userPlaneCode);
                break;
            }
            case ScreenCode.CHARACTER_SELECT: {
                String backgroundImageName = "mainmenu";
                screen = new CharacterSelect(controller, loader, keybinds, backgroundImageName, ScreenCode.CHARACTER_SELECT, userPlaneCode);
                break;
            }
            case ScreenCode.LEVEL_SELECT: {
                String backgroundImageName = "mainmenu";
                screen = new LevelSelect(controller, loader, keybinds, backgroundImageName, ScreenCode.LEVEL_SELECT, userPlaneCode);
                break;
            }
            case ScreenCode.LEVEL_ONE: {
                String backgroundImageName = "background1";
                screen = new LevelOne(stage, controller, loader, keybinds, backgroundImageName, ScreenCode.LEVEL_ONE, userPlaneCode);
                break;
            }
            case ScreenCode.LEVEL_TWO: {
                String backgroundImageName = "background2";
                screen = new LevelTwo(stage, controller, loader, keybinds, backgroundImageName, ScreenCode.LEVEL_TWO, userPlaneCode);
                break;
            }
            case ScreenCode.LEVEL_THREE: {
                String backgroundImageName = "background3";
                screen = new LevelThree(stage, controller, loader, keybinds, backgroundImageName, ScreenCode.LEVEL_THREE, userPlaneCode);
                break;
            }
            case ScreenCode.LEVEL_FOUR: {
                String backgroundImageName = "background4";
                screen = new LevelFour(stage, controller, loader, keybinds, backgroundImageName, ScreenCode.LEVEL_THREE, userPlaneCode);
                break;
            }
        }
        if (screen == null) {
            Controller.triggerAlertAndExit("Missing Level!");
        }

        return screen;
    }
}
