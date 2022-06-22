package levels;

import static utils.HelpMethods.GetCrabs;
import static utils.HelpMethods.GetLevelData;
import static utils.HelpMethods.GetPlayerSpawn;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import entities.Crabby;
import main.Game;

public class Level {

	private BufferedImage img;
	private int[][] levelData;
	private ArrayList<Crabby> crabs;
	private int levelTilesWide; // the entire level width in tiles
	private int maxTilesOffset; // max tiles we can actually move the screen
								// (remainding offset tiles outside border)
	private int maxLevelOffsetX; // turn maxTilesOffset into pixels
	private Point playerSpawn;
	
	public Level(BufferedImage img) {
		this.img = img;
		createLevelData();
		createEnemies();
		calcLvlOffsets();
		calcPlayerSpawn();
	}
	private void calcPlayerSpawn() {
		playerSpawn = GetPlayerSpawn(img);
	}
	private void calcLvlOffsets() {
		levelTilesWide = img.getWidth();
		maxTilesOffset = levelTilesWide - Game.TILES_IN_WIDTH;
		maxLevelOffsetX = Game.TILES_SIZE * maxTilesOffset;
	}
	private void createEnemies() {
		crabs = GetCrabs(img);
	}
	private void createLevelData() {
		levelData = GetLevelData(img);		
	}
	public int getSpritePosition(int x, int y) {
		return levelData[y][x];
	}
	
	public Point getPlayerSpawn() {
		return playerSpawn;
	}

	public int[][] getLevelData() {
		return levelData;
	}
	public int getLevelOffset() {
		return maxLevelOffsetX;
	}
	public ArrayList<Crabby> getCrabs(){
		return crabs;
	}
}
