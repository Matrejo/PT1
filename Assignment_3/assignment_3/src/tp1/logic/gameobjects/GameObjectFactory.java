package tp1.logic.gameobjects;

import java.util.Arrays;

import java.util.List;

import tp1.logic.gameobjects.*;
import tp1.view.Messages;
import tp1.control.commands.Command;
import tp1.logic.GameWorld;
import tp1.logic.Position;
import tp1.exceptions.ObjectParseException;
import tp1.exceptions.OffBoardException;
import tp1.exceptions.GameModelException;
import tp1.exceptions.GameModelParseException;

public class GameObjectFactory {
	
	private static final List<GameObject> availableObjects = Arrays.asList(
			new Ground(),
			new ExitDoor(),
			new Goomba(),
			new Mario(),
			new Mushroom(),
			new Box()
	);
	
	public static GameObject parse (String objWords[], GameWorld game) throws GameModelException, GameModelParseException {
		
		for (GameObject c: availableObjects) {
			GameObject obj = c.parse(objWords, game);
			if (obj !=null) {
				return obj;
			}
		}
		
		String errorObject = "";
		
		for (int i = 0; i < objWords.length; i++) {
			errorObject = errorObject + objWords[i] + " ";
		}
		throw new ObjectParseException(Messages.OBJECT_ERROR.formatted(errorObject));
	};
}
