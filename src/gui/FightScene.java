package gui;

import dice.Dice;
import dice.DivideDice;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import unit.base.Attackable;
import unit.base.BaseUnit;
import unit.base.Defendable;
import unit.base.Healable;
import utils.FightLogic;
import utils.GameLogic;

public class FightScene extends GridPane {
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
		endTurnButton.setAlignment(Pos.CENTER);
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
		this.add(endTurnButton, 3, 0);
	}

	private void initializePlayerPane() {
		playerPane = new VBox();
		playerPane.setAlignment(Pos.CENTER);
		updatePlayerPane();
	}

	private void initializeDicePane() {
		dicePane = new VBox();
		dicePane.setAlignment(Pos.CENTER);
		updateDicePane();
	}

	private void initializeEnemyPane() {
		enemiesPane = new HBox();
		enemiesPane.setAlignment(Pos.CENTER);
		updateEnemiesPane();
	}

	private void updatePlayerPane() {
		playerPane.getChildren().clear();
		playerPane.getChildren().add(new Text(GameLogic.getInstance().getPlayer().getName()));
		playerPane.getChildren().add(new Text("HP: " + GameLogic.getInstance().getPlayer().getHp()));
		playerPane.getChildren().add(new Text("ATK: " + GameLogic.getInstance().getPlayer().getAtkVal()));
		playerPane.getChildren().add(new Text("DEF: " + GameLogic.getInstance().getPlayer().getDefVal()));
	}

	private void updateDicePane() {
		dicePane.getChildren().clear();
		for (Dice dice : GameLogic.getInstance().getPlayer().getDiceList()) {
			if(dice instanceof DivideDice) {
				continue;
			}
			HBox diceBox = new HBox();
			diceBox.getChildren()
					.add(new Text("[" + dice.getMinVal() + ", " + dice.getMaxVal() + "] : " + dice.getRollVal()));
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
				enemyPane.getChildren().add(new Text("ATK: " + ((Attackable) enemy).getAtkVal()));
			}
			if (enemy instanceof Defendable) {
				enemyPane.getChildren().add(new Text("DEF: " + ((Defendable) enemy).getDefVal()));
			}
			if (enemy instanceof Healable) {
				enemyPane.getChildren().add(new Text("HEAL: " + ((Healable) enemy).getHealVal()));
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
		Text atkText = new Text("Attack : " + GameLogic.getInstance().getPlayer().getAtkVal());
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
		Text divText = new Text("Divide : " + divDice.getRollVal());
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
