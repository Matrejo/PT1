package tp1.logic;

public interface GameModel {
	public boolean isFinished();
	public void update();
	public void restart();
	public void doMarioActions(Action[] actions);
	public void exit();
	public void setLevel(int level);
	public boolean newObject(String[] newObject);
}
