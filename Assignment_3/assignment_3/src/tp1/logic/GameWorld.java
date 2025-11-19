package tp1.logic;

import tp1.logic.gameobjects.GameItem;

public interface GameWorld {
	public void doInteractionsFrom(GameItem obj);
	public void addPoints(int new_points);
	public void addMushroom(Position pos);
	public boolean isSolid(Position pos);
	public void reachedDoor();
}
