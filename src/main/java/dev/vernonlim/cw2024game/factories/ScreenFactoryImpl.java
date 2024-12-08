package dev.vernonlim.cw2024game.factories;

import dev.vernonlim.cw2024game.Controller;
import dev.vernonlim.cw2024game.assets.AssetLoader;
import dev.vernonlim.cw2024game.elements.actors.UserPlaneCode;
import dev.vernonlim.cw2024game.configs.ScreenConfig;
import dev.vernonlim.cw2024game.factories.interfaces.ScreenFactory;
import dev.vernonlim.cw2024game.managers.KeybindStore;
import dev.vernonlim.cw2024game.screens.*;

public class ScreenFactoryImpl implements ScreenFactory {
    private final AssetLoader loader;
    private final KeybindStore keybinds;

    public ScreenFactoryImpl(AssetLoader loader, KeybindStore keybinds) {
        this.loader = loader;
        this.keybinds = keybinds;
    }

    public Screen createScreen(ScreenCode screenCode, UserPlaneCode userPlaneCode) {
        Screen screen = null;

        ScreenConfig config = new ScreenConfig(keybinds, loader);
        config.setUserPlaneCode(userPlaneCode);

        switch (screenCode) {
            case ScreenCode.MAIN_MENU: {
                config.setBackgroundImageName("mainmenu");
                config.setCurrentScreenCode(ScreenCode.MAIN_MENU);
                screen = new MainMenu(config);
                break;
            }
            case ScreenCode.CHARACTER_SELECT: {
                config.setBackgroundImageName("mainmenu");
                config.setCurrentScreenCode(ScreenCode.CHARACTER_SELECT);
                screen = new CharacterSelect(config);
                break;
            }
            case ScreenCode.LEVEL_SELECT: {
                config.setBackgroundImageName("mainmenu");
                config.setCurrentScreenCode(ScreenCode.LEVEL_SELECT);
                screen = new LevelSelect(config);
                break;
            }
            case ScreenCode.LEVEL_ONE: {
                config.setBackgroundImageName("background1");
                config.setCurrentScreenCode(ScreenCode.LEVEL_ONE);
                screen = new LevelOne(config);
                break;
            }
            case ScreenCode.LEVEL_TWO: {
                config.setBackgroundImageName("background2");
                config.setCurrentScreenCode(ScreenCode.LEVEL_TWO);
                screen = new LevelTwo(config);
                break;
            }
            case ScreenCode.LEVEL_THREE: {
                config.setBackgroundImageName("background3");
                config.setCurrentScreenCode(ScreenCode.LEVEL_THREE);
                config.setCountdownTime(20);
                screen = new LevelThree(config);
                break;
            }
            case ScreenCode.LEVEL_FOUR: {
                config.setBackgroundImageName("background4");
                config.setCurrentScreenCode(ScreenCode.LEVEL_FOUR);
                config.setCountdownTime(20);
                screen = new LevelFour(config);
                break;
            }
        }
        if (screen == null) {
            Controller.triggerAlertAndExit("Missing Level!");
        }

        return screen;
    }
}
