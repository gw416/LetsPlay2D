package utils;

public class Constants {

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

		public static int GetSpriteAmounts(int player_action) {

			// references sprite atlas resource 'player_sprites.png'
			switch (player_action) {
			case RUNNING:
				return 6; // has 6 sprites
			case IDLE:
				return 5; // has 5 sprites
			case HIT:
				return 4; // has 4 sprites
			case JUMP:
			case ATTACK_1:
			case ATTACK_JUMP_1:
			case ATTACK_JUMP_2:
				return 3; // all have 3 sprites
			case GROUND:
				return 2; // has 2 sprites
			case FALLING:
			default:
				return 1; // both have 1 sprite
			}
		}
	}
}
