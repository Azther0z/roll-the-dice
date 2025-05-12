package gui;

import dice.Dice;
import dice.DivideDice;
import dice.MultiplyDice;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import utils.GameConfig;
import utils.GameLogic;

public class RewardMenu extends GridPane {
	private boolean isUpgraded;

	public RewardMenu() {
		this.setUpgraded(false);
		this.setAlignment(Pos.CENTER);
		this.setHgap(GameConfig.FIGHT_SCENE_DICE_HGAP);
		this.setVgap(GameConfig.FIGHT_SCENE_DICE_HGAP);
		int row = 0;
		for (Dice dice : GameLogic.getInstance().getPlayer().getDiceList()) {
			if (dice instanceof DivideDice) {
				this.add(new Text("DIVIDE"), 0, row);
			} else if (dice instanceof MultiplyDice) {
				this.add(new Text("MULTIPLY"), 0, row);
			} else {
				this.add(new Text("NORMAL"), 0, row);
			}
			this.add(new Text("[" + dice.getMinVal() + ", " + dice.getMaxVal() + "]"), 1, row);
			Button upgradeMinButton = new Button("Upgrade MIN");
			upgradeMinButton.setOnMouseClicked(e -> {
				upgradeMin(dice);
			});
			Button upgradeMaxButton = new Button("Upgrade MAX");
			upgradeMaxButton.setOnMouseClicked(e -> {
				upgradeMax(dice);
			});
			this.add(upgradeMinButton, 2, row);
			this.add(upgradeMaxButton, 3, row);
			row++;
		}
		this.setMaxSize(GameConfig.STACK_MAP_MENU_WIDTH, GameConfig.STACK_MAP_MENU_HEIGHT);
		this.setBackground(new Background(new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, Insets.EMPTY)));
	}

	private void upgradeMin(Dice dice) {
		if (this.isUpgraded()) {
			return;
		}
		if (dice.getMinVal() < dice.getMaxVal()) {
			if (dice instanceof DivideDice) {
				this.add(new Text("DIVIDE"), 0, GameLogic.getInstance().getPlayer().getDiceList().size());
			} else if (dice instanceof MultiplyDice) {
				this.add(new Text("MULTIPLY"), 0, GameLogic.getInstance().getPlayer().getDiceList().size());
			} else {
				this.add(new Text("NORMAL"), 0, GameLogic.getInstance().getPlayer().getDiceList().size());
			}
			this.add(new Text("[" + (dice.getMinVal() + 1) + ", " + dice.getMaxVal() + "]"), 1,
					GameLogic.getInstance().getPlayer().getDiceList().size());
			this.add(new Text("MIN: " + dice.getMinVal() + " -> " + (dice.getMinVal() + 1)), 2,
					GameLogic.getInstance().getPlayer().getDiceList().size());
			this.add(new Text("MAX: " + dice.getMaxVal()), 3, GameLogic.getInstance().getPlayer().getDiceList().size());
			dice.setMinVal(dice.getMinVal() + 1);
			this.setUpgraded(true);
			this.add(new Text(""), 0, GameLogic.getInstance().getPlayer().getDiceList().size() + 1);
			this.add(new Text(""), 1, GameLogic.getInstance().getPlayer().getDiceList().size() + 1);
			this.add(new Text(""), 3, GameLogic.getInstance().getPlayer().getDiceList().size() + 1);
			this.add(SceneManager.getInstance().getMapMenu().createContinueButton(), 2,
					GameLogic.getInstance().getPlayer().getDiceList().size() + 1);
		}
	}

	private void upgradeMax(Dice dice) {
		if (this.isUpgraded()) {
			return;
		}
		if (dice instanceof DivideDice) {
			this.add(new Text("DIVIDE"), 0, GameLogic.getInstance().getPlayer().getDiceList().size());
		} else if (dice instanceof MultiplyDice) {
			this.add(new Text("MULTIPLY"), 0, GameLogic.getInstance().getPlayer().getDiceList().size());
		} else {
			this.add(new Text("NORMAL"), 0, GameLogic.getInstance().getPlayer().getDiceList().size());
		}
		this.add(new Text("[" + dice.getMinVal() + ", " + (dice.getMaxVal() + 1) + "]"), 1,
				GameLogic.getInstance().getPlayer().getDiceList().size());
		this.add(new Text("MIN: " + dice.getMinVal()), 2, GameLogic.getInstance().getPlayer().getDiceList().size());
		this.add(new Text("MAX: " + dice.getMaxVal() + " -> " + (dice.getMaxVal() + 1)), 3,
				GameLogic.getInstance().getPlayer().getDiceList().size());
		dice.setMaxVal(dice.getMaxVal() + 1);
		this.setUpgraded(true);
		this.add(new Text(""), 0, GameLogic.getInstance().getPlayer().getDiceList().size() + 1);
		this.add(new Text(""), 1, GameLogic.getInstance().getPlayer().getDiceList().size() + 1);
		this.add(new Text(""), 3, GameLogic.getInstance().getPlayer().getDiceList().size() + 1);
		this.add(SceneManager.getInstance().getMapMenu().createContinueButton(), 2,
				GameLogic.getInstance().getPlayer().getDiceList().size() + 1);
	}

	public boolean isUpgraded() {
		return isUpgraded;
	}

	public void setUpgraded(boolean isUpgraded) {
		this.isUpgraded = isUpgraded;
	}
}
