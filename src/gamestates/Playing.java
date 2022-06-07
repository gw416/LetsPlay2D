package gamestates;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.Random;

import entities.EnemyManager;
import entities.Player;
import levels.LevelManager;
import main.Game;
import ui.PauseOverlay;
import utils.LoadSave;
import static utils.Constants.Environment.*;

public class Playing extends State implements StateMethods {

	private Player player;
	private LevelManager levelManager;
	private EnemyManager enemyManager;
	private PauseOverlay pauseOverlay;
	private boolean paused = false;
	
	private BufferedImage horizonImg,bigCloud,smallCloud;
	private int[] smallCloudsPosition;
	private Random rnd = new Random();
	
	private int xLvlOffset; // how many tiles over the border to shift the level
	private int leftBorder = (int) (0.2 * Game.GAME_WIDTH); // the left border to determine offset
	private int rightBorder = (int) (0.8 * Game.GAME_WIDTH);// the right border to determine offset
	private int levelTilesWide = LoadSave.GetLevel()[0].length; // the entire level width in tiles
	private int maxTilesOffset = levelTilesWide - Game.TILES_IN_WIDTH; // max tiles we can actually move the screen (remainding offset tiles outside border)
	private int maxLevelOffsetX = maxTilesOffset * Game.TILES_SIZE; // turn maxTilesOffset into pixels
	
	public Playing(Game game) {
		super(game);
		System.out.println("Playing.Playing()..................... Creating Playing state");
		
		initClasses();
		
		horizonImg = LoadSave.GetSpriteAtlas(LoadSave.PLAYING_BG_IMG);
		bigCloud = LoadSave.GetSpriteAtlas(LoadSave.BIG_CLOUDS);
		smallCloud = LoadSave.GetSpriteAtlas(LoadSave.SMALL_CLOUDS);
		smallCloudsPosition = new int[8];	
		for (int i = 0; i < smallCloudsPosition.length; i++) 
			smallCloudsPosition[i] = (int) (90 * Game.SCALE) + rnd.nextInt((int) (105 * Game.SCALE));
	}

	private void initClasses() {
		System.out.println("Playing.initClasses()................. Initializing Playing class");
		
		levelManager = new LevelManager(game);
		enemyManager = new EnemyManager(this);
		player = new Player(200, 200, (int) (64 * Game.SCALE), (int) (40 * Game.SCALE));
		player.loadLvlData(levelManager.getCurrentLevel().getLevelData());
		pauseOverlay = new PauseOverlay(this);
		
	}

	@Override
	public void update() {
		if(!paused) {
			levelManager.update();
			player.update();
			enemyManager.update(levelManager.getCurrentLevel().getLevelData(), player);
			checkCloseToBorder();
		}else {
			pauseOverlay.update();	
		}
	}

	private void checkCloseToBorder() {
		int playerX = (int) player.getHitbox().x;
		int diff = playerX - xLvlOffset;
		
		if(diff > rightBorder)
			xLvlOffset += diff - rightBorder;
		else if (diff < leftBorder)
			xLvlOffset += diff - leftBorder;
	
		if(xLvlOffset > maxLevelOffsetX)
			xLvlOffset = maxLevelOffsetX;
		else if (xLvlOffset < 0 )
			xLvlOffset = 0;
	}

	@Override
	public void draw(Graphics g) {
		
		g.drawImage(horizonImg, 0, 0, Game.GAME_WIDTH, Game.GAME_HIEGHT, null);
		drawClouds(g);
	
		player.render(g, xLvlOffset);
		enemyManager.draw(g, xLvlOffset);
		levelManager.draw(g, xLvlOffset);
		
		if(paused) {
			g.setColor(new Color(210,163,163,150));
			g.fillRect(0, 0, Game.GAME_WIDTH, Game.GAME_HIEGHT);
			pauseOverlay.draw(g);
		}
	}
	
	private void drawClouds(Graphics g) {
		for (int i = 0; i < 3; i++) 
			g.drawImage(bigCloud, i * BIG_CLOUD_WIDTH - (int) (xLvlOffset * 0.3), (int)(204 * Game.SCALE), BIG_CLOUD_WIDTH, BIG_CLOUD_HEIGHT, null);
		
		for (int i = 0; i < smallCloudsPosition.length; i++) 
			g.drawImage(smallCloud, SMALL_CLOUD_WIDTH * 4 * i - (int) (xLvlOffset * 0.7), smallCloudsPosition[i], SMALL_CLOUD_WIDTH, SMALL_CLOUD_HEIGHT, null);
	}

	public void mouseDragged(MouseEvent e) {
		if(paused)
			pauseOverlay.mouseDragged(e);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1)
			player.setAttacking(true);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(paused)
			pauseOverlay.mousePressed(e);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(paused)
			pauseOverlay.mouseReleased(e);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if(paused)
			pauseOverlay.mouseMoved(e);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) 
		{
			case KeyEvent.VK_A:
				player.setLeft(true);
				break;
			case KeyEvent.VK_D:
				player.setRight(true);
				break;
			case KeyEvent.VK_SPACE:
				player.setJump(true);
				break;
			case KeyEvent.VK_BACK_SPACE:
				paused = !paused;
				break;
			case KeyEvent.VK_ESCAPE:
				paused = !paused;
				break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) 
		{
			case KeyEvent.VK_A:
				player.setLeft(false);
				break;
			case KeyEvent.VK_D:
				player.setRight(false);
				break;
			case KeyEvent.VK_SPACE:
				player.setJump(false);
				break;
		}
	}
	
	public void unpauseGame() {
		paused = false;
	}

	public void windowFocusLost() {
		player.resetBooleans();
	}
	public Player getPlayer() {
		return player;
	}
}
