package node;

import gui.SceneManager;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import utils.GameLogic;

public abstract class Node extends VBox {
	private final int row;
	private final int col;
	private Text status;

	public Node(int row, int col, String text) {
		super();
		this.row = row;
		this.col = col;
		this.getChildren().add(new Text(text));
		this.status = new Text();
		this.getChildren().add(status);
		this.setAlignment(Pos.CENTER);
		this.setOnMouseClicked(e -> {
			if (!validNodeChosen()) {
				return;
			}
			GameLogic.getInstance().setCurrentNode(this);
			this.updateStatus();
			executeNode();
			SceneManager.getInstance().getMapMenu().updateMap();
		});
	}

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}

	public void updateStatus() {
		if (!this.status.getText().equals("Selected")) {
			this.status.setText("");
		}
		if (this.equals(GameLogic.getInstance().getCurrentNode())) {
			this.status.setText("Selected");
			this.status.setFill(Color.GRAY);
		}
		if (validNodeChosen()) {
			this.status.setText("Available");
			this.status.setFill(Color.GREEN);
		}
	}

	public boolean validNodeChosen() {
		if (GameLogic.getInstance().getCurrentNode() == null && this.row == 0) {
			return true;
		}
		for (Edge edge : GameLogic.getInstance().getEdgeList()) {
			if (edge.getFrom().equals(GameLogic.getInstance().getCurrentNode()) && edge.getTo().equals(this)) {
				return true;
			}
		}
		return false;
	}

	abstract public void executeNode();

}
