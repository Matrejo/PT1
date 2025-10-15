package tp1.logic;

import tp1.logic.Ground;
import tp1.logic.gameobjects.Mario;
import tp1.logic.Game;

public class GameObjectContainer {
	public int ground_counter = 0, mario_counter = 0, goomba_counter = 0, door_counter = 0;
	public Ground groundList[] = new Ground[Game.DIM_X * Game.DIM_Y];
	public Mario marioList[] = new Mario[Game.DIM_X * Game.DIM_Y];
	public ExitDoor doorList[] = new ExitDoor[Game.DIM_X * Game.DIM_Y];
	public Goomba goombaList[] = new Goomba[Game.DIM_X * Game.DIM_Y];
	
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
	
	public void doInteractionsFrom(Mario mario) {
		for (int g = 0; g < goomba_counter; g++) {
			mario.interactWith(goombaList[g]);
		}
	}
	
	public void cleanUp() {
		boolean removed = true;
		
		while(removed) {
			removed = false;
			for (int i = 0; i < goomba_counter; i++) {
				if (goombaList[i].eliminated) {
					for (int j = i; j < goomba_counter; j++) {
						goombaList[j] = goombaList[j + 1];
					}
					goomba_counter--;
					removed = true;
				}
			}
		}
	}
	
	public void update(Game game) {
		game.mario.update();
		
		game.mario.mario_actions.deleteActionList();
		
		game.mario.interactWith(game.gameObjects.doorList[0]);
		
		cleanUp();
		
		game.mario.falling = false;
		
		for(int g = 0; g < goomba_counter; g++) {
			this.goombaList[g].update();
		}
		
		game.doInteractionsFrom(game.mario);
		
		cleanUp();
	}
}
