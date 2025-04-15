package gui;

import dice.Dice;
import javafx.collections.ObservableList;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import node.Edge;
import node.Node;
import utils.GameConfig;
import utils.GameLogic;

public class MapMenu extends StackPane {
	private Pane nodePane;
	private Pane edgePane;

	public MapMenu() {
		nodePane = new Pane();
		this.getChildren().add(nodePane);
		edgePane = new Pane();
		this.getChildren().add(edgePane);
		updateMap();
	}
	
	public void updateMap() {
		initializeNodePane();
		initializeEdgePane();
	}

	private void initializeNodePane() {
		nodePane.getChildren().clear();
		for (int i = 0; i < GameConfig.MAX_ROW; i++) {
			for (int j = 0; j < GameConfig.MAX_COL; j++) {
				Node node = GameLogic.getInstance().getNodeGrid().get(i).get(j);
				node.setLayoutX(100 + j * 50);
				node.setLayoutY(100 + i * 50);
				node.updateStatus();
				nodePane.getChildren().add(node);
			}
		}
		Node bossNode = GameLogic.getInstance().getBossNode();
		bossNode.setLayoutX(100 + GameConfig.MAX_COL * 50);
		bossNode.setLayoutY(100 + GameConfig.MAX_ROW * 50);
		bossNode.updateStatus();
		nodePane.getChildren().add(bossNode);
	}

	private void initializeEdgePane() {
		edgePane.getChildren().clear();
		edgePane.setPickOnBounds(false);
		for (Edge edge : GameLogic.getInstance().getEdgeList()) {
			Line line = new Line(edge.getFrom().getLayoutX(), edge.getFrom().getLayoutY(), edge.getTo().getLayoutX(),
					edge.getTo().getLayoutY());
			line.setStroke(Color.GRAY);
			line.setStrokeWidth(2);
			edgePane.getChildren().add(line);
		}
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

	public void initializeWinMenu() {
		this.getChildren().add(new WinMenu());
	}

	public void initializeLoseMenu() {
		this.getChildren().add(new LoseMenu());
	}

}
