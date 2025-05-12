package unit.monster;

import dice.DivideDice;
import unit.base.Attackable;
import unit.base.BaseUnit;
import unit.base.Defendable;
import utils.GameLogic;

public class Hybrid extends BaseUnit implements Attackable, Defendable {
	private final int defBase;
	private int atkVal;
	private int defVal;

	public Hybrid(int maxHp, String name, int defBase) {
		super(maxHp, name);
		if (defBase < 0) {
			defBase = 0;
		}
		this.defBase = defBase;
		this.setAtkVal(defBase);
		this.setDefVal(defBase);
	}

	public int getAtkVal() {
		return atkVal;
	}

	public void setAtkVal(int atkVal) {
		if (atkVal > this.defBase) {
			atkVal = this.defBase;
		} else if (atkVal < 0) {
			atkVal = 0;
		}
		this.atkVal = atkVal;
	}

	public int getDefVal() {
		return defVal;
	}

	public void setDefVal(int defVal) {
		if (defVal > this.defBase) {
			defVal = this.defBase;
		} else if (defVal < 0) {
			defVal = 0;
		}
		this.defVal = defVal;
	}

	@Override
	public void updateDefend() {
		int base = this.defBase;
		for (DivideDice divDice : GameLogic.getInstance().getPlayer().getDivDiceList()) {
			if (divDice.getDivTarget().equals(this)) {
				base = divDice.divide(base);
			}
		}
		if (GameLogic.getInstance().getPlayer().getAtkTarget().equals(this)) {
			int playerAtkVal = GameLogic.getInstance().getPlayer().getAtkVal();
			if (playerAtkVal > base) {
				this.setDefVal(base);
			} else {
				this.setDefVal(playerAtkVal);
			}

		} else {
			this.setDefVal(0);
		}
	}

	@Override
	public void updateAttack() {
		int base = this.defBase;
		for (DivideDice divDice : GameLogic.getInstance().getPlayer().getDivDiceList()) {
			if (divDice.getDivTarget().equals(this)) {
				base = divDice.divide(base);
			}
		}
		if (GameLogic.getInstance().getPlayer().getAtkTarget().equals(this)) {
			int playerAtkVal = GameLogic.getInstance().getPlayer().getAtkVal();
			if (playerAtkVal > base) {
				this.setAtkVal(0);
			} else {
				this.setAtkVal(base - playerAtkVal);
			}

		} else {
			this.setAtkVal(base);
		}
	}

	@Override
	public void executeAttack() {
		GameLogic.getInstance().getPlayer().takeDamage(this.getAtkVal());
	}

	@Override
	public int takeDamage(int damage) {
		int dealt = damage - defVal;
		if (dealt <= 0) {
			return 0;
		}
		if (dealt > this.getHp()) {
			dealt = this.getHp();
		}
		this.setHp(this.getHp() - dealt);
		return dealt;
	}

}
