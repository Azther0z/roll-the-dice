package gui;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import utils.GameConfig;
import utils.GuiUtils;

public class LoseMenu extends VBox {
	private Text gameText;
	private Button backButton;

	public LoseMenu() {
		gameText = GuiUtils.createText("DEFEATED", GameConfig.FONT_SIZE_BIG);
		backButton = new Button("RESTART");
		backButton.setPrefSize(GameConfig.MAIN_MENU_BUTTON_WIDTH, GameConfig.MAIN_MENU_BUTTON_HEIGHT);
		this.setSpacing(GameConfig.MAIN_MENU_BIG_SPACE);
		backButton.setOnMouseClicked(e -> {
			SceneManager.getInstance().goToMenu();
			SceneManager.getInstance().resetGame();
		});
		this.getChildren().addAll(gameText, backButton);
		this.setAlignment(Pos.CENTER);
	}
}
