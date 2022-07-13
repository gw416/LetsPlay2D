package objects;

import static utils.Constants.ObjectConstants.*;

import main.Game;

// Game containers are the boxes and barrels
public class GameContainer extends GameObject {

	public GameContainer(int x, int y, int objType) {
		super(x, y, objType);
		createHitbox();

	}

	private void createHitbox() {
		if (objType == BOX) {
			initHitbox(25, 18);
			xDrawOffset = (int) (7 * Game.SCALE);
			yDrawOffset = (int) (12 * Game.SCALE);
		} else {
			initHitbox(23, 25);
			xDrawOffset = (int) (8 * Game.SCALE);
			yDrawOffset = (int) (5 * Game.SCALE);
		}
		
		// fix floating containers - 19.2
		hitbox.y += yDrawOffset + (int) (Game.SCALE * 2);
		// fix off center containers when draw on edge
		hitbox.x += xDrawOffset / 2;
	}
	
	public void update() {
		if(doAnimation)
			updateAnimationTick();
	}

}
