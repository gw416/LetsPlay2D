package main;

import java.awt.Graphics;

import entities.Player;
import levels.LevelManager;

/**
 * The Game class will handle all other entities within the entire game.
 * 
 * @author Greg
 *
 */
public class Game implements Runnable {

	private GameWindow gameWindow;
	private GamePanel gamePanel;
	private Thread gameThread;
	private final int FPS = 120;
	private final int UPS = 200;

	private Player player;
	private LevelManager levelManager;
	
	public final static int TILES_DEFAULT_SIZE = 32;
	public final static float SCALE = 1f;
	public final static int TILES_IN_WIDTH = 26;
	public final static int TILES_IN_HIEGHT = 14;
	public final static int TILES_SIZE = (int) (TILES_DEFAULT_SIZE * SCALE);// 1.0, 1.5, 2.25, 2.5 etc to keep recommended pixel dimension ratio 
	public final static int GAME_WIDTH = TILES_SIZE * TILES_IN_WIDTH;
	public final static int GAME_HIEGHT = TILES_SIZE * TILES_IN_HIEGHT;

	public Game() {
		initClasses();

		gamePanel = new GamePanel(this);
		gameWindow = new GameWindow(gamePanel);
		gamePanel.requestFocus();

		startGameLooper();
	}

	private void initClasses() {
		levelManager = new LevelManager(this);
		player = new Player(200, 200, (int) (64 * SCALE), (int) (40 * SCALE));
		player.loadLvlData(levelManager.getCurrentLevel().getLevelData());
		
	}

	private void startGameLooper() {
		gameThread = new Thread(this);
		gameThread.start();
	}

	public void update() {
		levelManager.update();
		player.update();
	}

	public void render(Graphics g) {
		levelManager.draw(g);
		player.render(g);
	}

	@Override
	public void run() {

		double timePerFrame = 1000000000.0 / FPS;
		double timePerUpdate = 1000000000.0 / UPS;

		long previousTime = System.nanoTime();

		int frames = 0;
		int updates = 0;
		long lastSecond = System.currentTimeMillis();

		double deltaU = 0; // updates
		double deltaF = 0; // frames

		while (true) {
			long currentTime = System.nanoTime();

			deltaU += (currentTime - previousTime) / timePerUpdate;
			deltaF += (currentTime - previousTime) / timePerFrame;
			previousTime = currentTime;

			if (deltaU >= 1) {
				update();
				updates++;
				deltaU--;
			}

			if (deltaF >= 1) {
				gamePanel.repaint();
				frames++;
				deltaF--;
			}

			if (System.currentTimeMillis() - lastSecond >= 1000) {
				lastSecond = System.currentTimeMillis();
				System.out.println("FPS: " + frames + " | UPS: " + updates);
				frames = 0;
				updates = 0;
			}
		}
	}

	public void windowFocusLost() {
		player.resetBooleans();
	}
	public Player getPlayer() {
		return player;
	}

}
