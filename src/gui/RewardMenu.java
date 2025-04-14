package gui;

import dice.Dice;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import utils.GameLogic;

public class RewardMenu extends VBox {
	private boolean isUpgraded;

	public RewardMenu() {
		this.setUpgraded(false);
		for (Dice dice : GameLogic.getInstance().getPlayer().getDiceList()) {
			HBox diceBox = new HBox();
			diceBox.getChildren().add(new Text("[" + dice.getMinVal() + ", " + dice.getMaxVal() + "]"));
			diceBox.getChildren().add(new Text("" + dice.getImagePath()));
			Button upgradeMinButton = new Button("Upgrade min");
			upgradeMinButton.setOnMouseClicked(e -> {
				upgradeMin(dice);
			});
			Button upgradeMaxButton = new Button("Upgrade max");
			upgradeMaxButton.setOnMouseClicked(e -> {
				upgradeMax(dice);
			});
			diceBox.getChildren().add(upgradeMinButton);
			diceBox.getChildren().add(upgradeMaxButton);
			this.getChildren().add(diceBox);
		}
		this.getChildren().add(SceneManager.getInstance().getMapMenu().createContinueButton());
	}

	private void upgradeMin(Dice dice) {
		if (this.isUpgraded()) {
			return;
		}
		if (dice.getMinVal() < dice.getMaxVal()) {
			this.getChildren().add(new Text(dice.getMinVal() + " -> " + (dice.getMinVal() + 1)));
			dice.setMinVal(dice.getMinVal() + 1);
			this.setUpgraded(true);
		}
	}

	private void upgradeMax(Dice dice) {
		if (this.isUpgraded()) {
			return;
		}
		this.getChildren().add(new Text(dice.getMaxVal() + " -> " + (dice.getMaxVal() + 1)));
		dice.setMaxVal(dice.getMaxVal() + 1);
		this.setUpgraded(true);
	}

	public boolean isUpgraded() {
		return isUpgraded;
	}

	public void setUpgraded(boolean isUpgraded) {
		this.isUpgraded = isUpgraded;
	}
}
