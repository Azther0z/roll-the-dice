# User Interface

Back to [README](../README.md).

The game UI is built with JavaFX. Screens are implemented as JavaFX layout classes under [`src/gui/`](../src/gui/), and scene transitions are coordinated by [`SceneManager`](../src/gui/SceneManager.java).

For screenshots of the UI, see [`Saint_and_Poom_Documentation.pdf`](../Saint_and_Poom_Documentation.pdf).

## Scene management

[`src/gui/SceneManager.java`](../src/gui/SceneManager.java) owns the main JavaFX `Scene` and a root `BorderPane`. It switches the center content between:

- Main menu
- Scrollable map menu
- Fight scene

It also resets the game state when a new scene/game is created.

## Main menu

Source: [`src/gui/MainMenu.java`](../src/gui/MainMenu.java)

The main menu displays the project title, `ROLL THE DICE`, and two buttons:

- **START** - opens the map screen.
- **EXIT** - closes the application.

## Map menu

Source: [`src/gui/MapMenu.java`](../src/gui/MapMenu.java)

The map menu displays the generated route map. It contains:

- Node buttons/layout elements for enemy, shop, rest, and boss nodes.
- Gray lines representing edges between connected nodes.
- Node status text such as `Available` or `Selected`.

Only valid next nodes can be selected. When the player selects a valid node, that node executes its behavior:

- Enemy/Boss node -> fight scene
- Rest node -> rest overlay
- Shop node -> shop overlay

## Fight scene

Source: [`src/gui/FightScene.java`](../src/gui/FightScene.java)

The fight scene is divided into three main areas:

1. **Player status panel**
   - HP
   - Current attack value
   - Current defense value

2. **Dice panel**
   - Dice type: `NORMAL`, `MULTIPLY`, or `DIVIDE`
   - Dice range and rolled value
   - Clickable action text for normal/multiply dice to switch between attack and defense

3. **Enemy panel**
   - Enemy name
   - Enemy HP
   - Enemy attack, defense, and heal values
   - Attack target selector
   - Divide target selectors for divide dice

The **END TURN** button resolves the player's selected actions, lets enemies respond, and checks whether the battle has ended.

## Reward menu

Source: [`src/gui/RewardMenu.java`](../src/gui/RewardMenu.java)

After winning a normal battle, the reward menu appears as an overlay on the map. It lists the player's dice and gives two upgrade choices for each dice:

- **Upgrade MIN**
- **Upgrade MAX**

Only one upgrade can be applied per reward menu. After upgrading, a **Continue** button appears.

## Shop menu

Source: [`src/gui/ShopMenu.java`](../src/gui/ShopMenu.java)

The shop menu appears after entering a shop node. It shows:

- Current dice list
- Temporary money for this shop visit
- Generated dice available for purchase
- Cost of each item
- **Buy** buttons
- **Continue** button

Money is calculated from the sum of the current dice roll values and is not carried to later nodes.

## Rest menu

Source: [`src/gui/RestMenu.java`](../src/gui/RestMenu.java)

The rest menu appears after entering a rest node. It shows:

- Dice rolled for healing
- HP before resting
- HP after resting
- **Continue** button

The heal amount is based on the total rolled value of all dice.

## Win and lose menus

Sources:

- [`src/gui/WinMenu.java`](../src/gui/WinMenu.java)
- [`src/gui/LoseMenu.java`](../src/gui/LoseMenu.java)

The win menu appears after defeating the boss. The lose menu appears when the player is defeated in combat. These menus provide end-state feedback and allow returning/restarting through the main flow.

## UI constants

Screen sizes, spacing, font sizes, and component dimensions are centralized in [`src/utils/GameConfig.java`](../src/utils/GameConfig.java). The configured screen size is `1280 x 720`.
