package dice;

import utils.ActionType;

public class Dice {
	private int minVal;
	private int maxVal;
	private ActionType actionType;
	private int rollVal;
	private final String imagePath;

	public Dice(int minVal, int maxVal) {
		this(minVal, maxVal, "dice.png");
	}

	public Dice(int minVal, int maxVal, String imagePath) {
		this.setMinVal(minVal);
		this.setMaxVal(maxVal);
		this.setActionType(ActionType.NOTHING);
		this.setRollVal(minVal);
		this.imagePath = imagePath;
	}

	public int getMinVal() {
		return minVal;
	}

	public void setMinVal(int minVal) {
		if (minVal < 1) {
			minVal = 1;
		}
		this.minVal = minVal;
	}

	public int getMaxVal() {
		return maxVal;
	}

	public void setMaxVal(int maxVal) {
		if (maxVal < 1) {
			maxVal = 1;
		}
		this.maxVal = maxVal;
	}

	public ActionType getActionType() {
		return actionType;
	}

	public void setActionType(ActionType actionType) {
		this.actionType = actionType;
	}

	public int getRollVal() {
		return rollVal;
	}

	public void setRollVal(int rollVal) {
		this.rollVal = rollVal;
	}

	public void roll() {
		int rollVal = (int) Math.round((this.getMaxVal() - this.getMinVal()) * Math.random() + this.getMinVal());
		this.setRollVal(rollVal);
	}

	public void switchAction() {
		if (this.getActionType() == ActionType.ATTACK) {
			this.setActionType(ActionType.DEFEND);
		} else if (this.getActionType() == ActionType.DEFEND) {
			this.setActionType(ActionType.ATTACK);
		}
	}

	public String getImagePath() {
		return imagePath;
	}
}
