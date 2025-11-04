package tp1.logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import tp1.logic.gameobjects.GameObject;
import tp1.logic.gameobjects.GameItem;
import tp1.view.Messages;

public class GameObjectContainer {
	private ArrayList<GameObject> objects;

	public GameObjectContainer() {
		objects = new ArrayList<GameObject>();
	}
	
	// Only one add method (polymorphism)
	public void add(GameObject object) {
		objects.add(object);
	}
	
	public boolean hasGround(Position pos) {
		boolean found = false;
		if(positionToString(pos) == Messages.LAND) {
			found = true;
		}
		
		return found;
	}
	
	public String positionToString(Position pos) {
		String icon = "";
		
		for (GameObject c : objects) {
			if (c.isInPosition(pos)) {
				icon = c.getIcon();
			}
		}
		return icon;
	}	
	
	public boolean doInteractions(GameItem obj) {
		
		for (GameObject c: objects) {
			c.interactWith(obj);
		}
		return true;
	}

	//TODO fill your code

	// TODO you should write a toString method to return the string that represents the object status
	// @Override
	// public String toString()
}
