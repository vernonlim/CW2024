package dev.vernonlim.cw2024game.configs;

import dev.vernonlim.cw2024game.elements.ProjectileListener;
import dev.vernonlim.cw2024game.factories.interfaces.ProjectileFactory;
import dev.vernonlim.cw2024game.managers.InputManager;
import javafx.scene.layout.Pane;

public class UserPlaneConfig extends FighterPlaneConfig {
    private InputManager input;

    public UserPlaneConfig(Pane root, ProjectileFactory projectileFactory, ProjectileListener projectileListener, InputManager input) {
        super(root, projectileFactory, projectileListener);

        this.input = input;
    }

    public InputManager getInput() {
        return input;
    }

    public void setInput(InputManager input) {
        this.input = input;
    }
}
