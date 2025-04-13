package unit.monster;

import unit.base.Attackable;
import unit.base.BaseUnit;
import unit.base.Healable;
import utils.GameLogic;

public class Vampire extends BaseUnit implements Attackable, Healable {
	private final int atkBase;
	private int atkVal;
	private int healVal;

	public Vampire(int maxHp, String name, int atkBase) {
		super(maxHp, name);
		if (atkBase < 0) {
			atkBase = 0;
		}
		this.atkBase = atkBase;
		this.setAtkVal(atkBase);
		this.setHealVal(atkBase);
	}

	public int getAtkVal() {
		return atkVal;
	}

	public void setAtkVal(int atkVal) {
		if (atkVal > this.atkBase) {
			atkVal = this.atkBase;
		}
		if (atkVal < 0) {
			atkVal = 0;
		}
		this.atkVal = atkVal;
	}

	public int getHealVal() {
		return healVal;
	}

	public void setHealVal(int healVal) {
		if (healVal > this.atkBase) {
			healVal = this.atkBase;
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
		this.setHp(this.getHp() + this.getHealVal());
	}

	@Override
	public void updateAttack() {
		// TODO Auto-generated method stub

	}

	@Override
	public void executeAttack() {
		int dealt = GameLogic.getInstance().getPlayer().takeDamage(this.getAtkVal());
		this.setHealVal(dealt);
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
