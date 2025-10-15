package tp1.logic.gameobjects;

import tp1.logic.Game;
import tp1.logic.Position;
import tp1.view.Messages;
import tp1.logic.Action;
import tp1.logic.ActionList;
import tp1.logic.ExitDoor;
import tp1.logic.Goomba;

public class Mario {
	private Position small_pos, big_pos;
	private Action action = Action.UP;
	public ActionList mario_actions;
	private String mario;
	private Game game;
	private boolean right = true, moving = true, big = true;
	public boolean falling = false;
	public boolean update = false, damaged = false;
	
	public Mario(Game game, Position new_pos) { 
		this.game = game;
		this.small_pos = new_pos;
		this.big_pos = action.moveUp(new_pos);
	}
	
	public String getIcon() {
		
		if (right) {
			mario = Messages.MARIO_RIGHT;
		}
		
		else {
			mario = Messages.MARIO_LEFT;
		}
		
		if(!moving) {
			mario = Messages.MARIO_STOP;
		}
		
		return mario;
	}
	
	public boolean IsBig() {
		return big;			
	}
	
	public boolean IsInPos(Position pos) {
		if (big)
			return this.big_pos.equals(pos) || this.small_pos.equals(pos);
		else
			return this.small_pos.equals(pos);
	}
	
	public void update() {
		
	if (!update) {
		for (int i = 0; i < this.mario_actions.ActionListLength(); i++) {
			switch(this.mario_actions.action_list[i]) {
			case UP:
				if (big) {
					if(!game.hasGround(big_pos.add_y(big_pos, -1))) {
						this.small_pos = action.moveUp(small_pos);
						this.big_pos = action.moveUp(big_pos);
					}
				}
				else {
					if(!game.hasGround(small_pos.add_y(small_pos, -1))) {
						this.small_pos = action.moveUp(small_pos);
						this.big_pos = action.moveUp(big_pos);
					}
				}
				break;
			
			case DOWN:
				while(!game.hasGround(small_pos.add_y(small_pos, 1)) && !this.small_pos.outOfBounds()) {
					this.small_pos = action.moveDown(small_pos);
					this.big_pos = action.moveDown(big_pos);
				}
				falling = true;
				moving = false;
				break;
			
			case LEFT:
				if (big) {
					if(!game.hasGround(big_pos.add_x(big_pos, -1)) && !game.hasGround(small_pos.add_x(small_pos, -1))) {
						this.small_pos = action.moveLeft(small_pos);
						this.big_pos = action.moveLeft(big_pos);
						moving = true;
						right = false;
					}
				}
				else {
					if(!game.hasGround(small_pos.add_x(small_pos, -1))) {
						this.small_pos = action.moveLeft(small_pos);
						this.big_pos = action.moveLeft(big_pos);
						moving = true;
						right = false;
					}
				}
				break;
			
			case RIGHT:
				if (big) {
					if(!game.hasGround(big_pos.add_x(big_pos, 1)) && !game.hasGround(small_pos.add_x(small_pos, 1))) {
						this.small_pos = action.moveRight(small_pos);
						this.big_pos = action.moveRight(big_pos);
						moving = true;
						right = true;
					}
				}
				else {
					if(!game.hasGround(small_pos.add_x(small_pos, 1))) {
						this.small_pos = action.moveRight(small_pos);
						this.big_pos = action.moveRight(big_pos);
						moving = true;
						right = true;
					}
				}
				break;
				
			case STOP:
				moving = false;
				break;
			}
			game.doInteractionsFrom(this);
		}
		}
	
	else if (moving) {
		if((!game.hasGround(small_pos.add_x(small_pos, 1)) && !game.hasGround(big_pos.add_x(big_pos, 1))) && right && !small_pos.add_x(small_pos, 1).outOfBounds()) {
			this.small_pos = action.moveRight(small_pos);
			this.big_pos = action.moveRight(big_pos);
			moving = true;
		}
		
		else {
			if((!game.hasGround(small_pos.add_x(small_pos, -1)) && !game.hasGround(big_pos.add_x(big_pos, -1))) && !small_pos.add_x(small_pos, -1).outOfBounds()) {
				this.small_pos = action.moveLeft(small_pos);
				this.big_pos = action.moveLeft(big_pos);
				moving = true;
				right = false;
			}
			
			else if ((!game.hasGround(small_pos.add_x(small_pos, 1)) && !game.hasGround(big_pos.add_x(big_pos, 1))) && !small_pos.add_x(small_pos, 1).outOfBounds()){
				this.small_pos = action.moveRight(small_pos);
				this.big_pos = action.moveRight(big_pos);
				moving = true;
				right = true;
			}
		}
		
		game.doInteractionsFrom(this);
	}
	
	}
	
	public boolean interactWith(ExitDoor other) {
		boolean interacted = false;
		
		if (other.IsInPos(small_pos)) {
			game.marioExited();
			interacted = true;
		}
		
		return interacted;
	}
	
	public boolean interactWith(Goomba other) {
		boolean interacted = false;
		
		if (other.IsInPos(small_pos)) {
			if (!falling) {
				if(other.receiveInteraction(this)) {
					game.changeNumPoints(100);
				}
				if(big) {
					big = false;
				}
				else {
					damaged = true;
				}
			}
			
			else {
				other.receiveInteraction(this);
				game.changeNumPoints(100);
			}
		}
		
		else if(other.IsInPos(big_pos) && big) {
			other.receiveInteraction(this);
			game.changeNumPoints(100);
			big = false;
			
		}
		
		return interacted;
	}
	
	
	public boolean marioOutOfBounds() {
		return small_pos.outOfBounds();
	}
}
