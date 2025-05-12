package utils;

import java.util.ArrayList;

import dice.DivideDice;
import unit.base.Attackable;
import unit.base.BaseUnit;
import unit.base.Defendable;
import unit.base.Healable;
import unit.monster.Attacker;
import unit.monster.Boss;
import unit.monster.Defender;
import unit.monster.Healer;
import unit.monster.Hybrid;
import unit.monster.Vampire;

public class FightLogic {
	private static FightLogic instance;
	private ArrayList<BaseUnit> enemyList;
	private boolean isEnd;

	public FightLogic() {
		this.newFightLogic();
	}

	public static FightLogic getInstance() {
		if (instance == null) {
			instance = new FightLogic();
		}
		return instance;
	}

	public void newFightLogic() {
		this.enemyList = new ArrayList<BaseUnit>();
		if (GameLogic.getInstance().getCurrentNode().equals(GameLogic.getInstance().getBossNode())) {
			this.enemyList.add(new Boss(UnitConfig.BOSS.maxHp, UnitConfig.BOSS.name, UnitConfig.BOSS.atkBase,
					UnitConfig.BOSS.defBase));
		} else {
			addRandomEnemy();
		}
		initializeTurn();
		updateTurn();
		this.setEnd(false);
	}

	public boolean isEnd() {
		return isEnd;
	}

	public void setEnd(boolean isEnd) {
		this.isEnd = isEnd;
	}

	public ArrayList<BaseUnit> getEnemyList() {
		return enemyList;
	}

	public void setEnemyList(ArrayList<BaseUnit> enemyList) {
		this.enemyList = enemyList;
	}

	public void initializeTurn() {
		GameLogic.getInstance().getPlayer().rollDice();
		GameLogic.getInstance().getPlayer().setAllDiceAcionType(ActionType.ATTACK);
		GameLogic.getInstance().getPlayer().setAtkTarget(enemyList.get(0));
		for (DivideDice divDice : GameLogic.getInstance().getPlayer().getDivDiceList()) {
			divDice.setDivTarget(enemyList.get(0));
		}
	}

	public void updateTurn() {
		GameLogic.getInstance().getPlayer().updateDefend();
		GameLogic.getInstance().getPlayer().updateAttack();
		for (BaseUnit enemy : enemyList) {
			if (enemy instanceof Attackable) {
				Attackable attackable = (Attackable) enemy;
				attackable.updateAttack();
			}
		}
		for (BaseUnit enemy : enemyList) {
			if (enemy instanceof Defendable) {
				Defendable defendable = (Defendable) enemy;
				defendable.updateDefend();
			}
		}
		for (BaseUnit enemy : enemyList) {
			if (enemy instanceof Healable) {
				Healable healable = (Healable) enemy;
				healable.updateHeal();
			}
		}
	}

	public void endTurn() {
		GameLogic.getInstance().getPlayer().executeAttack();
		for (int i = 0; i < enemyList.size(); i++) {
			if (enemyList.get(i).getHp() <= 0) {
				enemyList.remove(i);
			}
		}
		if (enemyList.size() == 0) {
			this.setEnd(true);
			if (GameLogic.getInstance().getBossNode().equals(GameLogic.getInstance().getCurrentNode())) {
				GameLogic.getInstance().setWin(true);
			}
			return;
		}
		for (BaseUnit enemy : enemyList) {
			if (enemy instanceof Attackable) {
				Attackable attackable = (Attackable) enemy;
				attackable.executeAttack();
			}
		}
		for (BaseUnit enemy : enemyList) {
			if (enemy instanceof Healable) {
				Healable healable = (Healable) enemy;
				healable.executeHeal();
			}
		}
		if (GameLogic.getInstance().getPlayer().getHp() <= 0) {
			GameLogic.getInstance().setDefeated(true);
		}
		initializeTurn();
	}

	private void addRandomEnemy() {
		int amount = (int) Math
				.round(GameConfig.MIN_UNIT + (GameConfig.MAX_UNIT - GameConfig.MIN_UNIT) * Math.random());
		for (int i = 0; i < amount; i++) {
			int monster = (int) Math.round(4 * Math.random());
			if (monster == 0) {
				this.enemyList.add(
						new Attacker(UnitConfig.ATTACKER.maxHp, UnitConfig.ATTACKER.name, UnitConfig.ATTACKER.atkBase));
			} else if (monster == 1) {
				this.enemyList.add(
						new Defender(UnitConfig.DEFENDER.maxHp, UnitConfig.DEFENDER.name, UnitConfig.DEFENDER.defBase));
			} else if (monster == 2) {
				this.enemyList
						.add(new Hybrid(UnitConfig.HYBRID.maxHp, UnitConfig.HYBRID.name, UnitConfig.HYBRID.defBase));
			} else if (monster == 3) {
				this.enemyList.add(
						new Vampire(UnitConfig.VAMPIRE.maxHp, UnitConfig.VAMPIRE.name, UnitConfig.VAMPIRE.atkBase));
			} else if (monster == 4) {
				this.enemyList
						.add(new Healer(UnitConfig.HEALER.maxHp, UnitConfig.HEALER.name, UnitConfig.HEALER.healBase));
			}
		}
	}
}
