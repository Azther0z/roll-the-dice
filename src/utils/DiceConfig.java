package utils;

import java.util.ArrayList;

public enum DiceConfig {
	D4MULT(1, 4, DiceType.MULTIPLY), D4DIV(1, 4, DiceType.DIVIDE), D4NORM(1, 4, DiceType.NORMAL),
	D6NORM(1, 6, DiceType.NORMAL), D8NORM(1, 8, DiceType.NORMAL), D10NORM(1, 10, DiceType.NORMAL),
	D12NORM(1, 12, DiceType.NORMAL), D20NORM(1, 20, DiceType.NORMAL);

	public final int minVal;
	public final int maxVal;
	public final int cost;
	public final DiceType type;

	private DiceConfig(int minVal, int maxVal, DiceType type) {
		this.minVal = minVal;
		this.maxVal = maxVal;
		this.cost = minVal + maxVal;
		this.type = type;
	}

	public static ArrayList<DiceConfig> getDiceConfigList() {
		ArrayList<DiceConfig> diceConfigList = new ArrayList<DiceConfig>();
		for (DiceConfig diceConfig : DiceConfig.values()) {
			diceConfigList.add(diceConfig);
		}
		return diceConfigList;
	}
}
