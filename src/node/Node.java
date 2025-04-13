package node;

import java.util.Objects;

import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import utils.GameLogic;

public abstract class Node extends Pane{
	private final int row;
	private final int col;

	public Node(int row, int col, String text) {
		super();
		this.row = row;
		this.col = col;
		this.getChildren().add(new Text(text));
		this.setOnMouseClicked(e ->{
			GameLogic.getInstance().setCurrentNode(this);
			executeNode();
		});
	}

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}

//	abstract public void displayNode();
	// TODO wtf is this method

	abstract public void executeNode();

}
