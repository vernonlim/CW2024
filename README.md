# CW2024
[GitHub Repository](https://github.com/vernonlim/CW2024)

This is the coursework project for Nottingham University Malaysia Campus's COMP2042 module (Developing Maintainable Software) in 2024.

The task is to fork an existing git repository containing source code for a game, then to get it working, refactor it, document it and improve it.

# Compilation Instructions
## Prerequisites
Please ensure you have the following prerequisites:
- A Java 21 JDK with JavaFX included, such as [Azul Zulu](https://www.azul.com/downloads/?version=java-21-lts&package=jdk-fx#zulu).

The following tools may also be of use:
- Apache Maven or an IDE that comes bundled with it such as IntelliJ IDEA.
- git

## Source Code
If you don't already have the source code (from being the grader), download the source code by cloning the repository:
```shell
git clone https://github.com/vernonlim/CW2024.git
cd CW2024
```

If you don't have git, click on the code button on the GitHub page, then click on "Download ZIP". Then, extract the .zip file with a utility such as 7zip.

## Building
### From a CLI
While in the source code folder:
```shell
# Unix/Windows with Maven installed
mvn clean compile exec:java

# Unix without Maven
./mvnw clean compile exec:java

# Windows without Maven
mvnw.cmd clean compile exec:java
```

### From IntelliJ IDEA CE
If you have the local project folder available (being say, the grader for this assignment), a run configuration should already be available in IDEA - otherwise:

Open the project folder in IDEA, then navigate to Run -> Edit Configurations in the menu. Then, click the + button at the top left, select Maven and add
```shell
clean compile exec:java
```
to the run field.

Click 'Apply' and then 'Okay'. Now, clicking the play button (green triangle) at the top right of the window should run the project.

If things don't seem right, try [reimporting the maven dependencies](https://stackoverflow.com/questions/9980869/force-intellij-idea-to-reread-all-maven-dependencies).

## Testing
### From a CLI
```shell
mvn test
```

### From IntelliJ IDEA CE
Right click on the src/test/java folder and click "Run 'Tests in 'java''".

# Features
## Implemented and Working Properly
### Gameplay
#### Menus
CW2024Game has a total of 3 Menu overlays navigatable with a keyboard:
1. Main Menu - allows the user to start the game
2. Character Select - allows the user to select a character
3. Pause - allows the user to head back to the main menu or retry the level

The menus can be navigated with the up and down arrows, and a button selected with the 'c' key.

After a victory or loss, the pause menu is displayed, allowing the user to continue playing.

#### Pausing
Of course, if there's a pause menu, there has to be pausing. The game can now be paused by pressing the 'Escape' key on the keyboard (by default). This pauses the game's virtual timer along with the Timeline, meaning actor simulation and animation is paused, but uses real time for navigating the pause screen to avoid it also freezing.

The pause menu contains a button to head back to the main menu and a button to retry the current level. The retry button restarts the current level with the same player character already in use (the feature 2 headings below here).

#### Modular Keybinds
Key Bindings are read from a keybinds.json file at runtime from the resources folder instead of being in source code. This means that they are easily modifiable.

Try adding a new entry to the list like say, "V": "CONFIRM" if you want to try it out.

By default, a few more keybinds are added - WASD for movement, Z for firing and Shift for Focus Fire.

#### Multiple Player Characters
There are now 2 different planes that the player can choose from on the Character Select screen before entering a level. The first being a reworked version of the original plane, and the second being a variant coloured green.

Both planes have different speeds, health and projectiles fired, along with different effects from triggering 'Focus Fire'.

#### Focus Fire
When holding Shift (by default), the player character enters "Focus Fire". The effects for the two planes are as follows:

Regular Plane - Slows the plane down significantly and doubles the fire rate.
Green Plane - Slows the plane down marginally and switches to a spread shot.

The spread shot has the plane shoot 3 bullets each cycle in 3 directions.
    
#### Two New Levels
The game now has two new levels after Level Two - Level Three and Level Four. Each level has its own new background image and enemy type, along with a new win condition.

Both levels are won by surviving for a set amount of time - currently 20 seconds. A time display counting down is placed at the top right to indicate this.

Level Three's new enemy type is the "Blue Enemy", having the original plane sprite but coloured blue. During the level, they spawn in repeatedly at two positions from the right, one low and one high, and then shoot slow-moving projectiles towards the middle of the screen. To survive this, the player has to quickly move between the locations to kill them before too many projectiles are fired, or just use the green plane's focus fire shot.

Level Four's new enemy type is the "Red Enemy", having the original plane sprite but coloured red. During the level, they spawn in bursts of 5 from the right at a random Y location every 2 seconds or so. They don't shoot any projectiles, but they travel at a very high speed, meaning that killing them in time before they penetrate the player's defenses is quite a difficult challenge.

#### Audio
The game now has audio feedback for the majority of actions. Menu buttons have a select sound, firing projectiles makes a noise, enemies have an explosion sound on death, and so on. The sound effects vary between the different player characters and enemy types as well, as within the codebase customizing them is as easy as constructing them with different data.

#### Framerate-Independent Logic
Game state updates have been reworked to be based off of the time passed inbetween frames, along with the current time (of a virtual timer, in most cases). This means that the target framerate can be arbitrarily set without affecting how fast the game runs. The default value is 60fps, but try changing it in the source code to something like 20 or 120 if you want to verify that it works properly.

As a side effect, this allows for high refresh rate support, as any FPS, even dynamic (from lag or such), now works fine.

#### Letterboxing
The game is now resizeable, with its content being scaled down and aspect ratio being maintained via black bars at the side of the window. This means that while internally the coordinates the game came with are being used (1300, 750), the actual game window can be any size including fullscreen (however, with issues here - see the not working properly section). 

Do take note that the main logic for the dynamic scaling is from another author - the code has been attributed properly in the documentation. However, the setup with the Pane, StackPane and Group is something I had to come up with myself to get the code from there to function.

#### Better Hitboxes
I am not sure if this is considered an implemented feature or a fixed bug, but regardless the hitboxes for the player and enemy are now significantly more accurate, courtesy of cropping the blank space out of the images.

However, there is now also support for overriding the bounds used for collision. In-game, this is currently only used for giving the player a slightly smaller horizontal hitbox - the player hitbox is a square instead of a rectangle - but it's worth noting as a feature nonetheless.

#### Improved Movement
This is mainly notable because of two things - null cancelling and velocity normalization.

In the original code, 

### Code
#### Modular Actor behaviour using the Strategy Pattern
Actor behaviours (state changes with time) are now modelled wih the Strategy Pattern. Each main action in the game, that being Movement, Firing and Shielding (for the boss) has its own interface, which Actors then get passed an instance of (to namedrop that specific concept, Dependency Injection). 

What this means is that adding new behaviours to these Actors does not require modifying the Actors themselves. To create and implement a burst firing pattern for an enemy that shoots bullets in bursts, all that would be needed would be to make a class implementing `Firing`, with internal state tracking the time arguments passed into `updateStrategyState`, that produces such a behaviour. Then, this could be passed to any existing `FighterPlane` as its firing strategy without modification (in its factory for example) and it would work.

Player character actions are modelled as strategies that return values based on the state of a passed `InputHandler`. This means that `UserPlane` and derived classes have no input handling code whatsoever, and nor does `Level(Parent)`.

Movement, Firing and Shielding being separate also means that they are individually reusable. If there were to be a new boss with different movement and firing behaviour, but with the same shielding algorithm, the existing `BossShielding` class would be reusable.

#### Shared Assets and Generic Loading
Assets are now handled throughout the program through an instance of `AssetLoader`, defining methods that return an `Image` and an `AudioClip`, allowing for any approach that fetches them to be used. The code comes with two existing ones - `CachedAssetLoader` and `UpFrontAssetLoader`.

`UpFrontAssetLoader` is the preferred one. It loads every image defined in its parent `CW2024AssetLoader` upon object construction, currently at program startup, and then stores them in a map. It then simply returns the loaded `Image`s from the map without additional loading upon request. If a resource that isn't in the defined list is requested, it returns a purple placeholder image.

`CachedAssetLoader` loads images on demand. However, it stores requested images in a map once loaded, meaning if they are requested again it returns the same copy. If an image doesn't exist, it at the moment just returns `null`.

These two implement resource sharing - an image is only loaded once and is then reused for every `ImageView` made from it, similarly with `AudioClip`s. They also show how behaviour can be different between different concrete implementations, as intended.

#### Decoupling between Elements and their specific configuration (Factories)
An `Element` is the term I used in the codebase to refer to anything that holds a JavaFX Node, usually an `ImageView`. This includes things like enemy planes, but also things like the hearts within `HeartDisplay`, and `HeartDisplay` itself. All elements now have their exact characteristics (image, size, position) injected into them as opposed to being hardcoded.

While this may make constructing a certain `Element` significantly more troublesome, it significantly increases flexibility. The same `EnemyPlane` class can be reused for 3 different kinds of enemies with different looks and behaviours without needing to subclass it whatsoever, many classes from the original project have been removed entirely like `Heart`, as they are now just generic `ImageElement` instances, and so on.

Of course, this customization has to still exist somewhere - it is now centralized in factories. There is a corresponding factory method or enum code for each element in the game, which when used with an implementation of one of the factory interfaces (`ActorFactoryImpl` as an example) generates the right configuration of of the element. This means that game code that needs to create instances of the elements doesn't need to deal with the specifics and construction process itself - the factory handles it for them, keeping it concise.

With all that said, an example process for making something like a new enemy would now be:
1) Add a new code to the `EnemyCode` enum
2) Implement a case for it in `ActorFactoryImpl` with your new image, statistics and strategies

No inheritance needed! (of course following composition over inheritance)

These factories technically satisfy the requirement for an Abstract Factory, as they use interfaces to define them. Factory methods are obviously also used. This fact could be used to perhaps create a separate 'skin' of the game through only making a single new class - as you could make an alternate factory with entirely different enemies and elements for use instead. Obviously though, the example is rather contrived and the pattern does not have that much practical use here.

#### Streamlined object creation with Config classes
While this originally was mostly a non-ideal solution for dealing with the explosion of constructor parameters, it turned out to have some nice benefits in places, especially when combined with my low-subclass approach.

Firstly, classes under the `config` folder in the source code are all classes dedicated solely to collecting what would usually be constructor parameters, before being passed to a classes' constructor, which then gets the data back out. They are indeed - data classes. However, by having setters and getters, they can encapsulate the data within. As an example of a practical use of this:
```java
// In ImageElementConfig.java
public void setImage(Image image) {
    this.imageView = new ImageView(image);
    this.imageView.setPreserveRatio(true);
}
```
Now passing an `Image` to the `ImageElementConfig` has it automatically create an `ImageView` wrapping it, without needing the logic to be in a separate method floating around or within the element's constructor (if the `Image` were to be passed directly).

Furthermore, this allows for passing around and working with sets of parameters easily. In `ScreenFactoryImpl`, the user plane code only needs to be set once, and it applies to all the branches of the switch statement.
```java
// ScreenFactoryImpl.java
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
// --snip--
```

If these were passed as parameters to the constructors directly, the same variable would have to be repeated a myriad number of times. Here, it only needs to be said once.

#### Decoupling of Input Handling from the Level(Parent) class
Input handling is now handled in a separate class from the classes that need it, and is injected as a dependency. Any class that needs input information can be passed an instance of `InputManager` and use its methods to check the current state of user input.

`InputManager` itself contains a `KeybindStore`, which works as a data object tracking the state of keybinds and their associated actions. `InputManager` then attaches event handlers to the `Scene`'s keypress events that modify the state of the `KeybindStore`.

In `KeybindStore`, actions are mapped to `BooleanProperty`s which have their state set to true/false on key down and key up events respectively. This converts the unpredictable event handlers to a binary variable - "is any keybind mapped to 'FIRE' activated?".

#### Decoupling of Collision Handling from the Level(Parent) class
Pretty simple improvement - collision handling is now performed with dependency injection, with a `CollisionManager` interface. Concrete implementations can have their own custom behaviour, the only current implementation `DamageCollisionManager` dealing 1 damage to both actors, unless the first is a projectile, in which case the second actor takes the damage of the projectile. 

This decouples the behaviour from the `Level` class, allowing it to be used more generically, and while there isn't much use for having multiple different collision behaviours, it *is* possible. Perhaps if there was a minigame to be made where collisions would trigger knockback and not damage, the `Level` code could be reused with a different `CollisionManager`.

#### Functioning Overlays
Overlays in this project are abstracted as `ContainerElement`s that contain all of their sub-elements within a container. By rendering this container as a child of the top `StackPane` of the node hierarchy in the program, it then gets rendered above all of the gameplay elements, which are rendered in a `Pane` at the bottom of the `StackPane`. Any additional overlays added after this then get rendered on top of the previous one.

With this system, components like the health display (and newly added, the countdown timer) are properly always above gameplay. In the original project, the hearts in the `HeartDisplay` would render below the player character for example, while the projectiles they shot would be addest soonest and thus be on top. Overlays can also be stacked on top of each other easily, for example having the `Pause` overlay render over everything else.

The Menus are also abstracted as Overlays, being one that contains a `GridPane` as its container and having a method for adding `TextBox`es to the grid.

#### A Vector class for simplying

## Implemented but Not Working Properly
## Not Implemented

# Classes
## New Java Classes
## Modified Java Classes

# Unexpected Problems
## NixOS Troubles
