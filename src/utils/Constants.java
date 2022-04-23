package utils;

import main.Game;

public class Constants {
	
	public static class UI {
		public static class Buttons{
			public static final int B_WIDTH_DEFAULT = 140;
			public static final int B_HEIGHT_DEFAULT = 56;
			
			public static final int B_WIDTH = (int) (B_WIDTH_DEFAULT * Game.SCALE);
			public static final int B_HEIGHT = (int) (B_HEIGHT_DEFAULT * Game.SCALE);			
		}
		public static class PauseButtons{
			public static final int SOUND_SIZE_DEFAULT = 42; // pixel size for sound button sprites
			public static final int SOUND_SIZE = (int) (SOUND_SIZE_DEFAULT * Game.SCALE);
			
		}
		
		public static class URMButtons{
			public static final int URM_SIZE_DEFAULT = 56; // pixel size for sound button sprites
			public static final int URM_SIZE = (int) (URM_SIZE_DEFAULT * Game.SCALE);
			
		}
		
		public static class VolumeButtons{
			public static final int VOLUME_DEFAULT_WIDTH = 28;
			public static final int VOLUME_DEFAULT_HEIGHT = 44;
			public static final int SLIDER_DEFAULT_WIDTH = 215;
			
			public static final int VOLUME_WIDTH = (int) (VOLUME_DEFAULT_WIDTH * Game.SCALE);
			public static final int VOLUME_HEIGHT = (int) (VOLUME_DEFAULT_HEIGHT * Game.SCALE);
			public static final int SLIDER_WIDTH = (int) (SLIDER_DEFAULT_WIDTH * Game.SCALE);
		}
	}

	public static class Directions {
		public static final int LEFT = 0;
		public static final int UP = 1;
		public static final int RIGHT = 2;
		public static final int DOWN = 3;
	}

	public static class PlayerConstants {
		public static final int IDLE = 0;
		public static final int RUNNING = 1;
		public static final int JUMP = 2;
		public static final int FALLING = 3;
		public static final int GROUND = 4;
		public static final int HIT = 5;
		public static final int ATTACK_1 = 6;
		public static final int ATTACK_JUMP_1 = 7;
		public static final int ATTACK_JUMP_2 = 8;

		/**
		 * Determine the number of sprites for each action.
		 * 
		 * each action referes to a sprite row in the atlas.
		 *  
		 * @param player_action
		 * @return
		 */
		public static int GetSpriteAmounts(int player_action) {
			switch (player_action) {
			case RUNNING:
				return 6; // has 6 sprites
			case IDLE:
				return 5; // has 5 sprites
			case HIT:
				return 4; // has 4 sprites ect.
			case JUMP:
			case ATTACK_1:
			case ATTACK_JUMP_1:
			case ATTACK_JUMP_2:
				return 3;
			case GROUND:
				return 2; 
			case FALLING:
			default:
				return 1;
			}
		}
	}
}