package tp1.logic;

import tp1.logic.Ground;
import tp1.logic.gameobjects.Mario;

public class GameObjectContainer {
	int ground_counter = 0, mario_counter = 0, goomba_counter = 0, door_counter = 0;
	Ground groundList[] = new Ground[Game.DIM_X * Game.DIM_Y];
	Mario marioList[] = new Mario[Game.DIM_X * Game.DIM_Y];
	ExitDoor doorList[] = new ExitDoor[Game.DIM_X * Game.DIM_Y];
	Goomba goombaList[] = new Goomba[Game.DIM_X * Game.DIM_Y];
	
	public void add(Ground ground) {
		groundList[ground_counter] = ground;
		ground_counter++;
	}
	
	public void add(Mario mario) {
		marioList[mario_counter] = mario;
		mario_counter++;
	}
	
	public void add(ExitDoor door) {
		doorList[door_counter] = door;
		door_counter++;
	}
	
	public void add(Goomba goomba) {
		goombaList[goomba_counter] = goomba;
		goomba_counter++;
	}
}
