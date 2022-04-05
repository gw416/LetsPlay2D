package entities;

import static utils.Constants.PlayerConstants.ATTACK_1;
import static utils.Constants.PlayerConstants.GetSpriteAmounts;
import static utils.Constants.PlayerConstants.IDLE;
import static utils.Constants.PlayerConstants.RUNNING;
import static utils.HelpMethods.CanMoveHere;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.Game;
import utils.LoadSave;

public class Player extends Entity {

	private BufferedImage[][] animations;
	private int animationTick, animationIndex, tickRate = 20;
	private int playerAction = IDLE;
	private boolean moving = false, attacking = false;
	private boolean left,up,right,down;
	private float playerSpeed = 2.0f;
	private int[][] lvlData;
	private float xDrawOffset = 21 * Game.SCALE;
	private float yDrawOffset = 4 * Game.SCALE;

	public Player(float x, float y, int width, int height) {
		super(x, y, width, height);
		loadAnimations();
		initHitbox(x, y, 20 * Game.SCALE, 28 * Game.SCALE);
	}

	public void update() {
		updatePos();
		updateAnimationTick();
		setAnimation();
	}

	public void render(Graphics g) {
		g.drawImage(animations[playerAction][animationIndex], (int) (hitbox.x - xDrawOffset), (int) (hitbox.y - yDrawOffset), width, height, null);
		drawHitbox(g);
	}

	private void updateAnimationTick() {
		animationTick++;

		if (animationTick >= tickRate) {
			animationTick = 0;
			animationIndex++;

			if (animationIndex >= GetSpriteAmounts(playerAction)) {
				animationIndex = 0;
				attacking = false;
			}
		}
	}

	private void setAnimation() {
		int startAnimation = playerAction;
		
		if (moving)
			playerAction = RUNNING;
		else
			playerAction = IDLE;
		
		if(attacking)
			playerAction = ATTACK_1;
		
		if(startAnimation != playerAction) // some change in animation
			resetAnimation();
	}

	//bug fix - choppy attack animation
	private void resetAnimation() {
		animationTick = 0;
		animationIndex = 0;
	}

	private void updatePos() {
		float xSpeed = 0, ySpeed = 0;
		moving = false;
		if(!left && !right && !up && !down)
			return;		
		
		if(left && !right) 
			xSpeed = -playerSpeed;
		else if(right && !left) 
			xSpeed = playerSpeed;
		
		if(up && !down) 
			ySpeed = -playerSpeed;
		else if(down && !up) 
			ySpeed = playerSpeed;
		
//		if(CanMoveHere(x + xSpeed, y + ySpeed, width, height, lvlData)) {
//			this.x += xSpeed;
//			this.y += ySpeed;
//			moving = true;
//		}
		
		if(CanMoveHere(hitbox.x + xSpeed, hitbox.y + ySpeed, hitbox.width, hitbox.height, lvlData)) {
			hitbox.x += xSpeed;
			hitbox.y += ySpeed;
			moving = true;
		}
	}

	private void loadAnimations() {
		BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.PLAYER_ATLAS);
		
		animations = new BufferedImage[9][6];
		for (int j = 0; j < animations.length; j++)
			for (int i = 0; i < animations[i].length; i++)
				animations[j][i] = img.getSubimage(i * 64, j * 40, 64, 40);		
	}

	public void loadLvlData(int[][] lvlData) {
		this.lvlData = lvlData;
	}
	public void setAttacking(boolean attacking) {
		this.attacking = attacking;
	}
	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

	public boolean isUp() {
		return up;
	}

	public void setUp(boolean up) {
		this.up = up;
	}

	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public boolean isDown() {
		return down;
	}

	public void setDown(boolean down) {
		this.down = down;
	}
	
	public void resetBooleans() {
		left = false;
		up = false;
		right = false;
		down = false;
	}

}
