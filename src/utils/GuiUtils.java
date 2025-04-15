package utils;

import javafx.scene.Node;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class GuiUtils {

	public static Text createText(String text, int size) {
		Text display = new Text();
		display.setText(text);
		display.setFont(new Font(size));
		return display;
	}
	
	public static void setLayout(Node node, int x,int y) {
		node.setLayoutX(x);
		node.setLayoutY(y);
	}
	public static void setLayout(Node node, double x,double y) {
		node.setLayoutX(x);
		node.setLayoutY(y);
	}
}
