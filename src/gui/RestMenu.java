package gui;

import dice.Dice;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import utils.GameConfig;
import utils.GameLogic;
import utils.GuiUtils;

public class RestMenu extends VBox {
	private VBox dicePane;

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
			diceBox.setAlignment(Pos.CENTER);
			dicePane.getChildren().add(diceBox);
		}
		dicePane.getChildren().add(GuiUtils.createText("Total : " + GameLogic.getInstance().getPlayer().getHealVal(),
				GameConfig.FONT_SIZE_MEDIUM));
		dicePane.setAlignment(Pos.CENTER);
		this.getChildren().add(dicePane);
		this.getChildren().add(GuiUtils.createText("Heal : " + hp1 + " ->" + hp2, GameConfig.FONT_SIZE_MEDIUM));
		this.getChildren().add(SceneManager.getInstance().getMapMenu().createContinueButton());
		this.setAlignment(Pos.CENTER);
		this.setMaxSize(GameConfig.STACK_MAP_MENU_WIDTH,GameConfig.STACK_MAP_MENU_HEIGHT);
		this.setBackground(new Background(new BackgroundFill(Color.GRAY,CornerRadii.EMPTY,Insets.EMPTY)));
	}
}
