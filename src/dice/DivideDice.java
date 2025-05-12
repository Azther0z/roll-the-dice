package dice;

import unit.base.BaseUnit;

public class DivideDice extends Dice {
	private BaseUnit divTarget;

	public DivideDice(int minVal, int maxVal) {
		super(minVal, maxVal);
		this.setDivTarget(null);
	}

	public BaseUnit getDivTarget() {
		return divTarget;
	}

	public void setDivTarget(BaseUnit divTarget) {
		this.divTarget = divTarget;
	}

	public int divide(int val) {
		return (int) Math.round(((double) val) / ((double) this.getRollVal()));
	}
}
