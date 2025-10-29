package tp1.logic;

import tp1.view.Messages;

public class Ground{
	
	private Position pos;
	
	public Ground(Position new_pos) {
		this.pos = new_pos;
	}
	
	public boolean IsInPos(Position pos) {
		return this.pos.equals(pos);
	}
	
	public String getIcon() {
		return Messages.LAND;
	}
	
	
}