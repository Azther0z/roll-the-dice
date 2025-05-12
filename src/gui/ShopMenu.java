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
import utils.GuiUtils;
import utils.ShopItem;
import utils.ShopLogic;

public class ShopMenu extends VBox {
	private HBox pane;
	private GridPane dicePane;
	private Text moneyText;
	private GridPane shopPane;
	private ShopLogic shopLogic;

	public ShopMenu() {
		pane = new HBox(GameConfig.SHOP_MENU_PADDING);
		shopLogic = new ShopLogic();
		initializeDicePane();
		initializeShopPane();
		pane.getChildren().addAll(dicePane, shopPane);
		pane.setAlignment(Pos.CENTER);
		this.getChildren().add(pane);
		this.getChildren().add(new Text(""));
		this.getChildren().add(SceneManager.getInstance().getMapMenu().createContinueButton());
		this.setAlignment(Pos.CENTER);
		this.setMaxSize(GameConfig.STACK_MAP_MENU_WIDTH, GameConfig.STACK_MAP_MENU_HEIGHT);
		this.setBackground(new Background(new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, Insets.EMPTY)));
	}

	private void initializeDicePane() {
		dicePane = new GridPane();
		dicePane.add(new Text("DICE"), 1, 0);
		int row = 1;
		for (Dice dice : GameLogic.getInstance().getPlayer().getDiceList()) {
			if (dice instanceof DivideDice) {
				dicePane.add(new Text("DIVIDE"), 0, row);
			} else if (dice instanceof MultiplyDice) {
				dicePane.add(new Text("MULTIPLY"), 0, row);
			} else {
				dicePane.add(new Text("NORMAL"), 0, row);
			}
			dicePane.add(new Text("[" + dice.getMinVal() + ", " + dice.getMaxVal() + "]: "), 1, row);
			dicePane.add(new Text("" + dice.getRollVal()), 2, row);
			row++;
		}
		dicePane.add(new Text("Money: "), 0, row + 1);
		moneyText = new Text("");
		dicePane.add(moneyText, 1, row + 1);
		updateMoney();
		dicePane.setAlignment(Pos.CENTER);
		dicePane.setHgap(GameConfig.FIGHT_SCENE_DICE_HGAP);
		dicePane.setVgap(GameConfig.FIGHT_SCENE_DICE_HGAP);
	}

	private void updateMoney() {
		moneyText.setText("" + shopLogic.getMoney());
	}

	private void initializeShopPane() {
		shopPane = new GridPane();
		shopPane.add(new Text(""), 0, 0);
		shopPane.add(new Text(""), 1, 0);
		shopPane.add(new Text("SHOP"), 2, 0);
		shopPane.add(new Text(""), 3, 0);
		shopPane.add(new Text(""), 4, 0);
		int row = 1;
		for (ShopItem item : shopLogic.getShopList()) {
			if (item.getDice() instanceof DivideDice) {
				shopPane.add(new Text("DIVIDE"), 0, row);
			} else if (item.getDice() instanceof MultiplyDice) {
				shopPane.add(new Text("MULTIPLY"), 0, row);
			} else {
				shopPane.add(new Text("NORMAL"), 0, row);
			}
			shopPane.add(new Text("[" + item.getDice().getMinVal() + ", " + item.getDice().getMaxVal() + "]"), 1, row);
			shopPane.add(new Text("Cost: " + item.getCost()), 2, row);
			Button buyButton = new Button("Buy");
			final int finalRow = row;
			buyButton.setOnMouseClicked(e -> {
				boolean buySuccess = shopLogic.buyDice(item);
				if (buySuccess) {
					shopPane.add(new Text("Bought"), 4, finalRow);
					updateMoney();
				}
			});
			shopPane.add(buyButton, 3, row);
			row++;
		}
		shopPane.setAlignment(Pos.CENTER);
		shopPane.setHgap(GameConfig.FIGHT_SCENE_DICE_HGAP);
		shopPane.setVgap(GameConfig.FIGHT_SCENE_DICE_HGAP);
	}

}
