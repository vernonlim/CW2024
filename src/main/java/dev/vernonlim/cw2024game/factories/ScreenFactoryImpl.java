package dev.vernonlim.cw2024game.factories;

import dev.vernonlim.cw2024game.Controller;
import dev.vernonlim.cw2024game.ScreenCode;
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

    public Screen createScreen(ScreenCode screenCode, UserPlaneCode code) {
        Screen screen = null;

        switch (screenCode) {
            case ScreenCode.MAIN_MENU: {
                String backgroundImageName = "mainmenu";
                screen = new MainMenu(controller, loader, keybinds, backgroundImageName, ScreenCode.MAIN_MENU);
                break;
            }
            case ScreenCode.CHARACTER_SELECT: {
                String backgroundImageName = "mainmenu";
                screen = new CharacterSelect(controller, loader, keybinds, backgroundImageName, ScreenCode.CHARACTER_SELECT);
                break;
            }
            case ScreenCode.LEVEL_ONE: {
                String backgroundImageName = "background1";
                screen = new LevelOne(stage, controller, loader, keybinds, backgroundImageName, ScreenCode.LEVEL_ONE, code);
                break;
            }
            case ScreenCode.LEVEL_TWO: {
                String backgroundImageName = "background2";
                screen = new LevelTwo(stage, controller, loader, keybinds, backgroundImageName, ScreenCode.LEVEL_TWO, code);
                break;
            }
            case ScreenCode.LEVEL_THREE: {
                String backgroundImageName = "background3";
                screen = new LevelThree(stage, controller, loader, keybinds, backgroundImageName, ScreenCode.LEVEL_THREE, code);
                break;
            }
        }
        if (screen == null) {
            Controller.triggerAlertAndExit("Missing Level!");
        }

        return screen;
    }
}
