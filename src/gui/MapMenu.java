package gui;

import dice.Dice;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import node.Node;
import utils.GameConfig;
import utils.GameLogic;

public class MapMenu extends StackPane {
	private GridPane nodePane;

	public MapMenu() {
		nodePane = new GridPane();
		for (int i = 0; i < GameConfig.MAX_ROW; i++) {
			for (int j = 0; j < GameConfig.MAX_COL; j++) {
				Node node = GameLogic.getInstance().getNodeGrid().get(i).get(j);
				nodePane.add(node, j, i);
			}
		}
		nodePane.setAlignment(Pos.CENTER);
		this.getChildren().add(nodePane);
	}

	public Button createContinueButton() {
		Button continueButton = new Button("Continue");
		continueButton.setOnMouseClicked(e -> {
			this.getChildren().removeLast();
		});
		return continueButton;
	}

	public void initializeRewardMenu() {
		this.getChildren().add(new RewardMenu());
	}

	public void initializeRestMenu() {
		this.getChildren().add(new RestMenu());
	}

	public void initializeShopMenu() {
		this.getChildren().add(new ShopMenu());
	}

	// TODO WinMenu

	// TODO LoseMenu

}
