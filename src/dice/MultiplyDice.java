package dice;

public class MultiplyDice extends Dice {

	public MultiplyDice(int minVal, int maxVal) {
		super(minVal, maxVal);
	}

	public int multiply(int val) {
		return this.getRollVal() * val;
	}
}
