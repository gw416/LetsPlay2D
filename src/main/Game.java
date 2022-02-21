package main;

/**
 * The Game class will handle all other entities within the entire game.
 * 
 * @author isGreg
 *
 */
public class Game implements Runnable{
	
	private GameWindow gameWindow;
	private GamePanel gamePanel;
	private Thread gameThread;
	private final int FPS = 120;

	public Game() {
		gamePanel = new GamePanel();
		gameWindow = new GameWindow(gamePanel);
		gamePanel.requestFocus();
		startGameLooper();
	}
	
	private void startGameLooper() {
		gameThread = new Thread(this);
		gameThread.start();
	}

	@Override
	public void run() {
		
		double timePerFrame = 1000000000.0 / FPS;
		long lastFrame = System.nanoTime();
		long currentTime = System.nanoTime();
		int frames =0;
		long lastSecond = System.currentTimeMillis();
		while(true) 
		{
			currentTime = System.nanoTime();
			if(currentTime - lastFrame >= timePerFrame) {
				gamePanel.repaint();
				lastFrame = currentTime;
				frames++;
			}
			if(System.currentTimeMillis() - lastSecond >= 1000) {
				lastSecond = System.currentTimeMillis();
				System.out.println("FPS: " + frames);
				frames= 0;
			}
		}
	}
	
}
