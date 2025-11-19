package tp1.logic;

import tp1.logic.gameobjects.GameObject;
import tp1.logic.gameobjects.GameObjectFactory;

public class AddObject {
	private String[] objWords;
	
	public AddObject(String[] newObject, Game game) {
		this.objWords = newObject;
	}
	
	public GameObject addObject(GameWorld game) {
		GameObject newGameObject = GameObjectFactory.parse(objWords, game);
		
		return newGameObject;
	}
}
