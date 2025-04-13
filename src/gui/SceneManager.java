package gui;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import gui.MainMenu;

public class SceneManager {
	private static SceneManager instance;
	private Scene scene;
	private BorderPane root;
	private VBox mainMenu;
	private StackPane mapMenu;
	private GridPane fightScene;

	public SceneManager() {
		this.newScene();
	}
	
	public Scene getScene() {
		return scene;
	}

	private void newScene() {
		root = new BorderPane();
		root.setPrefSize(800, 600);
		mainMenu = new MainMenu();
		mapMenu = new MapMenu();
		fightScene = null;
		scene = new Scene(root);
		root.setCenter(mainMenu);
	}

	public static SceneManager getInstance() {
		if (instance == null) {
			instance = new SceneManager();
		}
		return instance;
	}
	
	public void goToMenu() {
		this.root.setCenter(mainMenu);
	}
	
	public void goToMap() {
		this.root.setCenter(mapMenu);
	}
	
	public void goToFight() {
		if(fightScene==null) {
			fightScene = new FightScene();
		}
		this.root.setCenter(fightScene);
	}
}
