package tp1.logic;

public interface GameStatus {
	public String positionToString(int col, int row);
	public boolean playerWins();
	public int remainingTime();
	public int points();
	public int numLives();
	public String toString();
	public boolean playerLoses();
}
