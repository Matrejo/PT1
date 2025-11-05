package tp1.logic.gameobjects;

import tp1.logic.Game;
import tp1.logic.Position;
import tp1.view.Messages;
import tp1.logic.Action;

public class Goomba extends GameObject{	
	private boolean right = false;
	
	public Goomba(Game game, Position new_pos) {
		super(game, new_pos, false);
	}
	
	public String getIcon() {
		return Messages.GOOMBA;
	}
	
	public void update() {
		if (this.interactWith(null))
			this.move(Action.LEFT);
		
		else if (right && !this.pos.add_x(pos, 1).outOfBounds() && !game.hasGround(this.pos.add_x(pos, 1)))
			this.move(Action.RIGHT);
		
		else {
			if(!this.pos.add_x(pos, -1).outOfBounds() && !game.hasGround(this.pos.add_x(pos, -1))) {
				this.move(Action.LEFT);;
				right = false;
			}
			else if (!this.pos.add_x(pos, 1).outOfBounds() && !game.hasGround(this.pos.add_x(pos, 1))){
				this.move(Action.RIGHT);
				right = true;
			}
		}
	}
	
	public boolean recieveInteraction(Mario mario) {
		boolean interacted = false;
		
		if (mario.isInPosition(this.pos)) {
			interacted = true;
			this.dead();
		}
		
		return interacted;
	}
	
	
	public boolean interactWith(GameItem other) {
		boolean interacted = false;
	  	
		if (other != null)
			interacted = other.receiveInteraction(this);
		
		if (interacted) {
			this.dead();
		}
		
		return interacted;
	 }
}
