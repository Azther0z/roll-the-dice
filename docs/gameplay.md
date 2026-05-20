# Gameplay

Back to [README](../README.md).

`ROLL THE DICE` is a turn-based dice roguelike inspired by route-based deck-building games such as *Slay the Spire*. The player travels through a generated map, improves their dice set, survives battles, and wins by defeating the final boss.

For the original full explanation and screenshots, see [`Saint_and_Poom_Documentation.pdf`](../Saint_and_Poom_Documentation.pdf).

## Objective

The goal is to reach the `BossNode` at the end of the map and defeat the boss. The player loses if their HP reaches 0 during combat.

Relevant source files:

- [`src/utils/GameLogic.java`](../src/utils/GameLogic.java)
- [`src/utils/FightLogic.java`](../src/utils/FightLogic.java)
- [`src/unit/player/Player.java`](../src/unit/player/Player.java)

## Map progression

The game creates a grid-based route map with connected paths. At the beginning, the player may choose an available node from the first row. After selecting a node, only connected next nodes become available.

Node types:

- **Enemy node** - starts a normal fight.
- **Shop node** - lets the player buy new dice using temporary money from rolled dice.
- **Rest node** - heals the player based on dice rolls.
- **Boss node** - starts the final battle.

Relevant source files:

- [`src/node/Node.java`](../src/node/Node.java)
- [`src/node/EnemyNode.java`](../src/node/EnemyNode.java)
- [`src/node/ShopNode.java`](../src/node/ShopNode.java)
- [`src/node/RestNode.java`](../src/node/RestNode.java)
- [`src/node/BossNode.java`](../src/node/BossNode.java)

## Dice types

The player starts with four dice:

- Normal D4
- Normal D6
- Multiply D4
- Divide D4

Dice are represented by [`src/dice/Dice.java`](../src/dice/Dice.java), [`src/dice/MultiplyDice.java`](../src/dice/MultiplyDice.java), and [`src/dice/DivideDice.java`](../src/dice/DivideDice.java).

### Normal dice

Normal dice contribute their rolled value to either attack or defense, depending on their selected action.

### Multiply dice

Multiply dice can be assigned to attack or defense. They multiply the total value of the selected action.

For example, if normal attack dice total `5` and a multiply dice rolls `3`, the attack value becomes `15`.

### Divide dice

Divide dice target enemies. They are used to reduce enemy action values by dividing them by the rolled divide value.

## Combat flow

Combat happens in [`src/gui/FightScene.java`](../src/gui/FightScene.java) and is processed by [`src/utils/FightLogic.java`](../src/utils/FightLogic.java).

A typical combat turn follows this structure:

1. The player rolls all dice.
2. Dice actions are shown in the fight UI.
3. The player chooses whether usable dice are assigned to attack or defense.
4. The player selects an enemy attack target.
5. The player selects targets for divide dice.
6. The player presses **END TURN**.
7. The player attacks the selected enemy.
8. Defeated enemies are removed.
9. Remaining enemies attack and/or heal depending on their type.
10. If all enemies are defeated, the fight ends.
11. If the player HP reaches 0, the game ends in defeat.

## Enemy types

Enemies are units with different capabilities. The implementation uses interfaces to describe behavior:

- `Attackable` - can attack.
- `Defendable` - can defend or reduce incoming damage.
- `Healable` - can heal.

Monster classes are in [`src/unit/monster/`](../src/unit/monster/):

- `Attacker`
- `Defender`
- `Healer`
- `Hybrid`
- `Vampire`
- `Boss`

## Rewards

After winning a normal battle, the reward menu allows the player to upgrade exactly one dice value:

- **Upgrade MIN** increases the minimum roll value.
- **Upgrade MAX** increases the maximum roll value.

Relevant file: [`src/gui/RewardMenu.java`](../src/gui/RewardMenu.java).

## Resting

On a rest node, the player rolls all dice and heals by the total rolled value. HP is capped by the player's maximum HP.

Relevant files:

- [`src/gui/RestMenu.java`](../src/gui/RestMenu.java)
- [`src/utils/GameLogic.java`](../src/utils/GameLogic.java)

## Shopping

On a shop node, the player rolls all dice. The sum of rolled values becomes temporary money for that shop visit only. The player can buy generated dice if they have enough money.

Relevant files:

- [`src/gui/ShopMenu.java`](../src/gui/ShopMenu.java)
- [`src/utils/ShopLogic.java`](../src/utils/ShopLogic.java)
- [`src/utils/DiceConfig.java`](../src/utils/DiceConfig.java)

## Win and lose conditions

- **Win:** defeat the boss at the `BossNode`.
- **Lose:** player HP becomes 0 during combat.
