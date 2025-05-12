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
		if (healVal < 0) {
			healVal = 0;
		}
		this.healVal = healVal;
	}

	@Override
	public void updateHeal() {
		int newHealVal = 0;
		for (Dice dice : this.getDiceList()) {
			newHealVal += dice.getRollVal();
		}
		this.setHealVal(newHealVal);
	}

	@Override
	public void executeHeal() {
		this.setHp(this.getHp() + this.getHealVal());
	}

	@Override
	public void updateDefend() {
		int newDefVal = 0;
		int mult = 1;
		for (Dice dice : this.getDiceList()) {
			if (dice.getActionType() == ActionType.DEFEND) {
				if (dice instanceof MultiplyDice) {
					mult *= dice.getRollVal();
				} else if (!(dice instanceof DivideDice)) {
					newDefVal += dice.getRollVal();
				}
			}
		}
		this.setDefVal(newDefVal * mult);
	}

	@Override
	public void updateAttack() {
		int newAtkVal = 0;
		int mult = 1;
		for (Dice dice : this.getDiceList()) {
			if (dice.getActionType() == ActionType.ATTACK) {
				if (dice instanceof MultiplyDice) {
					mult *= dice.getRollVal();
				} else if (!(dice instanceof DivideDice)) {
					newAtkVal += dice.getRollVal();
				}
			}
		}
		this.setAtkVal(newAtkVal * mult);
	}

	@Override
	public void executeAttack() {
		this.getAtkTarget().takeDamage(this.getAtkVal());
	}

	@Override
	public int takeDamage(int damage) {
		int dealt = damage - this.getDefVal();
		if (dealt < 0) {
			dealt = 0;
		} else if (dealt > this.getHp()) {
			dealt = this.getHp();
		}
		this.setDefVal(this.getDefVal() - damage);
		this.setHp(this.getHp() - dealt);
		return dealt;
	}

	public void addDice(Dice dice) {
		this.getDiceList().add(dice);
		if (dice instanceof DivideDice) {
			this.getDivDiceList().add((DivideDice) dice);
		}
	}

	public void sortDice() {
		ArrayList<Dice> normalDice = new ArrayList<>();
		ArrayList<Dice> multiplyDice = new ArrayList<>();
		ArrayList<Dice> divideDice = new ArrayList<>();

		for (Dice dice : this.diceList) {
			if (dice instanceof DivideDice) {
				divideDice.add(dice);
			} else if (dice instanceof MultiplyDice) {
				multiplyDice.add(dice);
			} else {
				normalDice.add(dice);
			}
		}

		this.diceList.clear();
		this.diceList.addAll(normalDice);
		this.diceList.addAll(multiplyDice);
		this.diceList.addAll(divideDice);
	}

	public void rollDice() {
		for (Dice dice : this.getDiceList()) {
			dice.roll();
		}
		sortDice();
	}

	public void setAllDiceAcionType(ActionType actionType) {
		for (Dice dice : this.getDiceList()) {
			dice.setActionType(actionType);
		}
	}
}
