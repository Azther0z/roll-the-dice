package node;

import gui.SceneManager;
public class EnemyNode extends Node {

	public EnemyNode(int row, int col) {
		super(row, col, "Enemy");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void executeNode() {
		// TODO Auto-generated method stub
		SceneManager.getInstance().goToFight();
	}

}
