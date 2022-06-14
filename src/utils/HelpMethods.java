package utils;

import static utils.Constants.EnemyConstants.CRABBY;

import java.awt.Color;
import java.awt.Point;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.text.DefaultEditorKit.CutAction;

import entities.Crabby;
import main.Game;

public class HelpMethods {

	public static boolean CanMoveHere(float x, float y, float width, float height, int[][] levelData) {

		if (!IsSolid(x, y, levelData)) // check top left position
			if (!IsSolid(x + width, y + height, levelData)) // check bottom right position
				if (!IsSolid(x + width, y, levelData)) // check bottom left position
					if (!IsSolid(x, y + height, levelData)) // check top right position
						return true;

		return false;
	}

	private static boolean IsSolid(float x, float y, int[][] levelData) {
		int maxWidth = levelData[0].length * Game.TILES_SIZE;
		if (x < 0 || x >= maxWidth)
			return true;
		if (y < 0 || y >= Game.GAME_HIEGHT)
			return true;

		float xIndex = x / Game.TILES_SIZE;
		float yIndex = y / Game.TILES_SIZE;

		return IsTileSolid((int) xIndex, (int) yIndex, levelData);
	}

	public static boolean IsTileSolid(int xTile, int yTile, int[][] lvlData) {
		int value = lvlData[yTile][xTile];

		if (value >= 48 || value < 0 || value != 11) // only have 48 sprites, sprite 12 is empty
			return true;

		return false;
	}

	public static float GetEntityXPosNextToWall(Rectangle2D.Float hitbox, float xSpeed) {

		int currentTile = (int) (hitbox.x / Game.TILES_SIZE);
		if (xSpeed > 0) {
			// Right
			int tileXPos = currentTile * Game.TILES_SIZE;
			int xOffset = (int) (Game.TILES_SIZE - hitbox.width);
			return tileXPos + xOffset - 1;
		} else {
			// Left
			return currentTile * Game.TILES_SIZE;
		}
	}

	public static float GetEntityYPosUnderRoofOrAboveFloor(Rectangle2D.Float hitbox, float airSpeed) {

		int currentTile = (int) (hitbox.y / Game.TILES_SIZE);
		if (airSpeed > 0) {
			// Down (floor)
			int tileYPos = currentTile * Game.TILES_SIZE;
			int yOffset = (int) (Game.TILES_SIZE - hitbox.height);
			return tileYPos + yOffset - 1;
		} else {
			// Up (jump/roof)
			return currentTile * Game.TILES_SIZE;
		}
	}

	public static boolean IsEntityOnFloor(Rectangle2D.Float hitbox, int[][] levelData) {

		// check pixel below bottom left and bottom right
		if (!IsSolid(hitbox.x, hitbox.y + hitbox.height + 1, levelData))
			if (!IsSolid(hitbox.x + hitbox.width, hitbox.y + hitbox.height + 1, levelData))
				return false;
		return true;
	}

	public static boolean IsFloor(Rectangle2D.Float hitbox, float xSpeed, int[][] levelData) {
		if(xSpeed > 0)
			return IsSolid(hitbox.x + hitbox.width + xSpeed, hitbox.y + hitbox.height + 1, levelData);
		else
			return IsSolid(hitbox.x + xSpeed, hitbox.y + hitbox.height + 1, levelData);
	}

	public static boolean IsAllTilesWalkable(int xStart, int xEnd, int y, int[][] lvlData) {
		for (int i = 0; i < xEnd - xStart; i++) {
			if(IsTileSolid(xStart + i, y, lvlData))
				return false;
			if(!IsTileSolid(xStart + i, y + 1, lvlData))
				return false;
		}
		return true;
		
	}
	
	public static boolean IsSightClear(int[][] lvlData, Rectangle2D.Float firstHitbox, Rectangle2D.Float secondHitbox, int yTile) {

		int firstXTile = (int) (firstHitbox.x / Game.TILES_SIZE);
		int secondXTile = (int) (secondHitbox.x / Game.TILES_SIZE);

		if (firstXTile > secondXTile) 
			return IsAllTilesWalkable(secondXTile, firstXTile, yTile, lvlData);
		else 
			return IsAllTilesWalkable(firstXTile, secondXTile, yTile, lvlData);

	}
	
	// use level data to find the amtching level sprite. level data will be either red green or blue
	public static int[][] GetLevelData(BufferedImage img) {
		
		int[][] levelData = new int[img.getHeight()][img.getWidth()];
		
		for(int j=0; j< img.getHeight(); j++) {
			for(int i=0; i<img.getWidth(); i++) {
				Color color = new Color(img.getRGB(i, j)); // X, Y
				int value = color.getRed();
				if(value >= 48)
					value = 0;
				levelData[j][i] = value; // prevent null pointer by hard coding 12x4
			}
		}
		return levelData;
	}
	
	public static ArrayList<Crabby> GetCrabs(BufferedImage img){
		
		ArrayList<Crabby> list = new ArrayList<Crabby>();

		for(int j=0; j< img.getHeight(); j++) {
			for(int i=0; i<img.getWidth(); i++) {
				Color color = new Color(img.getRGB(i, j)); // X, Y
				int value = color.getGreen();
				if(value == CRABBY)
					list.add(new Crabby(i * Game.TILES_SIZE, j * Game.TILES_SIZE));
			}
		}
		return list;
	}
	
	
	public static Point GetPlayerSpawn(BufferedImage img) {

		for(int j=0; j< img.getHeight(); j++) {
			for(int i=0; i<img.getWidth(); i++) {
				Color color = new Color(img.getRGB(i, j)); // X, Y
				int value = color.getGreen();
				if(value == 100)
					return new Point(i * Game.TILES_SIZE, j * Game.TILES_SIZE);
			}
		}
		
		return new Point(1 * Game.TILES_SIZE, 1 * Game.TILES_SIZE);
	}
	
	

}
