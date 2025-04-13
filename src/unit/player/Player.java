package unit.player;

import java.util.ArrayList;

import dice.Dice;
import unit.base.Attackable;
import unit.base.BaseUnit;
import unit.base.Defendable;
import unit.base.Healable;
import utils.ActionType;

public class Player extends BaseUnit implements Attackable, Defendable, Healable {
	private ArrayList<Dice> diceList;
	private BaseUnit atkTarget;
	private BaseUnit divTarget;
	private int atkVal;
	private int defVal;
	private int healVal;

	public Player(int maxHp, String name) {
		super(maxHp, name);
		this.diceList = new ArrayList<Dice>();
		this.setAtkVal(0);
		this.setDefVal(0);
		this.setAtkTarget(null);
		this.setDivTarget(null);
	}

	public ArrayList<Dice> getDiceList() {
		return diceList;
	}

	public BaseUnit getAtkTarget() {
		return atkTarget;
	}

	public void setAtkTarget(BaseUnit atkTarget) {
		this.atkTarget = atkTarget;
	}

	public BaseUnit getDivTarget() {
		return divTarget;
	}

	public void setDivTarget(BaseUnit divTarget) {
		this.divTarget = divTarget;
	}

	@Override
	public int getAtkVal() {
		return atkVal;
	}

	public void setAtkVal(int atkVal) {
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
		this.healVal = healVal;
	}

	@Override
	public void updateHeal() {
		this.setHealVal(0);
		for (Dice dice : this.getDiceList()) {
			this.setHealVal(this.getHealVal() + dice.getRollVal());
		}
	}

	@Override
	public void executeHeal() {
		this.setHp(this.getHp() + this.getHealVal());
	}

	@Override
	public void updateDefend() {
		this.setDefVal(0);
		for (Dice dice : this.getDiceList()) {
			if (dice.getActionType() == ActionType.DEFEND)
				this.setDefVal(this.getDefVal() + dice.getRollVal());
		}
	}

	@Override
	public void updateAttack() {
		this.setAtkVal(0);
		for (Dice dice : this.getDiceList()) {
			if (dice.getActionType() == ActionType.ATTACK)
				this.setAtkVal(this.getAtkVal() + dice.getRollVal());
		}
	}

	@Override
	public void executeAttack() {
		this.getAtkTarget().takeDamage(this.getAtkVal());
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

	public void addDice(Dice dice) {
		this.getDiceList().add(dice);
	}

	public void rollDice() {
		for (Dice dice : this.getDiceList()) {
			dice.roll();
		}
	}

	public void setAllDiceAcionType(ActionType actionType) {
		for (Dice dice : this.getDiceList()) {
			dice.setActionType(actionType);
		}
	}
}
