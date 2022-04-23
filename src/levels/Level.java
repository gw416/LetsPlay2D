package levels;

public class Level {

	private int[][] levelData;
	
	public Level(int[][] levelData) {
		System.out.println("Level.Level()......................... Creating Level");
		this.levelData = levelData;
	}
	
	public int getSpritePosition(int x, int y) {
		return levelData[y][x];
	}
	
	public int[][] getLevelData(){
		return levelData;
	}
}
