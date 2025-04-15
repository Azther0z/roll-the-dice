package unit.monster;

import dice.DivideDice;
import unit.base.Attackable;
import unit.base.BaseUnit;
import unit.base.Defendable;
import unit.base.Healable;
import utils.GameLogic;

public class Boss extends BaseUnit implements Attackable, Defendable, Healable {
	private final int atkBase;
	private final int defBase;
	private final int SCALING = 5;
	private int atkVal;
	private int defVal;
	private int healVal;
	private int receivedCount;
	private int willHeal;

	public Boss(int maxHp, String name, int atkBase, int defBase) {
		super(maxHp, name);
		if (atkBase < 0) {
			atkBase = 0;
		}
		this.atkBase = atkBase;
		this.setAtkVal(atkBase);
		if (defBase < 0) {
			defBase = 0;
		}
		this.defBase = defBase;
		this.setDefVal(defBase);
		this.setReceivedCount(0);
		this.setWillHeal(0);
	}

	@Override
	public int getAtkVal() {
		return atkVal;
	}

	public void setAtkVal(int atkVal) {
		if (atkVal > this.calculateAtkBase()) {
			atkVal = this.calculateAtkBase();
		}
		if (atkVal < 0) {
			atkVal = 0;
		}
		this.atkVal = atkVal;
	}

	@Override
	public int getDefVal() {
		return defVal;
	}

	public void setDefVal(int defVal) {
		if (defVal > this.calculateDefBase()) {
			defVal = this.calculateDefBase();
		}
		if (defVal < 0) {
			defVal = 0;
		}
		this.defVal = defVal;
	}

	@Override
	public int getHealVal() {
		return healVal;
	}

	public void setHealVal(int healVal) {
		if (healVal < 0) {
			healVal = 0;
		}
		this.healVal = healVal;
	}

	public int getReceivedCount() {
		return receivedCount;
	}

	public void setReceivedCount(int receivedCount) {
		if (receivedCount < 0) {
			receivedCount = 0;
		}
		this.receivedCount = receivedCount;
	}

	public int getWillHeal() {
		return willHeal;
	}

	public void setWillHeal(int willHeal) {
		if (willHeal < 0) {
			willHeal = 0;
		}
		this.willHeal = willHeal;
	}

	private int calculateAtkBase() {
		return this.atkBase + (this.SCALING * this.getReceivedCount());
	}

	private int calculateDefBase() {
		return this.defBase - (this.SCALING * this.getReceivedCount());
	}

	@Override
	public void updateHeal() {
		if (this.getWillHeal() == 1) {
			this.setHealVal(this.getMaxHp() / 2 - this.getHp());
		} else {
			this.setHealVal(0);
		}
	}

	@Override
	public void executeHeal() {
		this.setHp(this.getHp()+this.getHealVal());
		this.setWillHeal(this.getWillHeal()+1);
	}

	@Override
	public void updateDefend() {
		this.setDefVal(this.calculateDefBase());
		for(DivideDice divDice:GameLogic.getInstance().getPlayer().getDivDiceList()) {
			if(divDice.getDivTarget().equals(this)) {
				this.setDefVal(divDice.divide(this.getDefVal()));
			}
		}
	}

	@Override
	public void updateAttack() {
		this.setAtkVal(this.calculateAtkBase());
		for(DivideDice divDice:GameLogic.getInstance().getPlayer().getDivDiceList()) {
			if(divDice.getDivTarget().equals(this)) {
				this.setAtkVal(divDice.divide(this.getAtkVal()));
			}
		}
	}

	@Override
	public void executeAttack() {
		GameLogic.getInstance().getPlayer().takeDamage(this.getAtkVal());
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
		if (dealt > 0) {
			this.setReceivedCount(this.getReceivedCount() + 1);
		}
		if (this.getHp() < (int) Math.round(this.getMaxHp() / 4)) {
			this.setWillHeal(this.getWillHeal() + 1);
		}
		return dealt;
	}
}
