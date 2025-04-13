package gui;

import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
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
				nodePane.add(node, i, j);
			}
		}
		nodePane.setAlignment(Pos.CENTER);
		this.getChildren().add(nodePane);
	}
	// TODO RewardMenu
	
	// TODO RestMenu
	
	// TODO ShopMenu
	
	// TODO WinMenu
	
	// TODO LoseMenu

}
