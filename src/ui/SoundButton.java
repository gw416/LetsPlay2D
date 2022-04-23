package ui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import utils.LoadSave;
import static utils.Constants.UI.PauseButtons.*;

public class SoundButton extends PauseButton {

	private BufferedImage[][] soundImages;
	private boolean mouseOver, mousePressed;
	private boolean muted;
	private int rowIndex,colIndex;
	
	public SoundButton(int x, int y, int width, int height, String name) {
		super(x, y, width, height, name);
		System.out.println("SoundButton.SoundButton()............. Creating SoundButton");

		loadSoundImages();
	}

	private void loadSoundImages() {
		System.out.println("SoundButton.loadSoundImages()......... Loading SoundButton images");
		
		BufferedImage tmp = LoadSave.GetSpriteAtlas(LoadSave.SOUNDS_BUTTONS);
		soundImages = new BufferedImage[2][3];
		
		for (int j = 0; j < soundImages.length; j++) 
			for (int i = 0; i < soundImages[j].length; i++) 
				soundImages[j][i] = tmp.getSubimage(i * SOUND_SIZE_DEFAULT, j * SOUND_SIZE_DEFAULT, SOUND_SIZE_DEFAULT, SOUND_SIZE_DEFAULT);
	}
	
	public void update() {
		if(muted)
			rowIndex = 1; // get second row of sound image for muted icons
		else
			rowIndex = 0;// get first row of sound image for unmuted icons
		
		colIndex = 0;// use colIndex to get the appropriate click/hover/defualt sound button icon
		if(mouseOver)
			colIndex = 1;
		if(mousePressed)
			colIndex = 2;
	}
	
	public void draw(Graphics g) {
		g.drawImage(soundImages[rowIndex][colIndex], x, y, width, height, null);
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

	public boolean isMuted() {
		return muted;
	}

	public void setMuted(boolean muted) {
		this.muted = muted;
	}
	
	
}
