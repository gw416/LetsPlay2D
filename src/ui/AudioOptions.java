package ui;

import static utils.Constants.UI.PauseButtons.SOUND_SIZE;
import static utils.Constants.UI.VolumeButtons.SLIDER_WIDTH;
import static utils.Constants.UI.VolumeButtons.VOLUME_HEIGHT;

import java.awt.Graphics;
import java.awt.event.MouseEvent;

import main.Game;

public class AudioOptions {
	
	private VolumeButton volumeButton;
	private SoundButton musicButton, sfxButton;
	private Game game;
	
	public AudioOptions(Game game) {
		this.game = game;
		createSoundButtons();
		createVolumeButtons();
	}
	
	private void createVolumeButtons() {
		int volumeX = (int) (309 * Game.SCALE);
		int volumeY = (int) (278 * Game.SCALE);
		volumeButton = new VolumeButton(volumeX, volumeY, SLIDER_WIDTH, VOLUME_HEIGHT);
	}
	
	private void createSoundButtons() {
		int soundX = (int) (450 * Game.SCALE);
		int musicY = (int) (140 * Game.SCALE); // y location on menu
		int sfxY = (int) (186 * Game.SCALE); // y location on menu
		musicButton = new SoundButton(soundX, musicY, SOUND_SIZE, SOUND_SIZE);
		sfxButton = new SoundButton(soundX, sfxY, SOUND_SIZE, SOUND_SIZE);
	}
	
	public void update() {
		musicButton.update();
		sfxButton.update();
		volumeButton.update();
	}
	
	public void draw(Graphics g) {
		musicButton.draw(g);
		sfxButton.draw(g);
		volumeButton.draw(g);//volume slider
	}
	
	public void mouseDragged(MouseEvent e) {
		if(volumeButton.isMousePressed()) {
			float valueBefore = volumeButton.getFloatValue();
			volumeButton.changeX(e.getX());
			float valueAfter = volumeButton.getFloatValue();
			
			if(valueBefore != valueAfter)
				game.getAudioPlayer().setVolume(valueAfter);
		}	
	}

	public void mousePressed(MouseEvent e) {
		if(isIn(e,musicButton)) {
			musicButton.setMousePressed(true);
		}else if(isIn(e,sfxButton)) {
			sfxButton.setMousePressed(true);
		}else if(isIn(e,volumeButton)) {
			volumeButton.setMousePressed(true);
		}
	}

	public void mouseReleased(MouseEvent e) {
		if(isIn(e,musicButton)) {
			if(musicButton.isMousePressed()) {
				musicButton.setMuted(!musicButton.isMuted());
				game.getAudioPlayer().toggleSongMute();
			}
		}else if(isIn(e,sfxButton)) {
			if(sfxButton.isMousePressed()) {
				sfxButton.setMuted(!sfxButton.isMuted());
				game.getAudioPlayer().toggleEffectMute();
			}
		}
		musicButton.resetBooleans();
		sfxButton.resetBooleans();
		volumeButton.resetBooleans();
	}

	public void mouseMoved(MouseEvent e) {
		musicButton.setMouseOver(false);
		sfxButton.setMouseOver(false);
		volumeButton.setMouseOver(false);
		
		if(isIn(e,musicButton)) {
			musicButton.setMouseOver(true);
		}else if(isIn(e,sfxButton)) {
			sfxButton.setMouseOver(true);
		}else if(isIn(e,volumeButton)) {
			volumeButton.setMouseOver(true);
		}
	}
	
	private boolean isIn(MouseEvent e, PauseButton pb ) {
		return pb.getBounds().contains(e.getX(), e.getY());
	}
}
