package tp1.logic.gameobjects;

import tp1.logic.Game;
import tp1.logic.GameObjectContainer;
import tp1.logic.Position;
import tp1.view.Messages;
import tp1.logic.Action;

public class Mario {
	private Position small_pos, big_pos;
	private Action action = Action.UP;
	private String mario;
	private Game game;
	private boolean right = true, moving = true, big = true, falling = false;
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
	
	public void update(Action mario_action) {
		
	if (!update) {
		switch(mario_action) {
		case UP:
			if (big) {
				if(!game.hasGround(big_pos.add_y(big_pos, -1))) {
					this.small_pos = action.moveUp(small_pos);
					this.big_pos = action.moveUp(big_pos);
					moving = true;
				}
			}
			else {
				if(!game.hasGround(small_pos.add_y(small_pos, -1))) {
					this.small_pos = action.moveUp(small_pos);
					this.big_pos = action.moveUp(big_pos);
					moving = true;
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
				}
			}
			else {
				if(!game.hasGround(small_pos.add_x(small_pos, -1))) {
					this.small_pos = action.moveLeft(small_pos);
					this.big_pos = action.moveLeft(big_pos);
					moving = true;
				}
			}
			break;
		
		case RIGHT:
			if (big) {
				if(!game.hasGround(big_pos.add_x(big_pos, 1)) && !game.hasGround(small_pos.add_x(small_pos, 1))) {
					this.small_pos = action.moveRight(small_pos);
					this.big_pos = action.moveRight(big_pos);
					moving = true;
				}
			}
			else {
				if(!game.hasGround(small_pos.add_x(small_pos, 1))) {
					this.small_pos = action.moveRight(small_pos);
					this.big_pos = action.moveRight(big_pos);
					moving = true;
				}
			}
			break;
		}
		}
	
	else if (moving) {
		if(!game.hasGround(small_pos.add_x(small_pos, 1)) && right) {
			this.small_pos = action.moveRight(small_pos);
			this.big_pos = action.moveRight(big_pos);
			moving = true;
		}
		
		else { 
			if(!game.hasGround(small_pos.add_x(small_pos, -1))) {
				this.small_pos = action.moveLeft(small_pos);
				this.big_pos = action.moveLeft(big_pos);
				moving = true;
				right = false;
			}
			
			else {
				this.small_pos = action.moveRight(small_pos);
				this.big_pos = action.moveRight(big_pos);
				moving = true;
				right = true;
			}
		}
	}
	
	game.remaining_time--;
	
	if(!falling) {
		if(game.hasGround(small_pos.add_y(small_pos, 1)) && (game.eliminateGoomba(small_pos.add_x(small_pos, 1)) || game.eliminateGoomba(big_pos.add_y(big_pos, -1)))) {
			if(big)
				big = false;
			else
				damaged = true;
		}
		
		else if(game.eliminateGoomba(big_pos.add_y(big_pos, -1))) {
			if(big)
				big = false;
			else
				damaged = true;
		}
	}
	else {
		if(game.eliminateGoomba(small_pos.add_x(small_pos, 1)))
			game.changeNumPoints(100);
	}
	if(game.hasDoor(small_pos))
		game.won = true;
	
	game.updateGoombas();
	
	falling = false;
	
	}
	
	
	public boolean marioOutOfBounds() {
		return small_pos.outOfBounds();
	}
}
