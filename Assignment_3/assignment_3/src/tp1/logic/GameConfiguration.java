package tp1.logic;

import tp1.logic.gameobjects.*;
import java.util.List;
import tp1.exceptions.GameLoadException;

public interface GameConfiguration {
	   // game status
	   public int getRemainingTime() throws GameLoadException;
	   public int points() throws GameLoadException;
	   public int numLives() throws GameLoadException;
	   
	   // game objects
	   public Mario getMario() throws GameLoadException;
	   public List<GameObject> getNPCObjects() throws GameLoadException;
}
