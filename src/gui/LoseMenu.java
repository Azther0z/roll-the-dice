package gui;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class LoseMenu extends VBox{
	private Text gameText;
	private Button backButton;

	public LoseMenu() {
		gameText = new Text("DEFEATED");
		backButton = new Button("Back to menu");
		backButton.setOnMouseClicked(e -> {
			SceneManager.getInstance().goToMenu();
			SceneManager.getInstance().resetGame();
		});
		this.getChildren().addAll(gameText, backButton);
		this.setAlignment(Pos.CENTER);
	}
}
