package tp1.logic.gameobjects;

import java.util.Arrays;
import java.util.List;

import tp1.logic.gameobjects.*;
import tp1.control.commands.Command;
import tp1.logic.GameWorld;
import tp1.logic.Position;

public class GameObjectFactory {
	
	private static final List<GameObject> availableObjects = Arrays.asList(
			new Ground(),
			new ExitDoor(),
			new Goomba(),
			new Mario(),
			new Mushroom(),
			new Box()
	);
	
	public static GameObject parse (String objWords[], GameWorld game) {
		GameObject obj = null;
		
		for (GameObject c: availableObjects) {
			if (c.parse(objWords, game) != null) {
				obj = c.parse(objWords, game);
			}
		}
		
		return obj;
	};
}
