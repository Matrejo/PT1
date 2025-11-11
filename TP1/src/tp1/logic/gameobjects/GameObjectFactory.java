package tp1.logic.gameobjects;

import java.util.Arrays;
import java.util.List;

import tp1.logic.gameobjects.*;
import tp1.control.commands.Command;
import tp1.logic.Game;
import tp1.logic.Position;

public class GameObjectFactory {
	
	private static final List<GameObject> availableObjects = Arrays.asList(
			new Ground(new Game(-1), new Position(0, 0)),
			new ExitDoor(new Game(-1), new Position(0, 0)),
			new Goomba(new Game(-1), new Position(0, 0)),
			new Mario(new Game(-1), new Position(0, 0))
	);
	
	public static GameObject parse (String objWords[], Game game) {
		GameObject obj = null;
		
		for (GameObject c: availableObjects) {
			if (c.parse(objWords, game) != null) {
				obj = c.parse(objWords, game);
			}
		}
		
		return obj;
	};
}
