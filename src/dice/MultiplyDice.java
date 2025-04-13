package dice;

public class MultiplyDice extends Dice {

	public MultiplyDice(int minVal, int maxVal) {
		super(minVal, maxVal, "dice_multiply.png");
	}

	public int multiply(int val) {
		return this.getRollVal() * val;
		// TODO need new design
	}
}
