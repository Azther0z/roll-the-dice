package gui;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import utils.GameConfig;

public class MainMenu extends VBox {
	private Text gameText;
	private VBox buttonBox;
	private Button startButton;
	private Button exitButton;

	public MainMenu() {
		gameText = new Text(GameConfig.PROJECT_NAME);
		gameText.setFont(new Font(GameConfig.FONT_SIZE_BIG));
		buttonBox = new VBox();
		startButton = new Button("START");
		startButton.setOnMouseClicked(e -> {
			SceneManager.getInstance().goToMap();
		});
		startButton.setFont(new Font(GameConfig.FONT_SIZE_MEDIUM));
		startButton.setPrefSize(GameConfig.MAIN_MENU_BUTTON_WIDTH, GameConfig.MAIN_MENU_BUTTON_HEIGHT);
		exitButton = new Button("EXIT");
		exitButton.setOnMouseClicked(e -> {
			Platform.exit();
		});
		exitButton.setFont(new Font(GameConfig.FONT_SIZE_MEDIUM));
		exitButton.setPrefSize(GameConfig.MAIN_MENU_BUTTON_WIDTH, GameConfig.MAIN_MENU_BUTTON_HEIGHT);
		buttonBox.getChildren().addAll(startButton, exitButton);
		buttonBox.setAlignment(Pos.CENTER);
		buttonBox.setSpacing(GameConfig.MAIN_MENU_SMALL_SPACE);
		this.getChildren().addAll(gameText, buttonBox);
		this.setAlignment(Pos.CENTER);
		this.setSpacing(GameConfig.MAIN_MENU_BIG_SPACE);
	}
}
