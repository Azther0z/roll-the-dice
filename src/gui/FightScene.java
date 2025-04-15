package gui;

import dice.Dice;
import dice.DivideDice;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import unit.base.Attackable;
import unit.base.BaseUnit;
import unit.base.Defendable;
import unit.base.Healable;
import utils.FightLogic;
import utils.GameConfig;
import utils.GameLogic;
import utils.GuiUtils;

public class FightScene extends Pane {
	private VBox playerPane;
	private VBox dicePane;
	private HBox enemiesPane;
	private Button endTurnButton;

	public FightScene() {
		FightLogic.getInstance().newFightLogic();
		initializePlayerPane();
		initializeDicePane();
		initializeEnemyPane();
		endTurnButton = new Button("End turn");
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
		this.getChildren().addAll(playerPane, dicePane, enemiesPane, endTurnButton);
	}

	private void initializePlayerPane() {
		playerPane = new VBox();
		playerPane.setAlignment(Pos.CENTER);
		GuiUtils.setLayout(playerPane, GameConfig.FIGHT_SCENE_PLAYER_X, GameConfig.FIGHT_SCENE_PLAYER_Y);
		playerPane.setAlignment(Pos.CENTER_LEFT);
		updatePlayerPane();
	}

	private void initializeDicePane() {
		dicePane = new VBox();
		dicePane.setAlignment(Pos.CENTER);
		GuiUtils.setLayout(dicePane,GameConfig.FIGHT_SCENE_DICE_X,GameConfig.FIGHT_SCENE_DICE_Y);
		updateDicePane();
	}

	private void initializeEnemyPane() {
		enemiesPane = new HBox();
		enemiesPane.setAlignment(Pos.CENTER);
		GuiUtils.setLayout(enemiesPane,GameConfig.FIGHT_SCENE_ENEMY_X,GameConfig.FIGHT_SCENE_ENEMY_Y);
		updateEnemiesPane();
	}

	private void updatePlayerPane() {
		playerPane.getChildren().clear();
		playerPane.getChildren().add(new Text(GameLogic.getInstance().getPlayer().getName()));
		playerPane.getChildren().add(GuiUtils.createText("HP : " + GameLogic.getInstance().getPlayer().getHp(),
				GameConfig.FONT_SIZE_MEDIUM));
		playerPane.getChildren().add(GuiUtils.createText("ATK : " + GameLogic.getInstance().getPlayer().getAtkVal(),
				GameConfig.FONT_SIZE_MEDIUM));
		playerPane.getChildren().add(GuiUtils.createText("DEF : " + GameLogic.getInstance().getPlayer().getDefVal(),
				GameConfig.FONT_SIZE_MEDIUM));
	}

	private void updateDicePane() {
		dicePane.getChildren().clear();
		for (Dice dice : GameLogic.getInstance().getPlayer().getDiceList()) {
			if (dice instanceof DivideDice) {
				continue;
			}
			HBox diceBox = new HBox();
			diceBox.getChildren()
					.add(GuiUtils.createText(
							"[" + dice.getMinVal() + ", " + dice.getMaxVal() + "] : " + dice.getRollVal(),
							GameConfig.FONT_SIZE_MEDIUM));
			diceBox.getChildren().add(new Text("" + dice.getActionType()));
			diceBox.getChildren().add(new Text("" + dice.getImagePath()));
			diceBox.setOnMouseClicked(e -> {
				dice.switchAction();
				updateFightScene();
			});
			dicePane.getChildren().add(diceBox);
		}
	}

	private void updateEnemiesPane() {
		enemiesPane.getChildren().clear();
		for (BaseUnit enemy : FightLogic.getInstance().getEnemyList()) {
			VBox enemyPane = new VBox();
			enemyPane.setAlignment(Pos.CENTER);
			enemyPane.getChildren().add(new Text(enemy.getName()));
			enemyPane.getChildren().add(new Text("HP: " + enemy.getHp()));
			if (enemy instanceof Attackable) {
				enemyPane.getChildren().add(
						GuiUtils.createText("ATK: " + ((Attackable) enemy).getAtkVal(), GameConfig.FONT_SIZE_MEDIUM));
			}
			if (enemy instanceof Defendable) {
				enemyPane.getChildren().add(
						GuiUtils.createText("DEF: " + ((Defendable) enemy).getDefVal(), GameConfig.FONT_SIZE_MEDIUM));
			}
			if (enemy instanceof Healable) {
				enemyPane.getChildren().add(
						GuiUtils.createText("HEAL: " + ((Healable) enemy).getHealVal(), GameConfig.FONT_SIZE_MEDIUM));
			}
			enemyPane.getChildren().add(createAtkTargetButton(enemy));
			for (DivideDice divDice : GameLogic.getInstance().getPlayer().getDivDiceList()) {
				enemyPane.getChildren().add(createDivTargetButton(divDice, enemy));
			}
			enemiesPane.getChildren().add(enemyPane);
		}
	}

	private VBox createAtkTargetButton(BaseUnit enemy) {
		VBox atkTargetButton = new VBox();
		Text atkText = GuiUtils.createText("Attack : " + GameLogic.getInstance().getPlayer().getAtkVal(),
				GameConfig.FONT_SIZE_SMALL);
		atkTargetButton.setOnMouseClicked(e -> {
			GameLogic.getInstance().getPlayer().setAtkTarget(enemy);
			updateFightScene();

		});
		atkTargetButton.getChildren().add(atkText);
		if (enemy.equals(GameLogic.getInstance().getPlayer().getAtkTarget())) {
			atkTargetButton.getChildren().add(new Text("is target"));
		}
		return atkTargetButton;
	}

	private VBox createDivTargetButton(DivideDice divDice, BaseUnit enemy) {
		VBox divTargetButton = new VBox();
		Text divText = GuiUtils.createText("Divide : " + divDice.getRollVal(), GameConfig.FONT_SIZE_SMALL);
		divTargetButton.setOnMouseClicked(e -> {
			divDice.setDivTarget(enemy);
			updateFightScene();
		});
		divTargetButton.getChildren().add(divText);
		if (divDice.getDivTarget().equals(enemy)) {
			divTargetButton.getChildren().add(new Text("is target"));
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
