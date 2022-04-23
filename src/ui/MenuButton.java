package ui;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import gamestates.Gamestate;
import utils.LoadSave;
import static utils.Constants.UI.Buttons.*;

public class MenuButton {

	private int xPos, yPos, rowIndex, index;
	private int xOffsetCenter = B_WIDTH / 2; // center of menu or half of the width
	private Gamestate state;
	private BufferedImage[] imgs;
	private boolean mouseOver, mousePressed;
	private Rectangle bounds;
	
	public MenuButton(int xPos, int yPos, int rowIndex, Gamestate state) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.rowIndex= rowIndex;
		this.state = state;
		loadImg();
		initBounds();
	}
	private void initBounds() {
		setBounds(new Rectangle(xPos - xOffsetCenter, yPos, B_WIDTH, B_HEIGHT));
	}
	private void loadImg() {
		imgs = new BufferedImage[3];
		BufferedImage tmp = LoadSave.GetSpriteAtlas(LoadSave.MENU_BUTTONS);
		for (int i = 0; i < imgs.length; i++) {
			imgs[i] = tmp.getSubimage(i * B_WIDTH_DEFAULT, rowIndex * B_HEIGHT_DEFAULT, B_WIDTH_DEFAULT, B_HEIGHT_DEFAULT);
		}
	}
	
	public void draw(Graphics g) {
		g.drawImage(imgs[index], xPos - xOffsetCenter, yPos, B_WIDTH, B_HEIGHT, null);
	}
	
	public void update() {
		index = 0;
		if(mouseOver) 
			index = 1;
		if(mousePressed) 
			index = 2;
	}
	
	public void applyGamestate() { 
		Gamestate.state = state;
	}
	
	public void resetBooleans() {
		mouseOver = false;
		mousePressed = false;
	}
	
	public boolean isMouseOver() {
		return mouseOver;
	}
	public void setMouseOver(boolean mouseOver) {
		this.mouseOver = mouseOver;
	}
	public boolean isMousePressed() {
		return mousePressed;
	}
	public void setMousePressed(boolean mousePressed) {
		this.mousePressed = mousePressed;
	}
	public Rectangle getBounds() {
		return bounds;
	}
	public void setBounds(Rectangle bounds) {
		this.bounds = bounds;
	}
	
}
