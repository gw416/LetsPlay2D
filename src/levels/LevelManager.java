package levels;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.Game;
import utils.LoadSave;

public class LevelManager {

	private Game game;
	private BufferedImage[] levelSprite;
	private Level levelOne;
	
	public LevelManager(Game game) {
		System.out.println("LevelManager.LevelManager()........... Creating LevelManager");
		
		this.game = game;
		importOutsideSprites();
		levelOne = new Level(LoadSave.GetLevel());
	}
	
	private void importOutsideSprites() {
		System.out.println("LevelManager.importOutsideSprites()... Importing outside sprites");
		
		BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.LEVEL_ATLAS);
		levelSprite = new BufferedImage[48]; // level sprite is a 4 tile x 12 tile image
		
		for(int j = 0; j < 4; j++) {
			for(int i = 0; i < 12; i++) {
				int index = j * 12 + i;
				levelSprite[index] = img.getSubimage(i * 32, j * 32, 32, 32);
			}		
		}
	}

	public void draw(Graphics g, int lvlOffset) {
		for(int j = 0; j<Game.TILES_IN_HIEGHT; j++) {
			for(int i = 0; i< levelOne.getLevelData()[0].length; i++) {
				int index = levelOne.getSpritePosition(i, j);
				g.drawImage(levelSprite[index], Game.TILES_SIZE * i - lvlOffset, Game.TILES_SIZE * j, Game.TILES_SIZE, Game.TILES_SIZE, null);
			}
		}
	}
	public void update() {
		
	}
	
	public Level getCurrentLevel() {
		return levelOne;
	}
}
