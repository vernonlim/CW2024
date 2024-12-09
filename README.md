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
Classes used to bundle data together to pass to constructors. As these are just fancy data classes, they will just be listed below, along with some class relations. The naming pattern is {name}Config, where name is the class the config is meant for constructing.

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

# Unexpected Problems
## NixOS Troubles
