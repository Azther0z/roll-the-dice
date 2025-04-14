package node;

import gui.SceneManager;

public class ShopNode extends Node {

	public ShopNode(int row, int col) {
		super(row, col, "Shop");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void executeNode() {
		SceneManager.getInstance().getMapMenu().initializeShopMenu();
	}

}
