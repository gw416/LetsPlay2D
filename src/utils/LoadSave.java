package utils;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import entities.Crabby;
import main.Game;
import static utils.Constants.EnemyConstants.CRABBY;

/**
 * Level Creation, load level, save level, modify level, load game, save game
 * 
 * @author Greg
 *
 */
public class LoadSave {

	public static final String PLAYER_ATLAS = "player_sprites.png";
	public static final String LEVEL_ATLAS = "outside_sprites.png";
	//public static final String LEVEL_ONE = "level_one_data.png";
	public static final String LEVEL_ONE = "level_one_data_long.png";
	public static final String MENU_BUTTONS = "button_atlas.png";
	public static final String MENU_BACKGROUND = "menu_background.png";
	public static final String PAUSE_BACKGROUND = "pause_background.png";
	public static final String SOUNDS_BUTTONS = "sound_button.png";
	public static final String URM_BUTTONS = "urm_buttons.png"; // unpause replay menu buttons ??
	public static final String VOLUME_BUTTONS = "volume_buttons.png"; // volume slider
	public static final String MENU_BACKGROUND_IMG = "background_menu.png"; // volume slider
	public static final String PLAYING_BG_IMG = "playing_bg_img.png";
	public static final String SMALL_CLOUDS = "small_clouds.png";
	public static final String BIG_CLOUDS = "big_clouds.png";
	public static final String CRABBY_SPRITE = "crabby_sprite.png";
	
	public static BufferedImage GetSpriteAtlas(String fileName) {
		BufferedImage img = null;
		try (InputStream is = LoadSave.class.getResourceAsStream("/" + fileName)) {
			img = ImageIO.read(is);
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		return img;
	}
	
	public static ArrayList<Crabby> GetCrabs(){
		
		BufferedImage img = GetSpriteAtlas(LEVEL_ONE);
		ArrayList<Crabby> list = new ArrayList<Crabby>();

		for(int j=0; j< img.getHeight(); j++) {
			for(int i=0; i<img.getWidth(); i++) {
				Color color = new Color(img.getRGB(i, j)); // X, Y
				int value = color.getGreen();
				if(value == CRABBY)
					list.add(new Crabby(i * Game.TILES_SIZE, j * Game.TILES_SIZE));
			}
		}
		return list;
	}
	
	// use level data to find the amtching level sprite. level data will be either red green or blue
	public static int[][] GetLevel() {
		
		BufferedImage img = GetSpriteAtlas(LEVEL_ONE);
		int[][] levelData = new int[img.getHeight()][img.getWidth()];
		
		for(int j=0; j< img.getHeight(); j++) {
			for(int i=0; i<img.getWidth(); i++) {
				Color color = new Color(img.getRGB(i, j)); // X, Y
				int value = color.getRed();
				if(value >= 48)
					value = 0;
				levelData[j][i] = value; // prevent null pointer by hard coding 12x4
			}
		}
		return levelData;
	}
}
