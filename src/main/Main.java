package main;

import javafx.application.Application;
import javafx.stage.Stage;
import utils.GameConfig;
import gui.SceneManager;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setScene(SceneManager.getInstance().getScene());
		primaryStage.setTitle(GameConfig.PROJECT_NAME);
		primaryStage.setResizable(false);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
