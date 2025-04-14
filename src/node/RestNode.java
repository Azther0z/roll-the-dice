package node;

import gui.SceneManager;

public class RestNode extends Node {

	public RestNode(int row, int col) {
		super(row, col, "Rest");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void executeNode() {
		SceneManager.getInstance().getMapMenu().initializeRestMenu();
	}

}
