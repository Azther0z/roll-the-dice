package unit.base;

public abstract class BaseUnit {
	private final int maxHp;
	private int hp;
	private final String name;

	public BaseUnit(int maxHp, String name) {
		super();
		if (maxHp < 1) {
			maxHp = 1;
		}
		this.maxHp = maxHp;
		this.name = name;
		this.setHp(maxHp);
	}

	public int getMaxHp() {
		return maxHp;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		if (hp > this.maxHp) {
			hp = this.maxHp;
		}
		if (hp < 0) {
			hp = 0;
		}
		this.hp = hp;
	}

	public String getName() {
		return name;
	}

	abstract public int takeDamage(int damage);

}
