package dev.vernonlim.cw2024game.factories;

import dev.vernonlim.cw2024game.screens.ScreenChangeHandler;
import javafx.scene.media.AudioClip;

public class ButtonFactory {
    protected ScreenChangeHandler screenChangeHandler;
    protected AudioClip sound;

    public ButtonFactory(ScreenChangeHandler screenChangeHandler, AudioClip sound) {
        this.screenChangeHandler = screenChangeHandler;
        this.sound = sound;
    }


}
