package utils;

import java.util.ArrayList;

import dice.Dice;

public class ShopLogic {

	private ArrayList<ShopItem> shopList;
	private int money;

	public ShopLogic() {
		this.shopList = new ArrayList<ShopItem>();
		this.setMoney(0);
		this.setMoney(0);
		GameLogic.getInstance().getPlayer().rollDice();
		for (Dice dice : GameLogic.getInstance().getPlayer().getDiceList()) {
			this.setMoney(this.getMoney() + dice.getRollVal());
		}
		// TODO randomly add dice
		for (int i = 0; i < 4; i++) {
			shopList.add(new ShopItem(new Dice(1, 6), 5));
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

	public ArrayList<ShopItem> getShopList() {
		return shopList;
	}

	public void setShopList(ArrayList<ShopItem> shopList) {
		this.shopList = shopList;
	}

	public void updateShop() {
		// TODO wtf is this
	}

	public boolean buyDice(ShopItem item) {
		if (!item.isBought() && item.getCost() <= money) {
			item.setBought(true);
			GameLogic.getInstance().getPlayer().addDice(item.getDice());
			this.setMoney(this.getMoney() - item.getCost());
			return true;
		}
		return false;
	}
}
