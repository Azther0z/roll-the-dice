package utils;

import java.util.ArrayList;

import dice.Dice;
import dice.DivideDice;
import dice.MultiplyDice;
import node.BossNode;
import node.Edge;
import node.EnemyNode;
import node.Node;
import node.RestNode;
import node.ShopNode;
import unit.player.Player;

public class GameLogic {
	private static GameLogic instance;
	private Player player;
	private boolean isRolled;
	private ArrayList<Edge> edgeList;
	private ArrayList<ArrayList<Node>> nodeGrid;
	private Node currentNode;
	private Node bossNode;
	private boolean isDefeated;
	private boolean isWin;

	public GameLogic() {
		this.newGameLogic();
	}

	public static GameLogic getInstance() {
		if (instance == null) {
			instance = new GameLogic();
		}
		return instance;
	}

	public boolean isRolled() {
		return isRolled;
	}

	public void setRolled(boolean isRolled) {
		this.isRolled = isRolled;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Node getCurrentNode() {
		return currentNode;
	}

	public void setCurrentNode(Node currentNode) {
		this.currentNode = currentNode;
	}

	public Node getBossNode() {
		return bossNode;
	}

	public void setBossNode(Node bossNode) {
		this.bossNode = bossNode;
	}

	public boolean isDefeated() {
		return isDefeated;
	}

	public void setDefeated(boolean isDefeated) {
		this.isDefeated = isDefeated;
	}

	public boolean isWin() {
		return isWin;
	}

	public void setWin(boolean isWin) {
		this.isWin = isWin;
	}

	public ArrayList<Edge> getEdgeList() {
		return edgeList;
	}

	public void setEdgeList(ArrayList<Edge> edgeList) {
		this.edgeList = edgeList;
	}

	public ArrayList<ArrayList<Node>> getNodeGrid() {
		return nodeGrid;
	}

	public void setNodeGrid(ArrayList<ArrayList<Node>> nodeGrid) {
		this.nodeGrid = nodeGrid;
	}

	public void newGameLogic() {
		this.setCurrentNode(null);
		this.setDefeated(false);
		this.setWin(false);
		this.setPlayer(new Player(UnitConfig.PLAYER.maxHp, UnitConfig.PLAYER.name));
		starterDice();
		generateMap();
	}

	public void rest() {
		this.getPlayer().rollDice();
		this.getPlayer().setAllDiceAcionType(ActionType.HEAL);
		this.getPlayer().updateHeal();
		this.getPlayer().executeHeal();
	}

	private void generateMap() {
		edgeList = new ArrayList<Edge>();
		nodeGrid = new ArrayList<ArrayList<Node>>();
		for (int i = 0; i < GameConfig.MAX_ROW; i++) {
			nodeGrid.add(new ArrayList<Node>());
			for (int j = 0; j < GameConfig.MAX_COL; j++) {
				if (i % 3 == 0 && i != 0 && i != GameConfig.MAX_ROW - 1) {
					int index = (int) Math.round(Math.random());
					if (index == 0) {
						nodeGrid.get(i).add(new ShopNode(i, j));
					} else if (index == 1) {
						nodeGrid.get(i).add(new RestNode(i, j));
					}
					continue;
				}
				if (i == GameConfig.MAX_ROW - 1) {
					nodeGrid.get(i).add(new RestNode(i, j));
					continue;
				}
				nodeGrid.get(i).add(new EnemyNode(i, j));
			}
		}
		for (int i = 0; i < GameConfig.MAX_ROW - 1; i++) {
			for (int j = 0; j < GameConfig.MAX_COL; j++) {
				edgeList.add(new Edge(nodeGrid.get(i).get(j), nodeGrid.get(i + 1).get(j)));
				int index = (int) Math.round(Math.random() * 2);
				if (index == 1 && j > 0) {
					edgeList.add(new Edge(nodeGrid.get(i).get(j), nodeGrid.get(i + 1).get(j - 1)));
				} else if (index == 2 && j < GameConfig.MAX_COL - 1) {
					edgeList.add(new Edge(nodeGrid.get(i).get(j), nodeGrid.get(i + 1).get(j + 1)));
				}
			}
		}
		bossNode = new BossNode(GameConfig.MAX_ROW, 0);
		for (int j = 0; j < GameConfig.MAX_COL; j++) {
			edgeList.add(new Edge(nodeGrid.get(GameConfig.MAX_ROW - 1).get(j), bossNode));
		}
	}

	private void starterDice() {
		this.getPlayer().addDice(new Dice(DiceConfig.D4NORM.minVal, DiceConfig.D4NORM.maxVal));
		this.getPlayer().addDice(new Dice(DiceConfig.D6NORM.minVal, DiceConfig.D6NORM.maxVal));
		this.getPlayer().addDice(new MultiplyDice(DiceConfig.D4MULT.minVal, DiceConfig.D4MULT.maxVal));
		this.getPlayer().addDice(new DivideDice(DiceConfig.D4DIV.minVal, DiceConfig.D4DIV.maxVal));
	}

}
