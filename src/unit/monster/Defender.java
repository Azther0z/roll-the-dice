package unit.monster;

import unit.base.BaseUnit;
import unit.base.Defendable;

public class Defender extends BaseUnit implements Defendable {
	private final int defBase;
	private int defVal;

	public Defender(int maxHp, String name, int defBase) {
		super(maxHp, name);
		if (defBase < 0) {
			defBase = 0;
		}
		this.defBase = defBase;
		this.setDefVal(defBase);
	}

	@Override
	public int getDefVal() {
		return defVal;
	}

	public void setDefVal(int defVal) {
		if (defVal > this.defBase) {
			defVal = this.defBase;
		}
		if (defVal < 0) {
			defVal = 0;
		}
		this.defVal = defVal;
	}

	@Override
	public void updateDefend() {
		// TODO Auto-generated method stub

	}

	@Override
	public int takeDamage(int damage) {
		int dealt = damage;
		if (dealt <= defVal) {
			return 0;
		}
		dealt -= defVal;
		if (dealt > this.getHp()) {
			dealt = this.getHp();
		}
		this.setHp(this.getHp() - dealt);
		return dealt;
	}

}
