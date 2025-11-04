package tp1.logic.gameobjects;

import tp1.logic.Game;
import tp1.logic.Position;
import tp1.view.Messages;
import tp1.logic.Action;
import tp1.logic.ActionList;

public class Mario extends GameObject{
	private Position big_pos;
	private Action action = Action.UP;
	private ActionList mario_actions;
	private String mario;
	private Game game;
	private boolean right = true, moving = true, big = true;
	private boolean falling = false;
	private boolean update = false, damaged = false;
	
	public Mario(Game game, Position new_pos) { 
		super (game, new_pos, true);
		this.big_pos = this.pos.add_y(pos, -1);
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
	
	public void update() {
		
		if (!update) {
			for (int i = 0; i < this.mario_actions.ActionListLength(); i++) {
				switch(this.mario_actions.action_list[i]) {
				case UP:
					if (big) {
						if(!game.hasGround(this.pos.add_y(this.pos, -1)) && !game.hasGround(big_pos.add_y(big_pos, -1))) {
							this.move(Action.UP);
							this.big_pos = this.pos.add_y(this.pos, -1);
						}
					}
					else {
						if(!game.hasGround(this.pos.add_y(this.pos, -1))) {
							this.move(Action.UP);
							this.big_pos = this.pos.add_y(this.pos, -1);
						}
					}
					break;
				
				case DOWN:
					while(!game.hasGround(this.pos.add_y(this.pos, 1)) && !this.pos.outOfBounds()) {
						this.move(Action.DOWN);
						this.big_pos = this.pos.add_y(pos, 1);
					}
					falling = true;
					moving = false;
					break;
				
				case LEFT:
					if (big) {
						if(!game.hasGround(big_pos.add_x(big_pos, -1)) && !game.hasGround(this.pos.add_x(this.pos, -1))) {
							this.move(Action.LEFT);
							this.big_pos = this.pos.add_x(this.pos, -1);
							moving = true;
							right = false;
						}
					}
					else {
						if(!game.hasGround(this.pos.add_x(this.pos, -1))) {
							this.move(Action.LEFT);
							this.big_pos = this.pos.add_x(this.pos, -1);
							moving = true;
							right = false;
						}
					}
					break;
				
				case RIGHT:
					if (big) {
						if(!game.hasGround(big_pos.add_x(big_pos, 1)) && !game.hasGround(this.pos.add_x(this.pos, 1))) {
							this.move(Action.RIGHT);
							this.big_pos = this.pos.add_x(this.pos, 1);
							moving = true;
							right = true;
						}
					}
					else {
						if(!game.hasGround(this.pos.add_x(this.pos, 1))) {
							this.move(Action.RIGHT);
							this.big_pos = this.pos.add_x(this.pos, 1);
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
			if((!game.hasGround(this.pos.add_x(this.pos, 1)) && !game.hasGround(big_pos.add_x(big_pos, 1))) && right && !this.pos.add_x(this.pos, 1).outOfBounds()) {
				this.move(Action.RIGHT);
				this.big_pos = this.pos.add_x(this.pos, 1);
				moving = true;
			}
			
			else {
				if((!game.hasGround(this.pos.add_x(this.pos, -1)) && !game.hasGround(big_pos.add_x(big_pos, -1))) && !this.pos.add_x(this.pos, -1).outOfBounds()) {
					this.move(Action.LEFT);
					this.big_pos = this.pos.add_x(this.pos, -1);
					moving = true;
					right = false;
				}
				
				else if ((!game.hasGround(this.pos.add_x(this.pos, 1)) && !game.hasGround(big_pos.add_x(big_pos, 1))) && !this.pos.add_x(this.pos, 1).outOfBounds()){
					this.move(Action.RIGHT);
					this.big_pos = this.pos.add_x(this.pos, 1);
					moving = true;
					right = true;
				}
			}
			
			game.doInteractionsFrom(this);
		}

		}
	
	public boolean receiveInteraction(Goomba goomba) {
		boolean interacted = false;
		
		if (goomba.isInPosition(this.pos)) {
			interacted = true;
			if(!this.falling) {
				if (!damaged) {
					damaged = true;
				}
				else {
					this.dead();
				}
			}
		}
		
		else if (goomba.isInPosition(big_pos) && !damaged) {
			interacted = true;
			damaged = true;
		}
		
		return interacted;
	}
	
	
	public boolean marioOutOfBounds() {
		return this.pos.outOfBounds();
	}
	
	public boolean isInPosition(Position pos) {
		return this.pos.equals(pos) || big_pos.equals(pos);
	}
	
	public boolean interactWith(GameItem other) {
		boolean interacted;
	  	
	  	interacted = other.receiveInteraction(this);
		
		return interacted;
	 }
}