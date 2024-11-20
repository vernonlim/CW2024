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
To download the source code, clone the repository:
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
Open the project folder in IDEA, then navigate to Run -> Edit Configurations in the menu. Then, click the + button at the top left, select Maven and add
```shell
clean compile exec:java
```
to the run field.

Click 'Apply' and then 'Okay'. Now, clicking the play button (green triangle) at the top right of the window should run the project.

## Testing

# Features
## Implemented
## Missing

# Classes
## New Classes
## Modified Classes

# Unexpected Problems
## NixOS Troubles
