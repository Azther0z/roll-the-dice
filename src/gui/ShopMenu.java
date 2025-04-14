package gui;

import dice.Dice;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import utils.GameLogic;
import utils.ShopItem;
import utils.ShopLogic;

public class ShopMenu extends HBox {
	private VBox dicePane;
	private Text moneyText;
	private VBox shopPane;
	private ShopLogic shopLogic;

	public ShopMenu() {
		shopLogic = new ShopLogic();
		initializeDicePane();
		initializeShopPane();
		this.getChildren().addAll(dicePane, shopPane);
		this.getChildren().add(SceneManager.getInstance().getMapMenu().createContinueButton());
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
			dicePane.getChildren().add(diceBox);
		}
		moneyText = new Text();
		dicePane.getChildren().add(moneyText);
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
			shopPane.getChildren().add(itemBox);
		}
	}

}
