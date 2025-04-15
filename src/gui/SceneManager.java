package gui;

import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import utils.FightLogic;
import utils.GameLogic;

public class SceneManager {
	private static SceneManager instance;
	private Scene scene;
	private BorderPane root;
	private MainMenu mainMenu;
	private ScrollPane scrollPane;
	private MapMenu mapMenu;
	private FightScene fightScene;

	public SceneManager() {
		this.newScene();
	}

	public Scene getScene() {
		return scene;
	}

	public MapMenu getMapMenu() {
		return mapMenu;
	}

	public void setMapMenu(MapMenu mapMenu) {
		this.mapMenu = mapMenu;
	}

	public void newScene() {
		resetGame();
		root = new BorderPane();
		root.setPrefSize(800, 600);
		scene = new Scene(root);
		root.setCenter(mainMenu);
	}
	
	public void resetGame() {
		GameLogic.getInstance().newGameLogic();
		mainMenu = new MainMenu();
		mapMenu = new MapMenu();
		scrollPane = new ScrollPane();
		scrollPane.setContent(mapMenu);
		fightScene = null;
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
		this.root.setCenter(scrollPane);
	}

	public void goToFight() {
		if (FightLogic.getInstance().isEnd() || fightScene == null) {
			fightScene = new FightScene();
		}
		this.root.setCenter(fightScene);
	}
}
