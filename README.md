# ROLL THE DICE

A JavaFX turn-based dice roguelike by Saint and Poom. The player moves through a generated node map, fights enemies, visits shops/rest nodes, upgrades or buys dice, and defeats the final boss.

## Documentation

Expanded Markdown documentation is available in [`docs/`](docs/):

- [`docs/gameplay.md`](docs/gameplay.md) - gameplay rules, dice mechanics, combat flow, map progression, rewards, rest, shop, win/lose conditions.
- [`docs/ui.md`](docs/ui.md) - JavaFX screens, UI interactions, and scene transitions.
- [`docs/class-architecture.md`](docs/class-architecture.md) - package structure, major classes, interfaces, and logic managers.
- [`docs/files.md`](docs/files.md) - project file and folder reference.

Original project references:

- [`Saint_and_Poom_Documentation.pdf`](Saint_and_Poom_Documentation.pdf) - full original documentation with gameplay explanation, screenshots, implementation details, and UML diagrams.
- UML diagrams inside [`Saint_and_Poom_Documentation.pdf`](Saint_and_Poom_Documentation.pdf) should be used when better picture quality is needed.
- [`UML.png`](UML.png) - standalone UML image, useful as a quick secondary reference.

## Project files

- `src/` - Java source code.
  - `src/main/Main.java` - JavaFX application entry point.
  - `src/gui/` - UI scenes and menus.
  - `src/utils/` - game configuration, combat logic, shop logic, enums, and helper classes.
  - `src/dice/` - dice models, including normal, multiply, and divide dice.
  - `src/node/` - map node types and edges.
  - `src/unit/` - player, monsters, base unit classes, and combat interfaces.
- `bin/` - compiled `.class` files.
- `Saint_and_Poom.jar` - runnable packaged application (`Main-Class: main.Main`).

## Running the project

### Run the packaged JAR

```bash
java -jar Saint_and_Poom.jar
```

If your Java installation does not include JavaFX, run with the JavaFX SDK module path:

```bash
java --module-path /path/to/javafx-sdk/lib --add-modules javafx.controls,javafx.graphics -jar Saint_and_Poom.jar
```

### Run from source

```bash
javac -d bin $(find src -name "*.java")
java -cp bin main.Main
```

For Java versions where JavaFX is external:

```bash
javac --module-path /path/to/javafx-sdk/lib --add-modules javafx.controls,javafx.graphics -d bin $(find src -name "*.java")
java --module-path /path/to/javafx-sdk/lib --add-modules javafx.controls,javafx.graphics -cp bin main.Main
```

## Requirements

- Java JDK
- JavaFX runtime/SDK if it is not bundled with your JDK

## Notes

The main game title in code is configured as `ROLL THE DICE` in [`src/utils/GameConfig.java`](src/utils/GameConfig.java).
