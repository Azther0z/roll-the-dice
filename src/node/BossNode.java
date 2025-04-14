package node;

import gui.SceneManager;

public class BossNode extends Node{

	public BossNode(int row, int col) {
		super(row, col, "Boss");
	}

	@Override
	public void executeNode() {
		SceneManager.getInstance().goToFight();
	}

}
