package tp1.logic.gameobjects;

import tp1.logic.Position;
import tp1.logic.gameobjects.GameObject;
import tp1.logic.Game;
import tp1.view.Messages;

public class ExitDoor extends GameObject{
	private Position pos;
	
	public ExitDoor(Game game, Position new_pos) {
		super(game, new_pos, false);
		this.dead();
	}
	
	public boolean IsInPos(Position pos) {
		return this.pos.equals(pos);
	}
	
	public String getIcon() {
		return Messages.EXIT_DOOR;
	}
	
	public void update() {
		
	};
	
	public boolean interactWith(GameItem other) {
	  	boolean interacted;
	  	
	  	interacted = other.receiveInteraction(this);
		
		return interacted;
	 }
	
	public boolean receiveInteraction(Mario mario) {
		boolean interacted = false;
		
		if (mario.isInPosition(this.pos)) {
			interacted = true;
			this.game.playerWins();
		}
		
		return interacted;
	}
}
