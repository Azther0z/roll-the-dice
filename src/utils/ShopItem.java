package utils;

import dice.Dice;

public class ShopItem {
	private Dice dice;
	private int cost;
	private boolean isBought;

	public ShopItem(Dice dice, int cost) {
		super();
		this.dice = dice;
		this.cost = cost;
		this.isBought = false;
	}

	public Dice getDice() {
		return dice;
	}

	public void setDice(Dice dice) {
		this.dice = dice;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public boolean isBought() {
		return isBought;
	}

	public void setBought(boolean isBought) {
		this.isBought = isBought;
	}

}
