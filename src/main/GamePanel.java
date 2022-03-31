package main;

import static main.Game.GAME_HIEGHT;
import static main.Game.GAME_WIDTH;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

public class GamePanel extends JPanel {

	private MouseInputs moustInputs;
	private Game game;
	public GamePanel(Game game) {
		moustInputs = new MouseInputs(this);
		this.game = game;
		
		setPanelSize();
		addKeyListener(new KeyboardInputs(this));
		addMouseListener(moustInputs);
		addMouseMotionListener(moustInputs);
	}

	private void setPanelSize() {
		Dimension dim = new Dimension(GAME_WIDTH, GAME_HIEGHT); // set panel as this because image dimensions will 32 by 32 pixels
		setPreferredSize(dim);
		System.out.println("SIZE: "+GAME_WIDTH +"  : " + GAME_HIEGHT);
	}

	public void updateGame() {

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);// super class called to enable drawing graphics on JPanel
		game.render(g);
	}
	
	public Game getGame() {
		return game;
	}
}
