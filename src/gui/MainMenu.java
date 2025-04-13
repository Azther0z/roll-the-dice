package gui;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import utils.GameConfig;

public class MainMenu extends VBox {
	private Text gameText;
	private Button startButton;
	private Button exitButton;

	public MainMenu() {
		gameText = new Text(GameConfig.PROJECT_NAME);
		startButton = new Button("Start");
		startButton.setOnMouseClicked(e -> {
			SceneManager.getInstance().goToMap();
		});
		exitButton = new Button("Exit");
		exitButton.setOnMouseClicked(e -> {
			Platform.exit();
		});
		this.getChildren().addAll(gameText, startButton, exitButton);
		this.setAlignment(Pos.CENTER);
	}

}
