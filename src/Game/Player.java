package Game;

public class Player {
	public Color color; 
	private int score;
	
	/**
	 * Copy constructor
	 * @param player
	 */
	public Player(Player player) {
		this.color = player.color;
		this.score = player.score;
	}
	
	/**
	 * Player constructor
	 * @param color
	 */
	public Player(Color color) {
		this.color = color;
		this.score = 0;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	public int getScore() {
		return this.score;
	}
}
