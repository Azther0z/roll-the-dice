package dice;

public class DivideDice extends Dice {

	public DivideDice(int minVal, int maxVal) {
		super(minVal, maxVal, "dice_divide.png");
	}

	public int divide(int val) {
		return this.getRollVal() / val;
		// TODO need new design, might not return int
	}
}
