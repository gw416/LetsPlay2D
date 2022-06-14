package ui;

import static utils.Constants.UI.PauseButtons.*;
import static utils.Constants.UI.URMButtons.*;
import static utils.Constants.UI.VolumeButtons.*;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import gamestates.Gamestate;
import gamestates.Playing;
import main.Game;
import utils.LoadSave;

public class PauseOverlay {
	
	private Playing playing;
	private BufferedImage backgroundImg;
	private int bgX,bgY,bgW, bgH; // width, hieght, x and y location for background image
	private SoundButton musicButton, sfxButton;
	private UrmButton menuButton, replayButton, unpauseButton;
	private VolumeButton volumeButton;
		
	public PauseOverlay(Playing playing) {
		System.out.println("PauseOverlay.PauseOverlay()........... Creating PauseOverlay");

		this.playing = playing;
		loadBackground();
		createSoundButtons();
		createUrmButtons();
		createVolumeButtons();
	}
	
	private void createVolumeButtons() {
		int volumeX = (int) (309 * Game.SCALE);
		int volumeY = (int) (278 * Game.SCALE);
		volumeButton = new VolumeButton(volumeX, volumeY, SLIDER_WIDTH, VOLUME_HEIGHT);
	}

	private void createUrmButtons() {
		int menuX = (int) (313 * Game.SCALE);
		int replayX = (int) (387 * Game.SCALE);
		int unpauseX = (int) (462 * Game.SCALE);
		int buttonY = (int) (325 * Game.SCALE);
		
		menuButton = new UrmButton(menuX, buttonY, URM_SIZE, URM_SIZE, 2);
		replayButton = new UrmButton(replayX, buttonY, URM_SIZE, URM_SIZE, 1);
		unpauseButton = new UrmButton(unpauseX, buttonY, URM_SIZE, URM_SIZE, 0);
		
	}

	private void createSoundButtons() {
		int soundX = (int) (450 * Game.SCALE);
		int musicY = (int) (140 * Game.SCALE); // y location on menu
		int sfxY = (int) (186 * Game.SCALE); // y location on menu
		
		musicButton = new SoundButton(soundX, musicY, SOUND_SIZE, SOUND_SIZE);
		sfxButton = new SoundButton(soundX, sfxY, SOUND_SIZE, SOUND_SIZE);
	}

	private void loadBackground() {
		System.out.println("PauseOverlay.loadBackground()......... Loading PauseOverlay background");
		
		backgroundImg = LoadSave.GetSpriteAtlas(LoadSave.PAUSE_BACKGROUND);
		bgW = (int) (backgroundImg.getWidth() * Game.SCALE);
		bgH = (int) (backgroundImg.getHeight() * Game.SCALE);
		bgX = (int) (Game.GAME_WIDTH / 2 - bgW / 2);
		bgY = (int) (25 * Game.SCALE);
	}

	public void update() {
		//System.out.println("PauseOverlay.update()..................... Updating PauseOverlay ");
		musicButton.update();
		sfxButton.update();
		
		menuButton.update();
		replayButton.update();
		unpauseButton.update();
		
		volumeButton.update();
	}
	
	public void draw(Graphics g) {
		//System.out.println("PauseOverlay.draw()..................... Drawing PauseOverlay ");
		
		// background
		g.drawImage(backgroundImg, bgX, bgY, bgW, bgH, null);
		
		// sound buttons
		musicButton.draw(g);
		sfxButton.draw(g);
		
		// urm buttons
		menuButton.draw(g);
		replayButton.draw(g);
		unpauseButton.draw(g);
		
		//volume slider
		volumeButton.draw(g);
	}
	
	public void mouseDragged(MouseEvent e) {
		
		if(volumeButton.isMousePressed()) {
			volumeButton.changeX(e.getX());
		}
		
	}


	public void mousePressed(MouseEvent e) {
		if(isIn(e,musicButton)) {
			musicButton.setMousePressed(true);
		}else if(isIn(e,sfxButton)) {
			sfxButton.setMousePressed(true);
		}else if(isIn(e,menuButton)) {
			menuButton.setMousePressed(true);
		}else if(isIn(e,replayButton)) {
			replayButton.setMousePressed(true);
		}else if(isIn(e,unpauseButton)) {
			unpauseButton.setMousePressed(true);
		}else if(isIn(e,volumeButton)) {
			volumeButton.setMousePressed(true);
		}
	}


	public void mouseReleased(MouseEvent e) {
		if(isIn(e,musicButton)) {
			if(musicButton.isMousePressed()) 
				musicButton.setMuted(!musicButton.isMuted());
		}else if(isIn(e,sfxButton)) {
			if(sfxButton.isMousePressed()) 
				sfxButton.setMuted(!sfxButton.isMuted());
		}else if(isIn(e,menuButton)) {
			if(menuButton.isMousePressed()) {
				Gamestate.state = Gamestate.MENU;
				playing.unpauseGame();
			}
		}else if(isIn(e,replayButton)) {
			if (replayButton.isMousePressed()) {
				playing.resetAll();
				playing.unpauseGame();
			}
		}else if(isIn(e,unpauseButton)) {
			if(unpauseButton.isMousePressed()) 
				playing.unpauseGame();
		}
		
		musicButton.resetBooleans();
		sfxButton.resetBooleans();
		menuButton.resetBooleans();
		replayButton.resetBooleans();
		unpauseButton.resetBooleans();
		volumeButton.resetBooleans();
	}


	public void mouseMoved(MouseEvent e) {
		musicButton.setMouseOver(false);
		sfxButton.setMouseOver(false);
		menuButton.setMouseOver(false);
		replayButton.setMouseOver(false);
		unpauseButton.setMouseOver(false);
		volumeButton.setMouseOver(false);
		
		if(isIn(e,musicButton)) {
			musicButton.setMouseOver(true);
		}else if(isIn(e,sfxButton)) {
			sfxButton.setMouseOver(true);
		}else if(isIn(e,menuButton)) {
			menuButton.setMouseOver(true);
		}else if(isIn(e,replayButton)) {
			replayButton.setMouseOver(true);
		}else if(isIn(e,unpauseButton)) {
			unpauseButton.setMouseOver(true);
		}else if(isIn(e,volumeButton)) {
			volumeButton.setMouseOver(true);
		}
	}
	
	private boolean isIn(MouseEvent e, PauseButton pb ) {
		return pb.getBounds().contains(e.getX(), e.getY());
	}


}
