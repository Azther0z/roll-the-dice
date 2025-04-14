package gui;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class WinMenu extends VBox{
	private Text gameText;
	private Button backButton;

	public WinMenu() {
		gameText = new Text("VICTORY");
		backButton = new Button("Back to menu");
		backButton.setOnMouseClicked(e -> {
			SceneManager.getInstance().goToMenu();
			SceneManager.getInstance().resetGame();
		});
		this.getChildren().addAll(gameText, backButton);
		this.setAlignment(Pos.CENTER);
	}
}
