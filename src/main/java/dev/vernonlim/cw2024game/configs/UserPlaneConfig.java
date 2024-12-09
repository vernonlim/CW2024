package dev.vernonlim.cw2024game.configs;

import dev.vernonlim.cw2024game.elements.ProjectileListener;
import dev.vernonlim.cw2024game.factories.interfaces.ProjectileFactory;
import dev.vernonlim.cw2024game.managers.InputManager;
import javafx.scene.layout.Pane;

/**
 * A data class used for streamlining UserPlane creation
 */
public class UserPlaneConfig extends FighterPlaneConfig {
    /**
     * The InputManager handling user input.
     */
    private InputManager input;

    /**
     * Constructs a UserPlaneConfig with the given params.
     *
     * @param root               the root Pane on which the UserPlane is based
     * @param projectileFactory  the ProjectileFactory that produces the projectiles
     * @param projectileListener the ProjectileListener that handles the firing of projectiles
     * @param input              the InputManager handling user input
     */
    public UserPlaneConfig(Pane root, ProjectileFactory projectileFactory, ProjectileListener projectileListener, InputManager input) {
        super(root, projectileFactory, projectileListener);
        this.input = input;
    }

    /**
     * Gets the InputManager.
     *
     * @return the InputManager
     */
    public InputManager getInput() {
        return input;
    }

    /**
     * Sets the InputManager.
     *
     * @param input the InputManager to set
     */
    public void setInput(InputManager input) {
        this.input = input;
    }
}
