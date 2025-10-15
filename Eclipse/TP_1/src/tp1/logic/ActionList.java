package tp1.logic;

import tp1.logic.Action;
import tp1.logic.Game;

public class ActionList {
	private int counter = 0;
	public Action action_list[], action = Action.STOP;
	private int did_up = 0, did_down = 0, did_right = 0, did_left = 0, did_stop = 0;
	
	public ActionList(int size) {
		this.action_list = new Action[size];
	}
	
	public void addAction(Action new_action) {
		action_list[counter] = new_action;
		this.counter++;
	}
	
	public void parseCommands(String commands[]) {
		action.parseCommands(commands, this);
	}
	
	public int ActionListLength() {
		return this.counter;
	}
	
	public void deleteActionList() {
		for (int i = 0; i < this.action_list.length; i++) {
			this.action_list[i] = null;
		}
		this.counter = 0;
	}
}
