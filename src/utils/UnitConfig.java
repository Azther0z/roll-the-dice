package utils;

public enum UnitConfig {
	ATTACKER("Attacker", 20, 10, 0, 0), DEFENDER("Defender", 20, 0, 10, 0), HYBRID("Hybrid", 20, 0, 15, 0),
	HEALER("Healer", 20, 0, 0, 10), VAMPIRE("Vampire", 20, 10, 0, 0), BOSS("Boss", 80, 20, 40, 0),
	PLAYER("Player", 100, 0, 0, 0);

	public final String name;
	public final int maxHp;
	public final int atkBase;
	public final int defBase;
	public final int healBase;

	private UnitConfig(String name, int maxHp, int atkBase, int defBase, int healBase) {
		this.name = name;
		this.maxHp = maxHp;
		this.atkBase = atkBase;
		this.defBase = defBase;
		this.healBase = healBase;
	}
}
