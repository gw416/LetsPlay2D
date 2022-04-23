package utils;

import java.awt.geom.Rectangle2D;

import javax.swing.text.DefaultEditorKit.CutAction;

import main.Game;

public class HelpMethods {

	public static boolean CanMoveHere(float x, float y, float width, float height, int[][] levelData) {
		
		if(!IsSolid(x,y,levelData)) 						// check top left position
			if(!IsSolid(x+width, y+height, levelData))		// check bottom right position
				if(!IsSolid(x+width, y, levelData))			// check bottom left position
					if(!IsSolid(x, y+height, levelData))	// check top right position
						return true;
	
		
		return false;
	}
	
	private static boolean IsSolid(float x, float y, int[][] levelData) {
		int maxWidth = levelData[0].length * Game.TILES_SIZE;
		if(x < 0 || x >= maxWidth)
			return true;
		if (y < 0 || y >= Game.GAME_HIEGHT)
			return true;
		
		float xIndex = x / Game.TILES_SIZE;
		float yIndex = y / Game.TILES_SIZE;
		int value = levelData[(int) yIndex][(int) xIndex];
		
		if(value >= 48 || value <0 || value != 11) // only have 48 sprites, sprite 12 is empty
			return true;
		
		return false;
	}
	
	public static float GetEntityXPosNextToWall(Rectangle2D.Float hitbox, float xSpeed) {
		
		int currentTile = (int) (hitbox.x / Game.TILES_SIZE);
		if(xSpeed > 0) {
			// Right
			int tileXPos = currentTile * Game.TILES_SIZE;
			int xOffset = (int) (Game.TILES_SIZE - hitbox.width);
			return tileXPos + xOffset - 1;
		}else {
			// Left
			return currentTile * Game.TILES_SIZE;
		}
	}
	
	public static float GetEntityYPosUnderRoofOrAboveFloor(Rectangle2D.Float hitbox, float airSpeed) {
		
		int currentTile = (int) (hitbox.y / Game.TILES_SIZE);
		if(airSpeed > 0) {
			// Down (floor)
			int tileYPos = currentTile * Game.TILES_SIZE;
			int yOffset = (int) (Game.TILES_SIZE - hitbox.height);
			return tileYPos + yOffset - 1;
		}else {
			// Up (jump/roof)
			return currentTile * Game.TILES_SIZE;
		}
	}
	
	public static boolean IsEntityOnFloor(Rectangle2D.Float hitbox, int[][] levelData) {
		
		// check pixel below bottom left and bottom right
		if(!IsSolid(hitbox.x, hitbox.y + hitbox.height + 1, levelData))
			if(!IsSolid(hitbox.x + hitbox.width, hitbox.y + hitbox.height + 1, levelData))
				return false;
		return true;
	}
}
