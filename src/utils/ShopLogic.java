package utils;

import java.util.ArrayList;

import dice.Dice;
import dice.DivideDice;
import dice.MultiplyDice;
import utils.DiceConfig;

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
		generateDice();
	}

	private void generateDice() {
		ArrayList<DiceConfig> diceConfigList = DiceConfig.getDiceConfigList();
		for (int i = 0; i < GameConfig.SHOP_DICE_AMOUNT; i++) {
			int index = (int) Math.round(Math.random() * (diceConfigList.size() - 1));
			DiceConfig diceConfig = diceConfigList.get(index);
			if (diceConfig.type == DiceType.NORMAL) {
				shopList.add(new ShopItem(new Dice(diceConfig.minVal, diceConfig.maxVal), diceConfig.cost));
			} else if (diceConfig.type == DiceType.MULTIPLY) {
				shopList.add(new ShopItem(new MultiplyDice(diceConfig.minVal, diceConfig.maxVal), diceConfig.cost));
			} else if (diceConfig.type == DiceType.DIVIDE) {
				shopList.add(new ShopItem(new DivideDice(diceConfig.minVal, diceConfig.maxVal), diceConfig.cost));
			}
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
