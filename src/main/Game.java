package main;

import java.awt.Graphics;

import gamestates.Gamestate;
import gamestates.Menu;
import gamestates.Playing;

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
	
	private Playing playing;
	private Menu menu;
	
	public final static int TILES_DEFAULT_SIZE = 32;
	public final static float SCALE = 2f;
	public final static int TILES_IN_WIDTH = 26; // visible tiles
	public final static int TILES_IN_HIEGHT = 14; // visible tiles
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
		System.out.println("Game.initClasses().................... Initializing Game state");
		
		menu = new Menu(this);
		playing = new Playing(this);
	}

	private void startGameLooper() {
		System.out.println("Game.startGameLooper()................ Starting Game Thread looper");
		
		gameThread = new Thread(this);
		gameThread.start();
	}

	public void update() {

		switch(Gamestate.state) {
		case MENU:
			menu.update();
			break;
		case PLAYING:
			playing.update();
			break;
		case OPTIONS:
		case QUIT:
		default:
			System.exit(0);
			break;
		
		}
	}

	public void render(Graphics g) {
		
		switch(Gamestate.state) {
		case MENU:
			menu.draw(g);
			break;
		case PLAYING:
			playing.draw(g);
			break;
		default:
			break;
		
		}
	}

	@Override
	public void run() {

		System.out.println("Game.run()............................ Game loop ready");
		
		double timePerFrame = 1000000000.0 / FPS;
		double timePerUpdate = 1000000000.0 / UPS;

		long previousTime = System.nanoTime();

		int frames = 0;
		int updates = 0;
		long lastSecond = System.currentTimeMillis();

		double deltaU = 0; // updates
		double deltaF = 0; // frames
		
		System.out.println("Game.run()............................ Outside of Game loop");
		System.out.println("=============================================================================");
		System.out.println("======================== GAME: STARTED SUCCESSFULLY =========================");
		System.out.println("=============================================================================");
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
		if(Gamestate.state == Gamestate.PLAYING)
			playing.getPlayer().resetBooleans();
	}

	public Menu getMenu() {
		return menu;
	}
	
	public Playing getPlaying() {
		return playing;
	}

}
