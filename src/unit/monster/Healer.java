package unit.monster;

import dice.DivideDice;
import unit.base.BaseUnit;
import unit.base.Healable;
import utils.FightLogic;
import utils.GameLogic;

public class Healer extends BaseUnit implements Healable {
	private final int healBase;
	private int healVal;
	private BaseUnit healTarget;

	public Healer(int maxHp, String name, int healBase) {
		super(maxHp, name);
		if (healBase < 0) {
			healBase = 0;
		}
		this.healBase = healBase;
		this.setHealVal(healBase);
		this.setHealTarget(this);
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

	public BaseUnit getHealTarget() {
		return healTarget;
	}

	public void setHealTarget(BaseUnit healTarget) {
		this.healTarget = healTarget;
	}

	@Override
	public void updateHeal() {
		this.setHealVal(healBase);
		for (BaseUnit baseUnit : FightLogic.getInstance().getEnemyList()) {
			if (baseUnit.getHp() < healTarget.getHp() && !baseUnit.equals(healTarget)) {
				this.setHealTarget(baseUnit);
			}
		}
		for (DivideDice divDice : GameLogic.getInstance().getPlayer().getDivDiceList()) {
			if (divDice.getDivTarget().equals(this)) {
				this.setHealVal(divDice.divide(this.getHealVal()));
			}
		}
	}

	@Override
	public void executeHeal() {
		healTarget.setHp(healTarget.getHp() + this.getHealVal());
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
