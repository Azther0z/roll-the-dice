package gui;

import dice.Dice;
import dice.DivideDice;
import dice.MultiplyDice;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
import utils.GuiUtils;

public class RestMenu extends GridPane {

	public RestMenu() {
		int hp1 = GameLogic.getInstance().getPlayer().getHp();
		GameLogic.getInstance().rest();
		int hp2 = GameLogic.getInstance().getPlayer().getHp();
		this.add(new Text("DICE"), 1, 0);
		int row = 1;
		for (Dice dice : GameLogic.getInstance().getPlayer().getDiceList()) {
			if (dice instanceof DivideDice) {
				this.add(new Text("DIVIDE"), 0, row);
			} else if (dice instanceof MultiplyDice) {
				this.add(new Text("MULTIPLY"), 0, row);
			} else {
				this.add(new Text("NORMAL"), 0, row);
			}
			this.add(new Text("[" + dice.getMinVal() + ", " + dice.getMaxVal() + "]: "), 1, row);
			this.add(new Text("" + dice.getRollVal()), 2, row);
			row++;
		}
		this.setAlignment(Pos.CENTER);
		this.setHgap(GameConfig.FIGHT_SCENE_DICE_HGAP);
		this.setVgap(GameConfig.FIGHT_SCENE_DICE_HGAP);
		this.add(new Text("Before"), 1, row + 1);
		this.add(new Text("After"), 2, row + 1);
		this.add(new Text("HP : "), 0, row + 2);
		this.add(new Text("" + hp1), 1, row + 2);
		this.add(new Text("" + hp2), 2, row + 2);
		this.add(SceneManager.getInstance().getMapMenu().createContinueButton(), 1, row + 3);
		this.setMaxSize(GameConfig.STACK_MAP_MENU_WIDTH, GameConfig.STACK_MAP_MENU_HEIGHT);
		this.setBackground(new Background(new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, Insets.EMPTY)));
	}
}
