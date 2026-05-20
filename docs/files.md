# Project Files

Back to [README](../README.md).

This page summarizes the important files and folders in the project.

## Root files

| Path | Description |
| --- | --- |
| [`README.md`](../README.md) | Main landing page with overview, documentation links, and run instructions. |
| [`Saint_and_Poom.jar`](../Saint_and_Poom.jar) | Runnable packaged Java application. Its manifest points to `main.Main`. |
| [`Saint_and_Poom_Documentation.pdf`](../Saint_and_Poom_Documentation.pdf) | Full original project documentation, including gameplay explanation, screenshots, implementation details, and UML diagrams. Use the UML inside this PDF for better picture quality. |
| [`UML.png`](../UML.png) | Standalone exported UML image. Useful as a quick reference, but the PDF UML is preferred for clarity. |

## Documentation folder

| Path | Description |
| --- | --- |
| [`docs/gameplay.md`](gameplay.md) | Expanded gameplay explanation. |
| [`docs/ui.md`](ui.md) | UI screen and interaction documentation. |
| [`docs/class-architecture.md`](class-architecture.md) | Package and class architecture documentation. |
| [`docs/files.md`](files.md) | This file index. |

## Source folder

[`src/`](../src/) contains the Java source code.

### `src/main/`

| File | Description |
| --- | --- |
| [`src/main/Main.java`](../src/main/Main.java) | JavaFX application entry point. |

### `src/gui/`

| File | Description |
| --- | --- |
| [`SceneManager.java`](../src/gui/SceneManager.java) | Manages the JavaFX scene and switches between menu, map, and fight screens. |
| [`MainMenu.java`](../src/gui/MainMenu.java) | Start/exit menu. |
| [`MapMenu.java`](../src/gui/MapMenu.java) | Route map display and map overlays. |
| [`FightScene.java`](../src/gui/FightScene.java) | Combat UI. |
| [`RewardMenu.java`](../src/gui/RewardMenu.java) | Dice upgrade UI after combat. |
| [`ShopMenu.java`](../src/gui/ShopMenu.java) | Dice purchasing UI. |
| [`RestMenu.java`](../src/gui/RestMenu.java) | Healing UI. |
| [`WinMenu.java`](../src/gui/WinMenu.java) | Victory UI. |
| [`LoseMenu.java`](../src/gui/LoseMenu.java) | Defeat UI. |

### `src/utils/`

| File | Description |
| --- | --- |
| [`GameLogic.java`](../src/utils/GameLogic.java) | Main game state, map generation, starter dice, and rest behavior. |
| [`FightLogic.java`](../src/utils/FightLogic.java) | Combat initialization and turn resolution. |
| [`ShopLogic.java`](../src/utils/ShopLogic.java) | Shop money calculation, item generation, and purchases. |
| [`GameConfig.java`](../src/utils/GameConfig.java) | Global game and UI constants. |
| [`DiceConfig.java`](../src/utils/DiceConfig.java) | Dice presets and dice costs. |
| [`UnitConfig.java`](../src/utils/UnitConfig.java) | Player/enemy stats. |
| [`ActionType.java`](../src/utils/ActionType.java) | Dice action enum. |
| [`DiceType.java`](../src/utils/DiceType.java) | Dice type enum. |
| [`GuiUtils.java`](../src/utils/GuiUtils.java) | JavaFX helper methods. |
| [`ShopItem.java`](../src/utils/ShopItem.java) | Shop item wrapper for dice and cost. |

### `src/dice/`

| File | Description |
| --- | --- |
| [`Dice.java`](../src/dice/Dice.java) | Base dice class. |
| [`MultiplyDice.java`](../src/dice/MultiplyDice.java) | Dice that multiplies selected action values. |
| [`DivideDice.java`](../src/dice/DivideDice.java) | Dice that targets enemies for division effects. |

### `src/node/`

| File | Description |
| --- | --- |
| [`Node.java`](../src/node/Node.java) | Abstract map node with click handling and availability checks. |
| [`Edge.java`](../src/node/Edge.java) | Connection between map nodes. |
| [`EnemyNode.java`](../src/node/EnemyNode.java) | Node that starts a normal fight. |
| [`BossNode.java`](../src/node/BossNode.java) | Node that starts the final boss fight. |
| [`RestNode.java`](../src/node/RestNode.java) | Node that opens rest/healing UI. |
| [`ShopNode.java`](../src/node/ShopNode.java) | Node that opens shop UI. |

### `src/unit/`

| Folder | Description |
| --- | --- |
| [`src/unit/base/`](../src/unit/base/) | `BaseUnit` and behavior interfaces. |
| [`src/unit/player/`](../src/unit/player/) | Player class. |
| [`src/unit/monster/`](../src/unit/monster/) | Monster and boss classes. |

## Build output

| Path | Description |
| --- | --- |
| [`bin/`](../bin/) | Compiled `.class` files. This is build output, not primary source. |

## Preferred UML reference

When documenting or presenting the class architecture, prefer the UML diagrams inside [`Saint_and_Poom_Documentation.pdf`](../Saint_and_Poom_Documentation.pdf), because they have better picture quality than the standalone [`UML.png`](../UML.png).
