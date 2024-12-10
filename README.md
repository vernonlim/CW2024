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
2. Character Select - allows the user to select one of two characters
3. Pause - allows the user to head back to the main menu or retry the level

The menus can be navigated with the up and down arrows, and a button selected with the 'c' key.

After a victory or loss, the pause menu is displayed, allowing the user to continue playing (by either replaying the level, or heading to the main menu).

#### Pausing
Of course, if there's a pause menu, there has to be pausing. The game can now be paused by pressing the 'Escape' key on the keyboard (by default). This pauses the game's virtual timer along with the Timeline, meaning actor simulation and animation is paused, but uses real time for navigating the pause screen to avoid it also freezing.

The pause menu contains a button to head back to the main menu and a button to retry the current level. The retry button restarts the current level with the same player character already in use (the feature 2 headings below here).

#### Modular Keybinds
Key Bindings are read from a keybinds.json file at runtime from the resources folder instead of being in source code. This means that they are easily modifiable.

Try adding a new entry to the list like say, "V": "CONFIRM" if you want to try it out.

By default, a few more keybinds are added - WASD for movement, Z for firing and Shift for Focus Fire.

#### Framerate-Independent Logic
Game state updates have been reworked to be based off of the time passed inbetween frames (deltaTime), along with the current time (of a virtual timer, in most cases). This means that the target framerate can be arbitrarily set without affecting how fast the game runs. The default value is 60fps, but try changing it in the source code to something like 20 or 120 if you want to verify that it works properly.

As a side effect, this allows for high refresh rate support, as any FPS, even dynamic (from lag or such), should effectively approximate the same game speed.

All this has been done without affecting the original enemy algorithms of the game, by simulating the original 50ms tick time at the places that need it by storing the time of last update (and calculating the time since).

#### Improved Movement
The player character can now move in 4 directions! That by itself wouldn't be too notable as an achievement though, the notable features added here are null-cancellation and normalization.

Null-cancellation is a problem that only arises when an input system that can handle multiple flags at once, for example both UP and DOWN being held simultaneously, is implemented. In such a case, what should happen when both UP and DOWN are held? The obvious approach is to have them 'null' - they cancel each other out and the player stays still. However, this can be quite annoying for precise movement. The intuitive solution here is to have the player character move in the direction of the most recent keypress. For example:
```
  L-----
R--------
The character moves right first, and then moves left due to it being the most recent keypress.
```

This poses an interesting implementation challenge, because an extra variable tracking the last movement *with a single keypress* has to be stored. If it wasn't and the algorithm was simply implemented as "if L and R pressed, move in the last direction", it would flip direction every frame.

CW2024Game implements this with well, the extra variable tracking the last "full" movement.

```java
// From UserMovement.java
// null cancelling movement
if (down && up) {
    verticalMult = -lastVerticalMult;
} else if (up) {
    lastVerticalMult = -1;
    verticalMult = -1;
} else if (down) {
    lastVerticalMult = 1;
    verticalMult = 1;
} else {
    verticalMult = 0;
}
```

Secondly, there's the issue of vector normalization. If the player moves both up and right by 1 unit when moving diagonally, their speed will be higher than a player moving purely in 1 direction, specifically being sqrt(2) as opposed to 1. This is solved conveniently by a `Vector` class added to the project.
``` java
// UserMovement.java
Vector movement = new Vector(horizontalMult, verticalMult);
movement.normalize();
movement.scaleBy(movementMultiplier);
```

        
#### Multiple Player Characters
There are now 2 different planes that the player can choose from on the Character Select screen before entering a level. The first being a reworked version of the original plane, and the second being a variant coloured green.

Both planes have different speeds, health and projectiles fired, along with different effects from triggering 'Focus Fire'.

Regular Plane:
Health     - 5
Movement   - 24 units/second
Fire       - a mix of the original bullets and yellow balls
Focus Fire - doubles the fire rate, slows movement by 40%

Green Plane:
Health     - 3
Movement   - 30 units/second
Fire       - a mix of the original bullets and yellow balls
Focus Fire - switches to spread shot, slows movement by 10%

#### Focus Fire
When holding Shift (by default), the player character enters "Focus Fire". This is intended to slow the player character down, while giving them a stronger attack.

As mentioned in the section above, it doubles the fire rate for the regular plane. For the green plane, it switches to a "spread shot", where it fires 3 weaker bullets simultaneously in 3 directions (slightly up, straight, slightly down).
    
#### Two New Levels
The game now has two new levels after Level Two - Level Three and Level Four. Each level has its own new background image and enemy type, along with a new win condition.

Both levels are won by surviving for a set amount of time - currently 20 seconds. A time display counting down is placed at the top right to indicate this.

Level Three's new enemy type is the "Blue Enemy", having the original plane sprite but coloured blue. During the level, they spawn in repeatedly at two positions from the right, one low and one high, and then shoot slow-moving projectiles towards the middle of the screen. To survive this, the player has to quickly move between the locations to kill them before too many projectiles are fired, or just use the green plane's focus fire shot.

Level Four's new enemy type is the "Red Enemy", having the original plane sprite but coloured red. During the level, they spawn in bursts of 5 from the right at a random Y location every 2 seconds or so. They don't shoot any projectiles, but they travel at a very high speed, meaning that killing them in time before they penetrate the player's defenses is quite a difficult challenge.

#### Audio
The game now has audio feedback for the majority of actions. Menu buttons have a select sound, firing projectiles makes a noise, enemies have an explosion sound on death, and so on. The sound effects vary between the different player characters and enemy types as well, as within the codebase customizing them is as easy as constructing them with different data.

#### Letterboxing
The game is now resizable, with its content being scaled down and aspect ratio being maintained via black bars at the side of the window. This means that while internally the coordinates the game came with are being used (1300, 750), the actual game window can be any size including fullscreen (however, with issues here - see the not working properly section). 

Do take note that the main logic for the dynamic scaling is from another author - the code has been attributed properly in the documentation. However, the setup with the Pane, StackPane and Group is something I had to come up with myself to get the code from there to function.

#### Better Hitboxes
I am not sure if this is considered an implemented feature or a fixed bug, but regardless the hitboxes for the player and enemy are now significantly more accurate, courtesy of cropping the blank space out of the images.

However, there is now also support for overriding the bounds used for collision. In-game, this is currently only used for giving the player a slightly smaller horizontal hitbox - the player hitbox is a square instead of a rectangle - but it's worth noting as a feature nonetheless.

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

#### A Vector class for simplifying movement
As mentioned in the gameplay features section above, the project now contains a `Vector` class used for movement where convenient, representing a 2D vector with x and y components. The main reason this was added was due to the implementation of `Movement` strategies - methods can only return a single field, and having separate `getNextXMovement` and `getNextYMovement` methods seemed like a poor idea. Having it return a single `Vector` works significantly cleaner. The class also comes with many helper methods like `normalize()` related to movement calculations, making it convenient for other purposes as well.

In another language, making a `Vector` data structure would have been the first thing I would do, but Java doesn't have custom value types. As such, every `Vector` created is created as an instance of a class allocated on the heap, which is terrible for performance. They are also passed by reference instead of being copied when used in methods and what not (for good reason, a copy would mean allocating a new instance). While this is a pretty annoying tradeoff, I decided on making the decision to use the class anyway, as I believe the code quality benefits are big enough to justify the potential performance hit.

#### A Singleton instance of Controller and Stage
During the course of development, I noticed that over the lifecycle of the application, there was only ever one instance of Controller and one instance of Stage. As such, I turned Controller into a Singleton, storing a single instance of itself, and also having a static field for the Stage. This reduces the number of parameters needed to be passed to methods, as they can be replaced with direct references.

#### Unit tests for core functionality
While I didn't manage to implement more extensive integration tests of the game (mostly due to time limitations), There are tests present for some core functionality that the entire project relies on - namely tests for `Vector`, `Element` and `ImageElement`. These ensure that positions and locations are calculated correctly, that showing/hiding an element on a root node works as expected, and that methods for keeping sane values like the out of bounds checks function.

## Implemented but Not Working Properly
### Gameplay
#### Letterboxing in Fullscreen
While the letterboxing mentioned above works in windowed mode and also while *entering* fullscreen, when a screen transition occurs while in fullscreen, the game's letterboxing breaks and the game renders in the original resolution at the bottom left of the screen.

I have not been able to figure out the cause of this, but it almost certainly to do with that letterboxing code I referenced earlier. There *is* a more comprehensive solution out there that I could have gotten to work, but I preferred to stick with the smaller code snippet that I could quickly understand and properly document, rather than have to spend a long period understanding a larger piece of code.

#### Controls, but after Alt+Tab
While the systems for handling controls and user input are quite robust when the user interacts normally with the game window, if the user Alt+Tabs away to another window while moving, the player character gets stuck moving in that direction until the user sends another keypress to the window. This is because the game listens for Key Down events for toggling off keys. 

As JavaFX send Key Up events constantly as a key is held, this could be fixed by not handling Key Down events at all, and instead clearing the `KeyStore` state for every action other than the ones currently being held. However, "currently being held" doesn't make much sense for an event based system, and so a system adding another layer of booleans syncing it to perhaps the game clock would be needed.

Due to time constraints, I have not attempted this.

## Not Implemented
### Gameplay
#### Level 5
I had initial plans for Level 5 involving a second boss, which would showcase the flexible strategy system and `Element` generalization by having a chargeable laser ball attack. It would charge up for some time with an image being present as warning, before flying out at high speed.

Unfortunately, I had to scrap it due to time constraints.

#### Keybind reading from file
"Doesn't your code already read keybinds from a file?" you may ask. Well, it reads that file from the *resource* folder, not a physical location on someone's computer, meaning that keybinds are still hardcoded at compile time, unless the app is distributed in a form where the resources are openly editable (and not baked into a jar or something). If I had time, I would have implemented a system where a default keybind configuration json would be written to the user configuration folder (AppData on Windows), and could then be edited to customize keybinds.

#### Music
Music is not in the game, even though it could be easily implemented using the existing `AudioClip` infrastructure, because it isn't semantic. `AudioClip` is for short clips, because it stores all of its content in-memory. `Media` is what would be used for longer music tracks. However, the code infrastructure isn't there for loading files as `Media`, so I passed on it.

### Code
#### Dynamic directory scanning for resource names
Currently resource names and paths are hardcoded in `CW2024AssetLoader`, meaning adding an asset involves editing a source code file. It would be a better idea to scan the directory for available files, then add them to the map. This would probably also have some security issues, so some work would need to be done hardening it.

#### Integration tests
There are currently only unit tests for some core components of the game. I recently discovered how to properly test JavaFX (having tests inherit from `ApplicationTest`), so I know this is possible. Other than the rather obvious issues discussed before, they were left out because generally, tests are less useful for games than for other kinds of software. Outside of core API-focused components like the covered `Vector` and `Element`, game development is a heavily iterative cycle, where most things can change at any moment, which is not much of a suitable environment for testing things other than what has been stabilized (which should be tested for regressions).

#### A generalization of `InputManager` to a generic `Input` interface
This would allow for making other forms of `Input`-implementing classes, which would allow for things like an enemy plane with the same controls/abilities as the player, but controlled by an AI that simulates keyboard inputs. It would also allow for automatically testing things that require input, without the need to use mocking frameworks like Mockito.

# Classes
## New Java Classes
Location: `/`
Location: `/managers`
Location: `/screens`
Location: `/config`
Location: `/factories`
Location: `/factories/interfaces`
Location: `/overlays`
Location: `/elements`
Location: `/elements/actors`
Location: `/elements/strategies`
Location: `/assets`

### Miscellaneous
#### Vector
Location: `/`

This is a class for encapsulating a position or motion. It represents a 2D Vector with x and y components. It has various utility methods related to positions and motions, like normalization or ensuring it's within a range.

### Enums
#### Action
Location: `/managers`

This is an enum with all of the possible 'Actions' defined for the game. Examples include UP, DOWN, FIRE, CONFIRM and so on - things that the player can do and that keybinds can be mapped to. These are automatically enumerated by `KeybindStore`, so adding an entry here is all that is needed to add a new action.

#### ScreenCode
Location: `/screens`

This is an enum representing all the Screens available in the game. For every screen accessible to the player, there should be an entry here and a corresponding case statement in the switch statement in  `ScreenFactory`.

#### UserPlaneCode
Location: `/elements/actors`

This is an enum representing all the planes available to the user. For every entry here, there should be a corresponding entry in the switch statement in `ActorFactory`.

#### ProjectileCode
Location: `/elements/actors`

This is an enum representing all the projectiles available to create. These are fired by both enemies and players. For every entry here, there should be a corresponding entry in the switch statement in `ProjectileFactory`.

#### EnemyCode
Location: `/elements/actors`

This is an enum representing all the enemies in the game. For every entry here, there should be a corresponding entry in the switch statement in `ActorFactory`.


### Screens
Classes to do with `Screen`s, mostly ones from the actual `screens` subpackage.

#### Screen
Location: `/screens`

An interface defining the behaviour of a `Screen`. These are being able to return a scene that can be added to the application stage, and being able to be started.

#### ScreenParent
Location: `/screens`

This is an abstract class generalized out from what was originally LevelParent to cover all screens, including ones such as the Main Menu which don't involve gameplay. It contains logic common to all screens, such as initializing the graph of JavaFX Nodes used for the Overlay system.

#### SceneSizeChangeListener
Location: `/screens`

This is a class containing methods for implementing letterboxing. The code here is not original, the source is credited in the Javadoc.

#### ScreenChangeHandler
Location: `/screens`

This is an interface representing a handler for changing screens. This is needed so that it can be passed to other classes that might want to trigger a screen change, but don't have the access required to do so.

#### MainMenu
Location: `/screens`

This is a class for the Main Menu of the game. It works as the entry point for the game, and at the moment only has a single button that causes a screen switch to Character Select. All the class really does is hold and update a `MenuOverlay`.

#### CharacterSelect
Location: `/screens`

This is a class for the Character Select screen. It has two buttons, allowing the player to choose between characters.

#### CountdownLevel
Location: `/screens`

This is a class for Levels with a countdown timer as the win condition. It has a `TimerOverlay` displayed, counting down from a fixed time of 20s.

#### LevelThree
Location: `/screens`

The Level that comes after Two. It is a countdown level with blue enemies.

#### LevelFour
Location: `/screens`

The Level that comes after Three. It is a countdown level with red enemies. After this level is beat, it triggers the win sequence.

### Overlays
Classes that all inherit from `ContainerElement` that are drawn above the gameplay elements. Does not include `GameplayOverlay` because that is modified from `LevelView`.

#### MenuOverlay
Location: `/overlays`

This is an abstract class that represents Menu Overlays - overlays that consist of interactive buttons. It contains a `GridPane` that aligns elements along with a grid, and logic for positioning a menu cursor along that grid. It uses `TextBox`es for its buttons.

#### MainMenuOverlay
Location: `/overlays`

This is a class representing everything in the Main Menu other than the background. It contains the button, and the logic to activate it.

#### CharacterSelectOverlay
Location: `/overlays`

This is a class representing the Character Select Overlay - the elements rendered above the background in the Character Select screen. 

#### PauseOverlay
Location: `/overlays`

This is a class representing the Pause Overlay - the MenuOverlay displayed when paused in-game. It should get updated with real-time as opposed to virtual time by the Level.

#### FloatingOverlay
Location: `/overlays`

This is an abstract class representing overlays that are just a simple container for its elements, that cover the entire screen. 

#### GameplayOverlay
Location: `/overlays`

This is a class representing the Gameplay Overlay - the overlay that displays the player's health. It has its own internal logic for tracking how many hearts to display, and needs to be updated with such information.

#### TimerOverlay
Location: `/overlays`

This is a class representing the Timer Overlay - the overlay used for countdown levels 3 and 4, which displays a red box with black text in the top right that serves as a countdown timer for the level. It needs to be updated with the current time.

### Managers
Classes that roughly "Manage" something, such as input.

#### CollisionManager
Location: `/managers`

This is an interface defining the behaviour of a CollisionManager. That is, it has a method `handleCollisions`, that is meant to be run with two lists of actors that are supposed to have their collision between them handled. For example, user projectiles and enemies.

#### DamageCollisionManager
Location: `/managers`

This is a class implementing CollisionManager that implements the collision behaviour currently in-game. On collision, actors take 1 damage, unless the first actor is a projectile, in which case the second actor takes the projectile's damage value as damage.

#### KeybindStore
Location: `/managers`

This is a class used for mapping `KeyCode`s to `Action`s and `Action`s to `BooleanProperty`s. The `KeyCode`s are fetched from a JSON file and the `Action`s are fetched from the enum. What this does is keep track of the state of input - every time a key event with a certain `KeyCode` is triggered, it affects the corresponding shared `BooleanProperty`, meaning one `Action` can be triggered by multiple `KeyCode`s.

#### InputManager
Location: `/managers`

This is a class used to handle user input and direct it to the `KeybindStore`. An instance of this has to be passed anywhere where user input is needed, such as in user strategies.

### Factories
Classes that handle the construction of various things.

#### ActorFactory
Location: `/factories/interfaces`

This is an interface that defines a factory for creating `Actor`s - the enemies, boss and player character. It populates them with not only their image and data, but also strategies.

#### ElementFactory
Location: `/factories/interfaces`

This is an interface that defines a factory for creating some standalone `Element`s - currently only the boss's shield image and Screen backgrounds.

#### OverlayFactory
Location: `/factories/interfaces`

This is an interface that defines a factory for creating many different `Element`s associated with Overlays. They mostly all share the same common feature of needing to be drawn on a node that isn't the root of the scene.

#### ProjectileFactory
Location: `/factories/interfaces`

This is an interface that defines a factory for creating `Projectile`s. These are both projectiles fired by the enemies and projectiles fired by the player. It populates them with not only their image and data, but also strategies.

#### ScreenFactory
Location: `/factories/interfaces`

This is an interface that defines a factory for creating `Screen`s. These include both the menus and the main gameplay, and so this is used heavily by `Controller` for switch between screens.

#### Factory
Location: `/factories`

This is a class that contains shared behavior for all implementations of the interfaces above.

#### The Implementations
As the implementations just fulfil the interfaces described above, they are simply listed below without descriptions.

Locations: `/factories`
- ActorFactoryImpl
- ElementFactoryImpl
- OverlayFactoryImpl
- ProjectileFactoryImpl
- ScreenFactoryImpl

### Elements
Anything that can be added to the JavaFX Node graph and convenience methods to operate on them.

#### Element
Location: `/elements`

This is the abstract class representing an `Element`, the most general of them all. It has a reference to its root node and its own node and various methods such as `show()` that adds it to the root.

#### ImageElement
Location: `/elements`

This is a class representing an `Element` that has an `ImageView` as its node. This is the basis behind most elements in the game.

#### ContainerElement
Location: `/elements`

This is an abstract class representing an `Element` that has a container as its node - a container being a node that can contain other nodes. This is the basis behind all of the Overlays, for one, but is also behind the heart display and things like Text boxes.

#### ClickListener
Location: `/elements`

This is an interface defining a handler for user confirmation. i.e, confirmation in menus.

#### ProjectileListener
Location: `/elements`

This is an interface defining what should be done with a projectile passed to it. It is used for allowing `Actor`s to add projectiles to the level, via the level passing them a lambda that does that.

#### TextBox
Location: `/elements`

This is a class representing a Text box, used in menus. It consists of a white rectangle sized to fit the menu's `GridPane` bounds and black text that renders on top of it.

#### TimeDisplay
Location: `/elements`

This is a class representing a Time display, used in countdown levels. It consists of a red rectangle with black text in the middle showing a time.

#### RegularPlane
Location: `/elements/actors`

This is a class representing the ordinary User Plane, the original class having been modified into an abstract one.

#### GreenPlane
Location: `/elements/actors`

This is a class representing the secondary, Green User Plane. It has different parameters and different behaviour on focus fire.

### Assets
Classes to do with loading and processing assets.

#### AssetLoader
Location: `/assets`

This is a class representing an interface for an Asset Loader, something that can load images and load sounds.

#### CW2024AssetLoader
Location: `/assets`

This is an abstract class that contains the predefined names and locations of all assets used in the game. Meant for inheriting from to implement asset loaders.

#### UpFrontAssetLoader
Location: `/assets`

This is a class that implements AssetLoader and inherits from CW2024AssetLoader. It loads all assets up front, then stores them in a map, then fetches them from that map upon request.

#### CachedAssetLoader
Location: `/assets`

This is a class that implements AssetLoader and inherits from CW2024AssetLoader. It loads assets on-demand, but then stores them in a map, and fetches assets if they are already present.

### Configs
Classes used to bundle data together to pass to constructors. As these are just fancy data classes, they will be listed below with no further details, along with some class relations. The naming pattern is {name}Config, where {name} is the class the config is meant for constructing.

Location (all): `/configs`

(: indicates inheritance)
- ElementConfig
- ImageElementConfig : ElementConfig
- ActiveActorDestructibleConfig : ImageElementConfig
- FighterPlaneConfig : ActiveActorDestructibleConfig
- ProjectileConfig : ActiveActorDestructibleConfig
- BossConfig : FighterPlaneConfig
- UserPlaneConfig : FighterPlaneConfig
- OverlayConfig : ElementConfig
- TextBoxConfig : ElementConfig
- ScreenConfig

## Modified Java Classes
### Top Level
#### Main
Location: `/`

The entry point of the program. 

First, `SCREEN_HEIGHT` and `SCREEN_WIDTH` are now public instead of private, removing the need to pass them to methods. As the game now operates with fixed coordinates (and just scaling up/down using letterboxing), those two are not meant to change, meaning this change is fine conceptually. The target `FRAME_RATE` is also present for similar reasons - no point passing it as a parameter when it's (at the moment) meant to be fixed by the program itself. If there were plans to make a settings screen where these could be changed during the game or read from a file, a different approach would be needed.

Second, the class no longer stores an instance of `Controller`, as that is instead stored within controller itself as a singleton and the program never returns to `start()`.

Third, the class now also initializes the `AssetLoader` and `KeybindStore` to be used throughout the rest of the program.

Finally, the exceptions have been cleaned up from the methods to reduce visual clutter, mainly by removing the use of reflection within the next class discussed.

#### Controller
Location: `/`

The class roughly 'controlling' the flow of the program, loading Levels and displaying them on the Stage.

First, the usage of `Observer` has been removed. While it's commonly used in JavaFX itself, it's been deprecated since Java 9, and is also an unnecessary complication in this case. In my opinion, the `Observer` pattern's main benefit is when there are potentially multiple `Observer`s, or different `Observer`s, for the `Observable`s, because then each `Observer` can add itself to the `Observable`'s list of `Observer`s to update. 

In my current code, I instead have `Screen`s call the `Controller`'s `goToLevel` method directly, via getting a singleton instance of it. As there is only going to be one instance of `Controller`, this doesn't have a downside I am aware of, while removing all of the complication that comes with using `Observable`.

Second, the `Controller` no longer uses reflection to load levels/screens. Reflection does have some benefits to do with flexibility - specifically, if used in a certain manner it could lead to adding new levels simply being making a new class (only one location needing changes), for example having `Screen` classes part of the game annotated with a custom annotation such as `@GameScreen`, and scanning the classpath to find all classes with that annotation.

However, the usage of reflection in the original code doesn't carry any of the potential benefits. The levels are found via searching up their fully qualified class name with a string. This means that the `Controller` still doesn't know about what levels actually exist, and is looking for a hardcoded one with a certain name. 

As such, I replaced the reflection code with a simple enum of available `Screen`s, `ScreenCode`, and a switch case within the `ScreenFactory` that matches the enum with the right `Level` class. This means that one gets compile-time verification that the level classes actually exist, instead of a runtime error from the reflection failing. While there *is* one more step to adding a level, as one has to route the level code to the right class instead of essentially the class path *being* the level code, the increased safety and resulting massive cleanup of exceptions is worth it in my opinion.

Finally, the `catch` branch within the `update` method of the original code for creating an error popup has been modified into a separate, generic error popup, called from various places in the program for alerting the user of an error, and proceeding to exit the program upon closing the window.

### Screens
#### Level (formerly LevelParent)
Location: `/screens`

An abstract class containing shared behaviour for all game levels.

First, the logic common to setting up JavaFX nodes has been abstracted out into a parent classed called `Screen`, as the new code has screens other than Levels, and of course this logic has itself been modified to achieve letterboxing as described previously.

Second, `SCREEN_HEIGHT_ADJUSTMENT` has been removed, in favour of calculating boundaries based off of the `SCREEN_HEIGHT` directly, meaning that `SCREEN_HEIGHT` can be adjusted with the rest of the program adapting. Along with that, `MILLISECOND_DELAY`, `screenHeight`/`screenWidth` and `enemyMaximumYPosition` have all been removed, as their responsibilities have been delegated elsewhere. (`Main` for `MILLISECOND_DELAY` in the form of `FRAME_RATE`, similarly in `Main` for `screenHeight`/`screenWidth` and individual `Level`s for `enemyMaximumYPosition`).

Third, all element creation is now handed off to factories, as opposed to being constructed directly within the `Level` class.

Fourth, `Level` no longer controls user input along with the user character. User input is instead handled within `InputManager`, which then has its output passed to places that need user input (such as screens or actor strategies). Along with that, collision behaviour is now abstracted out to a `CollisionManager`, instead of being hardcoded into `Level`.

Sixth, the update loop, `updateScene()`, now calculculates and passes `deltaTime` and `currentTime` to individual update methods, to facilitate the uncoupling of logic from framerate. These times are calculated from a virtual `Timer` that also lives in the class that increments a `virtualTime` field every millisecond, meaning that when logic should be paused like on the pause screen, the game's time can be paused. This would also allow for things like implementing a (pauseable) speedrun timer more easily.

Seventh, `Level` no longer controls the spawning of projectiles. The original code polled entities each frame for a projectile to spawn, and added it to the projectile list when it didn't return null. This is quite a weird inversion of control, as conceptually entities should decide when they want to fire a projectile. Thus, projectile spawning is now handled within entities through a `ProjectileListener` callback passed to them, which adds the projectile to the projectile list, with entities deciding when to call it within themselves using a `Firing` strategy.

Eighth, the `Level` now has handling for a separate pause overlay to the main gameplay overlay. This pause overlay uses real time to update as opposed to the virtual time mentioned in 'Sixth', as the virtual timer is paused when on this screen.

#### LevelOne
Location: `/screens`

The first `Level` of the game.

First, the `Level` no longer hard-codes the background image name, as that is now handled within the factory. This makes modifying it easier, as all the background image names are now modifiable within one place (the factory). The player initial health is also no longer hardcoded, instead being passed from the factory based on player character. The next level is also hard-coded in-line using an enum instead of the class named for reasons discussed earlier.

Second, `initializeFriendlyUnits` is now completely gone from both this and the parent class. While it would be necessary in the case of having ally units alongside the player character, there were no plans for it, and so instead the user is simply initialized based on the passed `UserPlaneCode` within the parent constructor. Enemy units are also constructed using their corresponding factory.

Third, to facilitate the `deltaTime` refactor, the enemy unit spawn emulates the original behaviour by only attempting to spawn an enemy every 50.0ms of time.

Fourth, `goToNextLevel` now has the current `UserPlaneCode` passed, so the player's character choice is kept.

Finally, the class no longer instantiates `LevelView` (now `GameplayOverlay`), as this is handled in the parent class as a universal overlay for all gameplay `Level`s.

#### LevelTwo
Location: `/screens`

The second `Level` of the game.

Points one, two and four from `LevelOne` apply. The logic for spawning the `Boss` doesn't need a refactor, so three doesn't.

Point five, with the `LevelView` instantiation is different for `LevelTwo`, as `LevelViewLevelTwo` has been removed entirely. In the original code, that handled the showing and hiding of the `Boss`'s shield, but now the shield is tied to and handled by the `Boss` element itself.

### Overlays
#### GameplayOverlay (formerly LevelView)
Location: `/overlays`

Contains and manages the player's health display, along with the win and loss images.

First, the win and loss images no longer have hardcoded arbitrary numbers as their position. Instead, they are centred on the screen as a whole. They, including the `HeartDisplay`, are also obviously constructed with factories.

Second, the overlay itself is now wrapped in a container node, allowing the `HeartDisplay` to actually render above the player character and projectiles consistently.

Third, it doesn't need to handle the adding/removal of the win/loss images to the node, because they now handle that themselves as `Elements`.

#### LevelViewLevelTwo
Location: removed

Originally contained logic for managing the `Boss`'s shield, said logic has been moved to the boss itself.

#### GameOverImage
Location: removed

The game over image is now a generic `Element` with a specific image created by an `OverlayFactory`.

#### WinImage
Location: removed

The win image is now a generic `Element`, like the `GameOverImage`.

#### ShieldImage
Location: removed

The shield image is now a generic `Element`, much like the above.

### Elements and Actors
#### HeartDisplay
Location: `/elements`

The player's health display at the top left.

First, as with all element creation in the game, it now uses a factory for making the hearts. It has also been generalized to a `ContainerElement`, so making and adding a heart is just using a factory with the root set to the container, and then creating it. `HEART_IMAGE_NAME` is also no longer hardcoded given it's been moved to the factory.

Second, the hearts are now removed from an internal linked list rather than the container directly. Once a heart is removed from the list, the `hide()` method inherited from `Element` works to remove it from the container.

#### ActiveActor
Location: `/elements/actors`

Represents an `Element` able to be updated.

First `IMAGE_LOCATION` base for images is no longer hardcoded here as an `AssetLoader` handles image loading. Similarly, all image-related code has been moved to `ImageElement`.

Second, the movement related methods have been moved to `Element`. As a result, all `ActiveActor` does now is define `updateActor` (formerly `updatePosition`).

#### Destructible
Location: `/elements/actors`

No changes have been made outside of formatting and Javadoc.

#### ActiveActorDestructible
Location: `/elements/actors`

Represents an actor that can take damage and be destroyed.

First, `updatePosition` and `updateActor` have been fused into one method. 

Second, `setDestroyed` has been removed as having `destroy` access the field directly saves a method. Destroyed actors are always removed from the level the next frame, so having a set method is unnecessary as an actor will never be un-destroyed.

Third, `ActiveActorDestructible` now holds a `Movement` strategy, a speed, a boolean field to determine whether it should be kept in bounds, and a default implementation for `updateActor` that updates and applies the `Movement` strategy. This means that all classes inheriting from `ActiveActorDestructible` now only need to pass a `Movement` strategy and call the default implementation for `updateActor` to get functional movement.

#### FighterPlane
Location: `/elements/actors`

Represents a fighter plane, whether enemy or ally.

First, `getProjectileXPosition` and `getProjectileYPosition` have both been removed, as projectiles are now always generated at the head of the `FighterPlane` by default. To allow for the new projectile firing model, it now has fields for a `ProjectileFactory`, `ProjectileListener` and `Firing` strategy. It also has a field for the proper Y offset for its projectiles, and 2 `AudioClip`s for sounds upon firing and death.

Second, `updateActor` now has a default implementation calling the default movement code from `ActiveActorDestructible`, and adding code updating and interpreting the `Firing` strategy, to fire projectiles. This means that classes inheriting from it don't need to implement any special firing logic, only pass their own `Firing` strategy.

#### EnemyPlane
Location: `/elements/actors`

Represents a non-boss enemy.

This class has been completely pared down, as all of the logic it originally has has been moved up to `FighterPlane` or `ActiveActorDestructible`, or moved into individual strategies. It is now nothing except a concrete implementation of `FighterPlane`.

#### UserPlane
Location: `/elements/actors`

Represents the player character.

First, the individual movement methods, `moveUp` and `moveDown` have been removed, along with `isMoving` and `stop`. This is because of the movement being handled in `Movement` strategies, and being processed in `ActiveActorDestructible`.

Second, it doesn't have the image tied to it, like the rest of the `Element`s, as that is handled in the factory. `INITIAL_X_POSITION`, `PROJECTILE_X_POSITION` and such are all also removed as instead the user spawns in at the middle-left of the screen, with projectiles spawning from its right bounds at an offset.

Third, it now overrides `getCollisionBounds`, a method added to `ActiveActorDestructible` to encapsulate getting the collision bounds, to make its own hitbox/collision box a square instead of rectangle, making the gameplay feel a bit more fair.

Finally, it overrides `updateActor` behaviour to ensure damage only happens every 500ms, and on the next frame of gameplay. This prevents the user from nearly instantly dying on collision with the `Boss`, and prevents some bugs with pausing.

#### Boss
Location: `/elements/actors`

Represents the Boss of Level Two.

Similar to the player and enemy planes, this class has been _significantly_ pared down as all of the logic has been moved to parent classes and strategies. Only the following remain:

First, the boss class is now only responsible for managing the shield image, being the only class with a reference to it and updating its position every update cycle.

Second, it has extra `AudioClip`s stored and played for taking damage (as it has plenty of health) and having its shield hit (being immune to damage).

Third, it holds a `Shielding` strategy for determining whether it is currently shielded, and thus whether to show the shield image and prevent damage from being taken.

All other logic has been moved to classes such as `BossMovement`.

#### Projectile
Location: `/elements/actors`

Represents a projectile fired by an actor.

Projectile has been turned from an abstract class with no implementation, to the only class used for projectiles in the game. 

First, `Projectile` is no longer abstract. As such, it has implementations for all of its methods.

Second, it simply has a property, `isAllyProjectile`, that determines whether it's an ally or enemy projectile.

Third, it has default movement inherited from `ActiveActorDestructible`, with an additional check that if it reaches the edges of the screen, it destroys itself.

Fourth, it has a `damage` property, so the damage of projectiles can be customized.

As with all `Element`s, its image can be customized, so this is enough to create the 9 kinds of projectiles now present in-game.

#### EnemyProjectile
Location: removed

See the entry for `Projectile` above.

#### UserProjectile
Location: removed

See the entry for `Projectile` above.

#### BossProjectile
Location: removed

See the entry for `Projectile` above.

# Unexpected Problems
## JavaFX not being included in JREs
I have actually encountered this issue before from trying to run another game written in JavaFX, but I forgot about it and was surprised yet again. Modern JREs don't come bundled with JavaFX, and installing it as a library doesn't exactly work. As such, I had to install a JDK that comes with it bundled. Being on NixOS, I used the `shell.nix` file in the directory to accomplish this.
```nix
{ pkgs ? import <nixpkgs> {} }:
  pkgs.mkShell {
    nativeBuildInputs = [
      pkgs.graphviz
      pkgs.maven
      (pkgs.zulu.override { enableJavaFX = true; })
      pkgs.ffmpeg
      pkgs.jdt-language-server
    ];
}
```
The core line there being the `enableJavaFX = true` override.

## Testing JavaFX being difficult
Writing unit tests for JavaFX went a bit weirdly. JavaFX requires all of its features to run on an application thread, which is meant to survive for the length of the program. That doesn't exactly mesh well with many separate unit tests.

At first, I solved this via manually starting up the platform.
```java
@BeforeAll
static void startup() {
    Platform.startup(() -> {});
}
```

This worked for running individual tests from IDEA. However, when running all tests at once through right clicking on test folder, or running `mvn test`, it failed due to the many test threads trying to start up the same `Platform` multiple times simultaneously.

I spent quite some time attempting to fix this, attempting to somehow start up and then tear down the `Platform`, considering merging all tests into one big one that only starts up the `Platform` once... Eventually though, I landed on [TestFX](https://github.com/TestFX/TestFX).

With `TestFX` added to the project, all I needed to do was have JavaFX tests inherit from `ApplicationTest`, and then they worked without issue... mostly. The console output constantly prints errors regarding `JavaFX` not being open for inspection, but other than that, the tests themselves with their object creation and assertions work fine.

## Getting Letterboxing functioning
As mentioned in the document earlier, I found an existing solution for letterboxing [here](https://stackoverflow.com/questions/16606162/javafx-fullscreen-resizing-elements-based-upon-screen-size). However, the integration of it into my codebase didn't go as smoothly as one might expect. No matter what I did, it didn't seem to work properly, and eventually I narrowed it down to my structure of nodes.

It took many hours of experimentation and reading JavaFX documentation, including a period where I attempted to use Copilot to find the answer (that did not work and it just gaslit me). Eventually though, I figured out a solution. 

```
Group 
StackPane
  Pane (root, fixed size)
    Enemies, etc
  Pane (overlay, fixed size)
    HeartDisplay
```

The above represents the graph structure I found that eventually worked as I wanted. Below is the code implementation of it within `ScreenParent`

```java
// initializing the main nodes
this.root = new Pane();

// VERY important - limits the size of the Pane Node used for drawing
// This allows the StackPane to properly align it
root.setMaxHeight(Main.SCREEN_HEIGHT);
root.setMaxWidth(Main.SCREEN_WIDTH);
root.setClip(new Rectangle(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT));

// To align the root pane above
this.stackPane = new StackPane(root);
stackPane.setStyle("-fx-background-color: black;");

// Keeps the "camera" of sorts fixed in place
this.scene = new Scene(new Group(stackPane));

// letterboxing
SceneSizeChangeListener.letterbox(scene, stackPane, Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
```

Let's break this down starting from the end, being the top of the node hierarchy. Firstly, the scene *has* to focus on a group.

```java
this.scene = new Scene(new Group(stackPane));
```

I do believe this prevents it from following the `StackPane` around in a sense, but I do not know the exact reason this is required.
Next, a `StackPane` has to be made to hold all of the elements.

```java
this.stackPane = new StackPane(root);
stackPane.setStyle("-fx-background-color: black;");
```

A `StackPane` well, stacks child nodes on top of each other in-order. This means that children have proper layering. This `StackPane` is also what the `Scene` sees, so the background should be set to black to have it have the proper "letterboxing with black bars" look.

Next, the node where the main game content (elements, backgrounds, so on) is drawn *has* to be a `Pane`.

```java
// initializing the main nodes
this.root = new Pane();

// VERY important - limits the size of the Pane Node used for drawing
// This allows the StackPane to properly align it
root.setMaxHeight(Main.SCREEN_HEIGHT);
root.setMaxWidth(Main.SCREEN_WIDTH);
root.setClip(new Rectangle(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT));
```

The original project used a `Group` for holding game elements. This is fundamentally incompatible with fixing the resolution of the game window, as far as I can tell, because `Group`s have no set size - they grow with their elements. A `Pane` is like a group, but it can control the position of its elements, along with having a fixed size. Setting the size to the desired screen height and width prevents it from growing beyond it as say, projectiles fly past the end of the screen. A rectangular clip mask makes projectiles not visible past the screen edges.

Finally, additional overlays are added to the `StackPane` as containers, so they render on top of the main game content pane sitting at the bottom. This is the core of the overlay system.

```java
// OverlayFactoryImpl.java 
// 'root' is the StackPane here
public GameplayOverlay createGameplayOverlay(int heartsToDisplay) {
    OverlayConfig config = new OverlayConfig(root, this);

    return new GameplayOverlay(config, heartsToDisplay);
}
```

With this setup, the existing letterboxing solution works flawlessly without any modification. With the note that the *`StackPane`* is what should have its content transformed to match the new screen size, not the `Group` wrapping it. I do not know why this is the case.

```java
// ScreenParent.java
SceneSizeChangeListener.letterbox(scene, stackPane, Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
```






