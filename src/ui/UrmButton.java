package ui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import utils.LoadSave;
import static utils.Constants.UI.URMButtons.*;

public class UrmButton extends PauseButton {

	private BufferedImage[] imgs;
	private int rowIndex, index;
	private boolean mouseOver, mousePressed;

	public UrmButton(int x, int y, int width, int height, String name, int rowIndex) {
		super(x, y, width, height, name);
		System.out.println("UrmButton.UrmButton()................. Creating UrmButton");

		this.rowIndex = rowIndex;
		loadImages();

	}

	private void loadImages() {
		BufferedImage tmp = LoadSave.GetSpriteAtlas(LoadSave.URM_BUTTONS);
		imgs = new BufferedImage[3];
		for (int i = 0; i < imgs.length; i++) {
			imgs[i] = tmp.getSubimage(i * URM_SIZE_DEFAULT, rowIndex * URM_SIZE_DEFAULT, URM_SIZE_DEFAULT, URM_SIZE_DEFAULT);
		}
	}

	public void update() {
		index = 0;
		if (mouseOver)
			index = 1;
		if (mousePressed)
			index = 2;

	}

	public void draw(Graphics g) {
		g.drawImage(imgs[index], x, y, URM_SIZE, URM_SIZE, null);
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

	
}
