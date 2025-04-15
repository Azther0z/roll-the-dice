package gui;

import dice.Dice;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
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
import utils.ShopItem;
import utils.ShopLogic;

public class ShopMenu extends VBox {
	private VBox dicePane;
	private Text moneyText;
	private VBox shopPane;
	private HBox shopBox;
	private ShopLogic shopLogic;

	public ShopMenu() {
		shopBox = new HBox();
		shopLogic = new ShopLogic();
		initializeDicePane();
		initializeShopPane();
		shopBox.getChildren().addAll(dicePane, shopPane);
		shopBox.setAlignment(Pos.CENTER);
		this.getChildren().add(shopBox);
		this.getChildren().add(SceneManager.getInstance().getMapMenu().createContinueButton());
		this.setAlignment(Pos.CENTER);
		this.setMaxSize(GameConfig.STACK_MAP_MENU_WIDTH,GameConfig.STACK_MAP_MENU_HEIGHT);
		this.setBackground(new Background(new BackgroundFill(Color.GRAY,CornerRadii.EMPTY,Insets.EMPTY)));
	}

	private void updateMoney() {
		moneyText.setText("Money : " + shopLogic.getMoney());
	}

	private void initializeDicePane() {
		dicePane = new VBox();
		for (Dice dice : GameLogic.getInstance().getPlayer().getDiceList()) {
			HBox diceBox = new HBox();
			diceBox.getChildren()
					.add(new Text("[" + dice.getMinVal() + ", " + dice.getMaxVal() + "] : " + dice.getRollVal()));
			diceBox.getChildren().add(new Text("" + dice.getImagePath()));
			diceBox.setAlignment(Pos.CENTER);
			dicePane.getChildren().add(diceBox);
		}
		moneyText = GuiUtils.createText("", GameConfig.FONT_SIZE_MEDIUM);
		dicePane.getChildren().add(moneyText);
		dicePane.setAlignment(Pos.CENTER);
		updateMoney();
	}

	private void initializeShopPane() {
		shopPane = new VBox();
		for (ShopItem item : shopLogic.getShopList()) {
			HBox itemBox = new HBox();
			itemBox.getChildren()
					.add(new Text("[" + item.getDice().getMinVal() + ", " + item.getDice().getMaxVal() + "]"));
			itemBox.getChildren().add(new Text(item.getDice().getImagePath()));
			itemBox.getChildren().add(new Text("" + item.getCost()));
			Button buyButton = new Button("Buy");
			buyButton.setOnMouseClicked(e -> {
				boolean buySuccess = shopLogic.buyDice(item);
				if (buySuccess) {
					itemBox.getChildren().add(new Text("Bought"));
					updateMoney();
				}
			});
			itemBox.getChildren().add(buyButton);
			itemBox.setAlignment(Pos.CENTER);
			shopPane.getChildren().add(itemBox);
		}
		shopPane.setAlignment(Pos.CENTER);
	}

}
