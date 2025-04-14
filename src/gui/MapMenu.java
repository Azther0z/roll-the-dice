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
		initializeNodePane();
		initializeEdgePane();
	}

	private void initializeNodePane() {
		nodePane = new Pane();
		for (int i = 0; i < GameConfig.MAX_ROW; i++) {
			for (int j = 0; j < GameConfig.MAX_COL; j++) {
				Node node = GameLogic.getInstance().getNodeGrid().get(i).get(j);
//				nodePane.add(node, j, i);
				node.setLayoutX(100 + j * 50);
				node.setLayoutY(100 + i * 50);
				nodePane.getChildren().add(node);
			}
		}
		Node bossNode = GameLogic.getInstance().getBossNode();
		bossNode.setLayoutX(100 + GameConfig.MAX_COL * 50);
		bossNode.setLayoutY(100 + GameConfig.MAX_ROW * 50);
		nodePane.getChildren().add(bossNode);
		this.getChildren().add(nodePane);
	}

	private void initializeEdgePane() {
		edgePane = new Pane();
		edgePane.setPickOnBounds(false);
		for (Edge edge : GameLogic.getInstance().getEdgeList()) {
			Line line = new Line(edge.getFrom().getLayoutX(), edge.getFrom().getLayoutY(), edge.getTo().getLayoutX(),
					edge.getTo().getLayoutY());
			line.setStroke(Color.GRAY);
			line.setStrokeWidth(2);
			edgePane.getChildren().add(line);
		}
		this.getChildren().add(edgePane);
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
