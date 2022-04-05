package utils;

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
		
		if(x < 0 || x >= Game.GAME_WIDTH)
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
}
