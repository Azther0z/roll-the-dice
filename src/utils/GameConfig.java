package utils;

public enum GameConfig {
	CONFIG;

	public static final String PROJECT_NAME = "I DONT FUCKING KNOW";
	public static final int MAX_ROW = 10;
	public static final int MAX_COL = 6;
	public static final int MIN_UNIT = 3;
	public static final int MAX_UNIT = 5;
	public static final int SHOP_DICE_AMOUNT = 4;
	public static final int SCREEN_WIDTH = 1280;
	public static final int SCREEN_HEIGHT = 720;
	public static final int MAIN_MENU_BIG_SPACE = SCREEN_HEIGHT / 6;
	public static final int MAIN_MENU_SMALL_SPACE = SCREEN_HEIGHT / 60;
	public static final int FONT_SIZE_BIG = 64;
	public static final int FONT_SIZE_MEDIUM = 32;
	public static final int FONT_SIZE_SMALL = 16;
	public static final int MAIN_MENU_BUTTON_WIDTH = SCREEN_WIDTH / 10;
	public static final int MAIN_MENU_BUTTON_HEIGHT = SCREEN_HEIGHT / 10;
	public static final int MAP_MENU_NODE_WIDTH = SCREEN_WIDTH / 20;
	public static final int MAP_MENU_NODE_HEIGHT = SCREEN_HEIGHT / 20;
	public static final int MAP_MENU_NODE_HGAP = MAP_MENU_NODE_WIDTH;
	public static final int MAP_MENU_NODE_VGAP = MAP_MENU_NODE_HEIGHT;
	public static final int MAP_MENU_BOSS_WIDTH = SCREEN_WIDTH/5;
	public static final int MAP_MENU_BOSS_HEIGHT = SCREEN_HEIGHT/5;
	public static final int MAP_MENU_BOSS_VGAP = MAP_MENU_BOSS_HEIGHT;
	public static final int MAP_MENU_CONTINUEBUTTON_WIDTH = SCREEN_WIDTH / 10;
	public static final int MAP_MENU_CONTINUEBUTTON_HEIGHT = SCREEN_HEIGHT / 10;
	public static final int MAP_MENU_VPADDING = SCREEN_HEIGHT / 10;
	public static final int MAP_MENU_HPADDING = 0;
	public static final int STACK_MAP_MENU_WIDTH = SCREEN_WIDTH/2;
	public static final int STACK_MAP_MENU_HEIGHT = SCREEN_HEIGHT/2;
	public static final int FIGHT_SCENE_PLAYER_X = SCREEN_WIDTH / 20;
	public static final int FIGHT_SCENE_PLAYER_Y = SCREEN_HEIGHT / 2;
	public static final int FIGHT_SCENE_DICE_X = FIGHT_SCENE_PLAYER_X * 4;
	public static final int FIGHT_SCENE_DICE_Y = SCREEN_HEIGHT / 2;
	public static final int FIGHT_SCENE_ENEMY_X = FIGHT_SCENE_PLAYER_X * 10;
	public static final int FIGHT_SCENE_ENEMY_Y = SCREEN_HEIGHT / 2;
	public static final int FIGHT_SCENE_ENDBUTTON_X = SCREEN_WIDTH / 10 * 2;
	public static final int FIGHT_SCENE_ENDBUTTON_Y = SCREEN_HEIGHT / 10 * 8;
	public static final int FIGHT_SCENE_ENDBUTTON_WIDTH = SCREEN_WIDTH / 10;
	public static final int FIGHT_SCENE_ENDBUTTON_HEIGHT = SCREEN_HEIGHT / 10;
}
