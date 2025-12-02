package tp1.logic;

import tp1.logic.gameobjects.GameObject;
import tp1.logic.gameobjects.GameObjectFactory;
import tp1.exceptions.*;
import tp1.exceptions.GameModelException;

public class AddObject {
	private String[] objWords;
	
	public AddObject(String[] newObject, Game game) {
		this.objWords = newObject;
	}
	
	public GameObject addObject(GameWorld game) throws GameModelException{
		GameObject newGameObject = GameObjectFactory.parse(objWords, game);
		
		return newGameObject;
	}
}
