package unit.monster;

import unit.base.BaseUnit;
import unit.base.Healable;
import utils.FightLogic;

public class Healer extends BaseUnit implements Healable {
	private final int healBase;
	private int healVal;

	public Healer(int maxHp, String name, int healBase) {
		super(maxHp, name);
		if (healBase < 0) {
			healBase = 0;
		}
		this.healBase = healBase;
	}

	@Override
	public int getHealVal() {
		return healVal;
	}

	public void setHealVal(int healVal) {
		if (healVal > this.healBase) {
			healVal = this.healBase;
		}
		if (healVal < 0) {
			healVal = 0;
		}
		this.healVal = healVal;
	}

	@Override
	public void updateHeal() {
		// TODO Auto-generated method stub

	}

	@Override
	public void executeHeal() {
		// TODO Auto-generated method stub
		BaseUnit healTarget = this;
		for (BaseUnit enemy : FightLogic.getInstance().getEnemyList()) {
			if (enemy.getHp() <= healTarget.getHp() && !enemy.equals(healTarget)) {
				healTarget = enemy;
			}
		}
		healTarget.setHp(healTarget.getHp()+this.getHealVal());
	}

	@Override
	public int takeDamage(int damage) {
		int dealt = damage;
		if (dealt > this.getHp()) {
			dealt = this.getHp();
		}
		this.setHp(this.getHp() - dealt);
		return dealt;
	}

}
