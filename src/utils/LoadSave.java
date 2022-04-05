package utils;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import main.Game;

/**
 * Level Creation, load level, save level, modify level, load game, save game
 * 
 * @author Greg
 *
 */
public class LoadSave {

	public static final String PLAYER_ATLAS = "player_sprites.png";
	public static final String LEVEL_ATLAS = "exterior_level_sprites.png";
	public static final String LEVEL_ONE = "level_one_data.png";
	
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
	
	// use level data to find the amtching level sprite. level data will be either red green or blue
	public static int[][] GetLevel() {
		int[][] levelData = new int[Game.TILES_IN_HIEGHT][Game.TILES_IN_WIDTH];
		BufferedImage img = GetSpriteAtlas(LEVEL_ONE);
		
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
