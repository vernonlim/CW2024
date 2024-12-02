package dev.vernonlim.cw2024game.factories;

import dev.vernonlim.cw2024game.Controller;
import dev.vernonlim.cw2024game.ScreenList;
import dev.vernonlim.cw2024game.assets.AssetLoader;
import dev.vernonlim.cw2024game.factories.interfaces.ScreenFactory;
import dev.vernonlim.cw2024game.managers.KeybindStore;
import dev.vernonlim.cw2024game.screens.LevelOne;
import dev.vernonlim.cw2024game.screens.LevelTwo;
import dev.vernonlim.cw2024game.screens.Screen;
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

    public Screen createScreen(ScreenList screen) {
        switch (screen) {
            case ScreenList.ONE: {
                String backgroundImageName = "background1";
                int playerInitialHealth = 5;
                return new LevelOne(stage, controller, loader, keybinds, backgroundImageName, playerInitialHealth);
            }
            case ScreenList.TWO: {
                String backgroundImageName = "background2";
                int playerInitialHealth = 5;
                return new LevelTwo(stage, controller, loader, keybinds, backgroundImageName, playerInitialHealth);
            }
            default: {
                return createScreen(ScreenList.ONE);
            }
        }
    }
}
