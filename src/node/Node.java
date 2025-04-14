package node;

import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import utils.GameLogic;

public abstract class Node extends VBox {
	private final int row;
	private final int col;

	public Node(int row, int col, String text) {
		super();
		this.row = row;
		this.col = col;
		this.getChildren().add(new Text(text));
		this.setOnMouseClicked(e -> {
			if (!validNodeChosen()) {
				return;
			}
			GameLogic.getInstance().setCurrentNode(this);
			this.getChildren().add(new Text("Selected"));
			executeNode();
		});
	}

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}

	private boolean validNodeChosen() {
		if (GameLogic.getInstance().getCurrentNode() == null && this.row==0) {
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
