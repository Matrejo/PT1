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
	
	public boolean hasSolid(Position pos) {
		boolean found = false;
		
		for (GameObject c : objects) {
			if (c.isInPosition(pos) && c.isSolid()) {
				found = true;
			}
		}
		
		return found;
	}
	
	public String toString(Position pos) {
		String icon = "";
		
		for (GameObject c : objects) {
			if (c.isInPosition(pos) && c.isAlive()) {
				icon = c.getIcon();
			}
		}
		return icon;
	}	
	
	public boolean doInteractions(GameItem obj) {
		boolean interacted = false, auxiliary = false;
		
		for (GameItem c: objects) {
			auxiliary = c.interactWith(obj);
			if (auxiliary) {
				interacted = true;
			}
		}
		
		return interacted;
	}
	
	public void update() {
		for (GameObject c : objects) {
			c.update();
		}
	}

	//TODO fill your code

	// TODO you should write a toString method to return the string that represents the object status
	// @Override
	// public String toString()
}
