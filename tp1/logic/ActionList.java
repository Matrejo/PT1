package tp1.logic;

import tp1.logic.Action;
import tp1.logic.Game;

public class ActionList {
	private int counter = 0;
	Action action_list[];
	private int did_up = 0, did_down = 0, did_right = 0, did_left = 0;
	
	public ActionList(int size) {
		this.action_list = new Action[size];
	}
	
	public void addAction(Action new_action) {
		action_list[counter] = new_action;
		this.counter++;
	}
	
	public void doActions(Game game) {
		for (int i = 0; i < this.action_list.length; i++) {
			switch(this.action_list[i]) {
			
			case Action.UP:
				if (did_down == 0 && did_up < 5) {
					game.mario.MoveMario(Action.UP);
					did_up++;
				}
				break;
				
			case Action.DOWN:
				if (did_up == 0 && did_down < 5) {
					game.mario.MoveMario(Action.DOWN);
					did_down++;
				}
				break;
				
			case Action.LEFT:
				if (did_right == 0 && did_left < 5) {
					game.mario.MoveMario(Action.LEFT);
					did_left++;
				}
				break;
				
			case Action.RIGHT:
				if (did_left == 0 && did_right < 5) {
					game.mario.MoveMario(Action.RIGHT);
					did_right++;
				}
				break;
			}
		}
	}
	
	public int ActionListLength() {
		return this.action_list.length;
	}
}
