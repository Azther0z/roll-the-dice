package gui;

import dice.Dice;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import utils.GameLogic;

public class RestMenu extends HBox {
	private VBox dicePane;
	private Text healText;

	public RestMenu() {
		int hp1 = GameLogic.getInstance().getPlayer().getHp();
		GameLogic.getInstance().rest();
		int hp2 = GameLogic.getInstance().getPlayer().getHp();
		dicePane = new VBox();
		for (Dice dice : GameLogic.getInstance().getPlayer().getDiceList()) {
			HBox diceBox = new HBox();
			diceBox.getChildren()
					.add(new Text("[" + dice.getMinVal() + ", " + dice.getMaxVal() + "] : " + dice.getRollVal()));
			diceBox.getChildren().add(new Text("" + dice.getImagePath()));
			dicePane.getChildren().add(diceBox);
		}
		dicePane.getChildren().add(new Text("Total : " + GameLogic.getInstance().getPlayer().getHealVal()));
		this.getChildren().add(dicePane);
		healText = new Text("Heal : " + hp1 + " ->" + hp2);
		this.getChildren().add(healText);
		this.getChildren().add(SceneManager.getInstance().getMapMenu().createContinueButton());
	}
}
