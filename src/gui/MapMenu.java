package gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import node.Edge;
import node.Node;
import utils.GameConfig;
import utils.GameLogic;
import utils.GuiUtils;

public class MapMenu extends StackPane {
	private Pane nodePane;
	private Pane edgePane;

	public MapMenu() {
		edgePane = new Pane();
		this.getChildren().add(edgePane);
		nodePane = new Pane();
		this.getChildren().add(nodePane);
		updateMap();
		this.setPadding(new Insets(GameConfig.MAP_MENU_VPADDING, GameConfig.MAP_MENU_HPADDING,
				GameConfig.MAP_MENU_VPADDING, GameConfig.MAP_MENU_HPADDING));
	}

	public void updateMap() {
		initializeNodePane();
		initializeEdgePane();
	}

	private void initializeNodePane() {
		nodePane.getChildren().clear();
		double totalGridWidth = GameConfig.MAX_COL * GameConfig.MAP_MENU_NODE_WIDTH
				+ (GameConfig.MAX_COL - 1) * GameConfig.MAP_MENU_NODE_HGAP;
		double totalGridHeight = GameConfig.MAX_ROW * GameConfig.MAP_MENU_NODE_HEIGHT
				+ (GameConfig.MAX_ROW - 1) * GameConfig.MAP_MENU_NODE_VGAP;
		double startX = (GameConfig.SCREEN_WIDTH - totalGridWidth) / 2;
		double startY = 0;
		for (int i = 0; i < GameConfig.MAX_ROW; i++) {
			for (int j = 0; j < GameConfig.MAX_COL; j++) {
				Node node = GameLogic.getInstance().getNodeGrid().get(i).get(j);
				double xPos = startX + j * (GameConfig.MAP_MENU_NODE_WIDTH + GameConfig.MAP_MENU_NODE_HGAP);
				double yPos = startY + i * (GameConfig.MAP_MENU_NODE_HEIGHT + GameConfig.MAP_MENU_NODE_VGAP);
				node.setPrefSize(GameConfig.MAP_MENU_NODE_WIDTH, GameConfig.MAP_MENU_NODE_HEIGHT);
				GuiUtils.setLayout(node, xPos, yPos);
				node.setBackground(new Background(new BackgroundFill(Color.BEIGE, CornerRadii.EMPTY, Insets.EMPTY)));
				node.updateStatus();
				nodePane.getChildren().add(node);
			}
		}
		Node bossNode = GameLogic.getInstance().getBossNode();
		GuiUtils.setLayout(bossNode, (GameConfig.SCREEN_WIDTH - GameConfig.MAP_MENU_BOSS_WIDTH) / 2,
				startY + totalGridHeight + GameConfig.MAP_MENU_BOSS_VGAP);
		bossNode.setBackground(new Background(new BackgroundFill(Color.BEIGE, CornerRadii.EMPTY, Insets.EMPTY)));
		bossNode.setPrefSize(GameConfig.MAP_MENU_BOSS_WIDTH, GameConfig.MAP_MENU_BOSS_HEIGHT);
		bossNode.updateStatus();
		nodePane.getChildren().add(bossNode);
	}

	private void initializeEdgePane() {
		edgePane.getChildren().clear();
		edgePane.setPickOnBounds(false);
		for (Edge edge : GameLogic.getInstance().getEdgeList()) {
			Line line = new Line(edge.getFrom().getLayoutX() + edge.getFrom().getPrefWidth() / 2,
					edge.getFrom().getLayoutY() + edge.getFrom().getPrefHeight() / 2,
					edge.getTo().getLayoutX() + edge.getTo().getPrefWidth() / 2,
					edge.getTo().getLayoutY() + edge.getTo().getPrefHeight() / 2);
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
