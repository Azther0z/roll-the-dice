package unit.monster;

import unit.base.Attackable;
import unit.base.BaseUnit;
import utils.GameLogic;

public class Attacker extends BaseUnit implements Attackable {
	private final int atkBase;
	private int atkVal;

	public Attacker(int maxHp, String name, int atkBase) {
		super(maxHp, name);
		if (atkBase < 0) {
			atkBase = 0;
		}
		this.atkBase = atkBase;
		this.setAtkVal(atkBase);
	}

	@Override
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

	@Override
	public void updateAttack() {
		// TODO Auto-generated method stub
	}

	@Override
	public void executeAttack() {
		GameLogic.getInstance().getPlayer().takeDamage(this.getAtkVal());
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
