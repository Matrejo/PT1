package tp1.logic;


public class ActionList {
	private int counter = 0;
	public Action action_list[], action = Action.STOP;
	
	public ActionList(int size) {
		this.action_list = new Action[size];
	}
	
	public void addAction(Action new_action) {
		action_list[counter] = new_action;
		this.counter++;
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
