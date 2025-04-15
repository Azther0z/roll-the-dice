package unit.player;

import java.util.ArrayList;

import dice.Dice;
import dice.DivideDice;
import dice.MultiplyDice;
import unit.base.Attackable;
import unit.base.BaseUnit;
import unit.base.Defendable;
import unit.base.Healable;
import utils.ActionType;

public class Player extends BaseUnit implements Attackable, Defendable, Healable {
	private ArrayList<Dice> diceList;
	private ArrayList<DivideDice> divDiceList;
	private BaseUnit atkTarget;
	private BaseUnit divTarget;
	private int atkVal;
	private int defVal;
	private int healVal;

	public Player(int maxHp, String name) {
		super(maxHp, name);
		this.setDiceList(new ArrayList<Dice>());
		this.setDivDiceList(new ArrayList<DivideDice>());
		this.setAtkVal(0);
		this.setDefVal(0);
		this.setAtkTarget(null);
		this.setDivTarget(null);
	}

	public ArrayList<Dice> getDiceList() {
		return diceList;
	}

	public void setDiceList(ArrayList<Dice> diceList) {
		this.diceList = diceList;
	}

	public ArrayList<DivideDice> getDivDiceList() {
		return divDiceList;
	}

	public void setDivDiceList(ArrayList<DivideDice> divDiceList) {
		this.divDiceList = divDiceList;
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
		int mult = 1;
		for (Dice dice : this.getDiceList()) {
			if (dice.getActionType() == ActionType.DEFEND) {
				if (dice instanceof MultiplyDice) {
					mult = ((MultiplyDice) dice).multiply(mult);
					continue;
				}
				if (dice instanceof DivideDice) {
					continue;
				}
				this.setDefVal(this.getDefVal() + dice.getRollVal());
			}
		}
		this.setDefVal(this.getDefVal() * mult);
	}

	@Override
	public void updateAttack() {
		this.setAtkVal(0);
		int mult = 1;
		for (Dice dice : this.getDiceList()) {
			if (dice.getActionType() == ActionType.ATTACK) {
				if (dice instanceof MultiplyDice) {
					mult = ((MultiplyDice) dice).multiply(mult);
					continue;
				}
				if (dice instanceof DivideDice) {
					continue;
				}
				this.setAtkVal(this.getAtkVal() + dice.getRollVal());
			}
		}
		this.setAtkVal(this.getAtkVal() * mult);
	}

	@Override
	public void executeAttack() {
		this.getAtkTarget().takeDamage(this.getAtkVal());
	}

	@Override
	public int takeDamage(int damage) {
		int dealt = damage;
		dealt -= defVal;
		if (dealt > this.getHp()) {
			dealt = this.getHp();
		}
		if (dealt < 0) {
			dealt = 0;
		}
		this.setDefVal(this.getDefVal() - damage);
		this.setHp(this.getHp() - dealt);
		return dealt;
	}

	public void addDice(Dice dice) {
		this.getDiceList().add(dice);
		if(dice instanceof DivideDice) {
			this.getDivDiceList().add((DivideDice) dice);
		}
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
