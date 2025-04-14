package node;

import gui.SceneManager;
public class EnemyNode extends Node {

	public EnemyNode(int row, int col) {
		super(row, col, "Enemy");
	}

	@Override
	public void executeNode() {
		SceneManager.getInstance().goToFight();
	}

}
