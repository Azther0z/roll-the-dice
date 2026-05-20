# Class Architecture

Back to [README](../README.md).

For the best-quality UML diagram, use the class diagram embedded inside [`Saint_and_Poom_Documentation.pdf`](../Saint_and_Poom_Documentation.pdf). The standalone [`UML.png`](../UML.png) is also available, but the UML inside the PDF should be preferred when image clarity matters.

## Package overview

The project is organized into these main packages:

| Package | Responsibility |
| --- | --- |
| [`main`](../src/main/) | JavaFX application entry point. |
| [`gui`](../src/gui/) | JavaFX screens, menus, overlays, and scene switching. |
| [`utils`](../src/utils/) | Game state, combat logic, shop logic, enums, and configuration constants. |
| [`dice`](../src/dice/) | Dice classes and special dice behavior. |
| [`node`](../src/node/) | Map nodes and map edges. |
| [`unit`](../src/unit/) | Player, monsters, base unit model, and behavior interfaces. |

## Entry point

[`src/main/Main.java`](../src/main/Main.java) extends `javafx.application.Application`.

It sets up the primary stage by getting the scene from [`SceneManager`](../src/gui/SceneManager.java), setting the window title from [`GameConfig.PROJECT_NAME`](../src/utils/GameConfig.java), and launching JavaFX.

## GUI layer

The GUI package contains JavaFX layout classes:

- [`MainMenu`](../src/gui/MainMenu.java) - title screen with start and exit buttons.
- [`MapMenu`](../src/gui/MapMenu.java) - route map display and overlays.
- [`FightScene`](../src/gui/FightScene.java) - combat interface.
- [`RewardMenu`](../src/gui/RewardMenu.java) - dice upgrade overlay.
- [`ShopMenu`](../src/gui/ShopMenu.java) - dice purchasing overlay.
- [`RestMenu`](../src/gui/RestMenu.java) - healing overlay.
- [`WinMenu`](../src/gui/WinMenu.java) - victory state.
- [`LoseMenu`](../src/gui/LoseMenu.java) - defeat state.
- [`SceneManager`](../src/gui/SceneManager.java) - central scene navigation manager.

`SceneManager`, `GameLogic`, and `FightLogic` use singleton-style access through `getInstance()` so that UI screens can access shared game state.

## Game state and logic

### GameLogic

Source: [`src/utils/GameLogic.java`](../src/utils/GameLogic.java)

`GameLogic` stores overall game state:

- Player instance
- Current node
- Boss node
- Node grid
- Edge list
- Defeat/win flags

It is also responsible for:

- Starting a new game
- Giving starter dice
- Generating the map
- Processing rest-node healing

### FightLogic

Source: [`src/utils/FightLogic.java`](../src/utils/FightLogic.java)

`FightLogic` manages combat state:

- Enemy list
- Fight end flag
- New fight initialization
- Dice rolling at turn start
- Player and enemy stat updates
- End-turn resolution
- Victory/defeat checks

It creates a boss enemy if the current node is the boss node; otherwise it generates random enemies.

### ShopLogic

Source: [`src/utils/ShopLogic.java`](../src/utils/ShopLogic.java)

`ShopLogic` handles shop visits:

- Rolls the player's dice
- Converts total rolled value into temporary money
- Generates shop dice from [`DiceConfig`](../src/utils/DiceConfig.java)
- Handles dice purchases

## Dice model

The dice package contains:

- [`Dice`](../src/dice/Dice.java)
- [`MultiplyDice`](../src/dice/MultiplyDice.java)
- [`DivideDice`](../src/dice/DivideDice.java)

`Dice` stores minimum value, maximum value, current action type, and current rolled value. It can roll a random value and switch between attack and defense actions.

`MultiplyDice` extends `Dice` and multiplies an action value.

`DivideDice` extends `Dice` and stores a target enemy for division effects.

Dice configuration is defined in [`src/utils/DiceConfig.java`](../src/utils/DiceConfig.java), while dice type labels are defined in [`src/utils/DiceType.java`](../src/utils/DiceType.java).

## Unit model

### BaseUnit

Source: [`src/unit/base/BaseUnit.java`](../src/unit/base/BaseUnit.java)

`BaseUnit` is the abstract parent class for the player and monsters. It stores:

- Maximum HP
- Current HP
- Name

It also defines the abstract method `takeDamage(int damage)`.

### Behavior interfaces

The project uses interfaces to compose unit behavior:

- [`Attackable`](../src/unit/base/Attackable.java) - provides attack value update/execution behavior.
- [`Defendable`](../src/unit/base/Defendable.java) - provides defense value update behavior.
- [`Healable`](../src/unit/base/Healable.java) - provides heal value update/execution behavior.

This allows different monsters to support different combinations of actions.

### Player

Source: [`src/unit/player/Player.java`](../src/unit/player/Player.java)

`Player` extends `BaseUnit` and implements `Attackable`, `Defendable`, and `Healable`. It owns the dice list and calculates attack, defense, and healing from dice rolls and dice action choices.

### Monsters

Monster classes are stored in [`src/unit/monster/`](../src/unit/monster/):

- `Attacker`
- `Defender`
- `Healer`
- `Hybrid`
- `Vampire`
- `Boss`

Each monster extends `BaseUnit` and implements the interfaces that match its behavior.

## Map node model

The map is represented by nodes and edges:

- [`Node`](../src/node/Node.java) - abstract JavaFX `VBox` node with row/column, status, click handling, and abstract `executeNode()`.
- [`EnemyNode`](../src/node/EnemyNode.java) - starts a fight.
- [`BossNode`](../src/node/BossNode.java) - starts the boss fight.
- [`RestNode`](../src/node/RestNode.java) - opens the rest overlay.
- [`ShopNode`](../src/node/ShopNode.java) - opens the shop overlay.
- [`Edge`](../src/node/Edge.java) - connects one node to another.

The node click logic checks whether a node is available based on the current node and the edge list.

## Configuration and enums

Important utility/configuration classes:

- [`GameConfig`](../src/utils/GameConfig.java) - project name, map size, screen size, spacing, and UI dimensions.
- [`UnitConfig`](../src/utils/UnitConfig.java) - player, monster, and boss base stats.
- [`DiceConfig`](../src/utils/DiceConfig.java) - available dice ranges, types, and costs.
- [`ActionType`](../src/utils/ActionType.java) - dice action states.
- [`DiceType`](../src/utils/DiceType.java) - dice categories.
