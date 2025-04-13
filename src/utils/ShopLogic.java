package utils;

import java.util.ArrayList;

import dice.Dice;

public class ShopLogic {

	private ArrayList<Dice> shopList;
	private ArrayList<Integer> shopCost;
	private ArrayList<Boolean> isBought;
	private int money;

	public ShopLogic() {
		this.shopList = new ArrayList<Dice>();
		this.shopCost = new ArrayList<Integer>();
		this.isBought = new ArrayList<Boolean>();
		this.setMoney(0);
		this.setMoney(0);
		GameLogic.getInstance().getPlayer().rollDice();
		for (Dice dice : GameLogic.getInstance().getPlayer().getDiceList()) {
			this.setMoney(this.getMoney() + dice.getRollVal());
		}
		// TODO randomly add dice
		for (int i = 0; i < 4; i++) {
			shopList.add(new Dice(1, 6));
			shopCost.add(5);
			isBought.add(false);
		}
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		if (money < 0) {
			money = 0;
		}
		this.money = money;
	}

	public void updateShop() {
		// TODO wtf is this
	}

	public void buyDice(int index) {
		if (!isBought.get(index) && shopCost.get(index) <= this.getMoney()) {
			isBought.set(index, true);
			GameLogic.getInstance().getPlayer().addDice(shopList.get(index));
			this.setMoney(this.getMoney() - shopCost.get(index));
		}
	}
}
