package gui;

import dice.Dice;
import dice.DivideDice;
import dice.MultiplyDice;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import unit.base.Attackable;
import unit.base.BaseUnit;
import unit.base.Defendable;
import unit.base.Healable;
import unit.monster.Vampire;
import utils.FightLogic;
import utils.GameConfig;
import utils.GameLogic;
import utils.GuiUtils;

public class FightScene extends GridPane {
	private VBox playerPane;
	private GridPane dicePane;
	private HBox enemiesPane;
	private Button endTurnButton;

	public FightScene() {
		FightLogic.getInstance().newFightLogic();
		initializePlayerPane();
		initializeDicePane();
		initializeEnemyPane();
		endTurnButton = new Button("END TURN");
		GuiUtils.setLayout(endTurnButton, GameConfig.FIGHT_SCENE_ENDBUTTON_X, GameConfig.FIGHT_SCENE_ENDBUTTON_Y);
		endTurnButton.setPrefSize(GameConfig.FIGHT_SCENE_ENDBUTTON_WIDTH, GameConfig.FIGHT_SCENE_ENDBUTTON_HEIGHT);
		endTurnButton.setOnMouseClicked(e -> {
			FightLogic.getInstance().endTurn();
			updateFightScene();
			if (FightLogic.getInstance().isEnd()) {
				if (GameLogic.getInstance().isWin()) {
					SceneManager.getInstance().getMapMenu().initializeWinMenu();
				} else {
					SceneManager.getInstance().getMapMenu().initializeRewardMenu();
				}
				SceneManager.getInstance().goToMap();
			}
			if (GameLogic.getInstance().isDefeated()) {
				SceneManager.getInstance().getMapMenu().initializeLoseMenu();
				SceneManager.getInstance().goToMap();
			}
		});
		this.add(playerPane, 0, 0);
		this.add(dicePane, 1, 0);
		this.add(enemiesPane, 2, 0);
		this.add(endTurnButton, 1, 1);
		this.setAlignment(Pos.CENTER);
		this.setHgap(GameConfig.FIGHT_SCENE_HGAP);
		this.setVgap(GameConfig.FIGHT_SCENE_VGAP);
	}

	private void initializePlayerPane() {
		playerPane = new VBox();
		playerPane.setAlignment(Pos.CENTER);
		updatePlayerPane();
	}

	private void initializeDicePane() {
		dicePane = new GridPane();
		dicePane.setAlignment(Pos.CENTER);
		dicePane.setHgap(GameConfig.FIGHT_SCENE_DICE_HGAP);
		updateDicePane();
	}

	private void initializeEnemyPane() {
		enemiesPane = new HBox();
		enemiesPane.setAlignment(Pos.CENTER);
		updateEnemiesPane();
	}

	private void updatePlayerPane() {
		playerPane.getChildren().clear();
		playerPane.getChildren().add(GuiUtils.createText("STATUS", GameConfig.FONT_SIZE_SMALL));
		playerPane.getChildren().add(
				GuiUtils.createText("HP : " + GameLogic.getInstance().getPlayer().getHp(), GameConfig.FONT_SIZE_SMALL));
		playerPane.getChildren().add(GuiUtils.createText("ATK : " + GameLogic.getInstance().getPlayer().getAtkVal(),
				GameConfig.FONT_SIZE_SMALL));
		playerPane.getChildren().add(GuiUtils.createText("DEF : " + GameLogic.getInstance().getPlayer().getDefVal(),
				GameConfig.FONT_SIZE_SMALL));
	}

	private void updateDicePane() {
		dicePane.getChildren().clear();
		int row = 0;
		for (Dice dice : GameLogic.getInstance().getPlayer().getDiceList()) {
			if (dice instanceof DivideDice) {
				dicePane.add(GuiUtils.createText("DIVIDE", GameConfig.FONT_SIZE_SMALL), 0, row);
				dicePane.add(GuiUtils.createText(
						"[" + dice.getMinVal() + ", " + dice.getMaxVal() + "] : " + dice.getRollVal(),
						GameConfig.FONT_SIZE_SMALL), 1, row);
			} else if (dice instanceof MultiplyDice) {
				dicePane.add(GuiUtils.createText("MULTIPLY", GameConfig.FONT_SIZE_SMALL), 0, row);
				dicePane.add(GuiUtils.createText(
						"[" + dice.getMinVal() + ", " + dice.getMaxVal() + "] : " + dice.getRollVal(),
						GameConfig.FONT_SIZE_SMALL), 1, row);
				Text diceAction = GuiUtils.createText("" + dice.getActionType(), GameConfig.FONT_SIZE_SMALL);
				diceAction.setOnMouseClicked(e -> {
					dice.switchAction();
					updateFightScene();
				});
				dicePane.add(diceAction, 2, row);
			} else {
				dicePane.add(GuiUtils.createText("NORMAL", GameConfig.FONT_SIZE_SMALL), 0, row);
				dicePane.add(GuiUtils.createText(
						"[" + dice.getMinVal() + ", " + dice.getMaxVal() + "] : " + dice.getRollVal(),
						GameConfig.FONT_SIZE_SMALL), 1, row);
				Text diceAction = GuiUtils.createText("" + dice.getActionType(), GameConfig.FONT_SIZE_SMALL);
				diceAction.setOnMouseClicked(e -> {
					dice.switchAction();
					updateFightScene();
				});
				dicePane.add(diceAction, 2, row);
			}
			row++;
		}
	}

	private void updateEnemiesPane() {
		enemiesPane.getChildren().clear();
		for (BaseUnit enemy : FightLogic.getInstance().getEnemyList()) {
			VBox enemyPane = new VBox();
			enemyPane.setAlignment(Pos.CENTER);
			enemyPane.getChildren().add(GuiUtils.createText(enemy.getName(), GameConfig.FONT_SIZE_SMALL));
			enemyPane.getChildren().add(GuiUtils.createText("HP: " + enemy.getHp(), GameConfig.FONT_SIZE_SMALL));
			if (enemy instanceof Attackable) {
				enemyPane.getChildren().add(
						GuiUtils.createText("ATK: " + ((Attackable) enemy).getAtkVal(), GameConfig.FONT_SIZE_SMALL));
			} else {
				enemyPane.getChildren().add(GuiUtils.createText("ATK: " + 0, GameConfig.FONT_SIZE_SMALL));
			}
			if (enemy instanceof Defendable) {
				enemyPane.getChildren().add(
						GuiUtils.createText("DEF: " + ((Defendable) enemy).getDefVal(), GameConfig.FONT_SIZE_SMALL));
			} else {
				enemyPane.getChildren().add(GuiUtils.createText("DEF: " + 0, GameConfig.FONT_SIZE_SMALL));
			}
			if (enemy instanceof Healable) {
				if (enemy instanceof Vampire) {
					enemyPane.getChildren().add(GuiUtils.createText("HEAL: ?", GameConfig.FONT_SIZE_SMALL));
				} else {
					enemyPane.getChildren().add(GuiUtils.createText("HEAL: " + ((Healable) enemy).getHealVal(),
							GameConfig.FONT_SIZE_SMALL));
				}
			} else {
				enemyPane.getChildren().add(GuiUtils.createText("HEAL: " + 0, GameConfig.FONT_SIZE_SMALL));
			}
			enemyPane.getChildren().add(new Text(""));
			enemyPane.getChildren().add(createAtkTargetButton(enemy));
			for (DivideDice divDice : GameLogic.getInstance().getPlayer().getDivDiceList()) {
				enemyPane.getChildren().add(createDivTargetButton(divDice, enemy));
			}
			enemyPane.setPadding(new Insets(GameConfig.FIGHT_SCENE_ENEMY_PADDING));
			enemiesPane.getChildren().add(enemyPane);
		}
	}

	private VBox createAtkTargetButton(BaseUnit enemy) {
		VBox atkTargetButton = new VBox();
		atkTargetButton.setAlignment(Pos.CENTER);
		Text atkText = GuiUtils.createText("ATTACK", GameConfig.FONT_SIZE_SMALL);
		atkTargetButton.setOnMouseClicked(e -> {
			GameLogic.getInstance().getPlayer().setAtkTarget(enemy);
			updateFightScene();

		});
		atkTargetButton.getChildren().add(atkText);
		if (enemy.equals(GameLogic.getInstance().getPlayer().getAtkTarget())) {
			atkTargetButton.getChildren().add(new Text("✔"));
		} else {
			atkTargetButton.getChildren().add(new Text(""));
		}

		return atkTargetButton;
	}

	private VBox createDivTargetButton(DivideDice divDice, BaseUnit enemy) {
		VBox divTargetButton = new VBox();
		divTargetButton.setAlignment(Pos.CENTER);
		Text divText = GuiUtils.createText("DIVIDE BY " + divDice.getRollVal(), GameConfig.FONT_SIZE_SMALL);
		divTargetButton.setOnMouseClicked(e -> {
			divDice.setDivTarget(enemy);
			updateFightScene();
		});
		divTargetButton.getChildren().add(divText);
		if (divDice.getDivTarget().equals(enemy)) {
			divTargetButton.getChildren().add(new Text("✔"));
		} else {
			divTargetButton.getChildren().add(new Text(""));
		}
		return divTargetButton;
	}

	public void updateFightScene() {
		FightLogic.getInstance().updateTurn();
		updateDicePane();
		updatePlayerPane();
		updateEnemiesPane();
	}
}
