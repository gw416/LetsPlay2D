package main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import inputs.KeyboardInputs;
import inputs.MouseInputs;

public class GamePanel extends JPanel {

	private MouseInputs moustInputs;
	private float xDelta = 100, yDelta = 100;
	private BufferedImage img, subImg;
	public GamePanel() {
		moustInputs = new MouseInputs(this);
		
		importImg();
		
		setPanelSize();
		addKeyListener(new KeyboardInputs(this));
		addMouseListener(moustInputs);
		addMouseMotionListener(moustInputs);
	}

	private void importImg() {
		InputStream is =getClass().getResourceAsStream("/player_sprites.png");
		
		try {
			img = ImageIO.read(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void setPanelSize() {
		Dimension dim = new Dimension(1280, 800); // set panel as this because image dimensions will 32 by 32 pixels
		setMinimumSize(dim);
		setMaximumSize(dim);
		setPreferredSize(dim);
	}

	public void changeXDelta(int value) {
		this.xDelta += value;
		repaint();
	}

	public void changeYDelta(int value) {
		this.yDelta += value;
		repaint();
	}

	public void setRectPos(int x, int y) {
		this.xDelta = x;
		this.yDelta = y;
		repaint();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);// super class called to enable drawing graphics on JPanel

	//	g.drawImage(img.getSubimage(0, 0, 64, 40), 0, 0, null);
		subImg = img.getSubimage(1*64, 8*40, 64, 40);
		g.drawImage(subImg, (int)xDelta, (int)yDelta, 128, 80, null);
		
	}

}
