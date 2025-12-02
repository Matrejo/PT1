package tp1.logic.gameobjects;

import tp1.logic.GameWorld;
import tp1.logic.Position;
import tp1.view.Messages;
import tp1.logic.Action;
import tp1.exceptions.OffBoardException;
import tp1.exceptions.ObjectParseException;
import tp1.exceptions.PositionParseException;

public class Mario extends MovingObject{
	private Position big_pos;
	private Action[] mario_actions = null;
	private String mario;
	private static final String NAME = "mario";
    private static final String SHORTCUT = "m";
	private boolean moving = true, big = true, updated = false;
	
	public Mario(GameWorld game, Position new_pos) { 
		super (game, new_pos, false);
		this.big_pos = this.pos.add_y(pos, -1);
	}
	
	public Mario() {}
	
	public void setActions(Action[] actions) {
		this.mario_actions = actions;
	}
	
	public String toString() {
		StringBuilder objectString = new StringBuilder();
		
		if (moving) {
			objectString.append(super.toString()).append(' ');
		}
		
		else {
			objectString.append(this.pos.toString()).append(' ').append(getName()).append(' ').append("STOP").append(' ');
		}
		
		if (big) {
			objectString.append("BIG");
		}
		
		else {
			objectString.append("SMALL");
		}
		
		
		
		return objectString.toString();
	}
	
	public String getShortcut() {
		return SHORTCUT;
	}
	
	public String getName() {
		return NAME;
	}
	
	public GameObject parse(String objWords[]) throws OffBoardException, ObjectParseException, PositionParseException{
		GameObject object = null;
		
		object = super.parse(objWords, game);
		
		return object;
	}
	
	public void makeBig() {
		this.big = true;
	}
	
	public void makeSmall() {
		this.big = false;
	}
	
	public String getIcon() {
		
		if (getRight()) {
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
	
	public Mario createInstance(String[] info, GameWorld game) throws OffBoardException, PositionParseException{
		Mario new_mario = new Mario(this.game, pos.coordsToPos(info[0]));
		
		if (info.length >= 3) {
			if(info[2].equalsIgnoreCase("left")) {
				setRight(false);
			}
			else if (info[2].equalsIgnoreCase("small")) {
				new_mario.makeSmall();
			}
			else if (info[2].equalsIgnoreCase("stop")) {
				new_mario.stop();
			}
			
			if(info.length == 4) {
				if(info[3].equalsIgnoreCase("left")) {
					setRight(false);
				}
				else if (info[3].equalsIgnoreCase("small")) {
					new_mario.makeSmall();
				}
				else if (info[3].equalsIgnoreCase("stop")) {
					new_mario.stop();
				}
			}
		}
		
		return new_mario;
	}
	
	public void stop() {
		this.moving = false;
	}
	
	public boolean IsBig() {
		return big;			
	}
	
	public void moveMario() {
		
		if (this.mario_actions != null) {
			for (int i = 0; i < this.mario_actions.length; i++) {
				switch(this.mario_actions[i]) {
				case UP:
					if (big) {
						if(!game.isSolid(this.pos.add_y(this.pos, -1)) && !game.isSolid(big_pos.add_y(big_pos, -1))) {
							this.move(Action.UP);
							this.big_pos = this.pos.add_y(this.pos, -1);
						}
					}
					else {
						if(!game.isSolid(this.pos.add_y(this.pos, -1))) {
							this.move(Action.UP);
							this.big_pos = this.pos.add_y(this.pos, -1);
						}
					}
					break;
				
				case DOWN:
					setFalling(true);
					while(!game.isSolid(this.pos.add_y(this.pos, 1)) && !this.pos.outOfBounds()) {
						this.move(Action.DOWN);
						this.big_pos = this.pos.add_y(pos, -1);
						game.doInteractionsFrom(this);
					}
					moving = false;
					break;
				
				case LEFT:
					if (big) {
						if(!game.isSolid(big_pos.add_x(big_pos, -1)) && !game.isSolid(this.pos.add_x(this.pos, -1))) {
							this.move(Action.LEFT);
							this.big_pos = this.pos.add_y(this.pos, -1);
							moving = true;
							setRight(false);
						}
					}
					else {
						if(!game.isSolid(this.pos.add_y(this.pos, -1))) {
							this.move(Action.LEFT);
							this.big_pos = this.pos.add_y(this.pos, -1);
							moving = true;
							setRight(false);
						}
					}
					break;
				
				case RIGHT:
					if (big) {
						if(!game.isSolid(big_pos.add_x(big_pos, 1)) && !game.isSolid(this.pos.add_x(this.pos, 1))) {
							this.move(Action.RIGHT);
							this.big_pos = this.pos.add_y(this.pos, -1);
							moving = true;
							setRight(true);
						}
					}
					else {
						if(!game.isSolid(this.pos.add_x(this.pos, 1))) {
							this.move(Action.RIGHT);
							this.big_pos = this.pos.add_y(this.pos, -1);
							moving = true;
							setRight(true);
						}
					}
					break;
					
				case STOP:
					moving = false;
					break;
				}
				game.doInteractionsFrom(this);
			}
			
			this.mario_actions = null;
		}
		
		updated = true;
		game.doInteractionsFrom(this);
		setFalling(false);
	}

	public void update() {
		if (!updated && moving) {
			if((!game.isSolid(this.pos.add_x(this.pos, 1)) && !game.isSolid(big_pos.add_x(big_pos, 1))) && getRight() && !this.pos.add_x(this.pos, 1).outOfBounds()) {
				this.move(Action.RIGHT);
				this.big_pos = this.pos.add_y(this.pos, -1);
				moving = true;
			}
				
			else {
				if((!game.isSolid(this.pos.add_x(this.pos, -1)) && !game.isSolid(big_pos.add_x(big_pos, -1))) && !this.pos.add_x(this.pos, -1).outOfBounds()) {
					this.move(Action.LEFT);
					this.big_pos = this.pos.add_y(this.pos, -1);
					moving = true;
					setRight(false);
				}
				
				else if ((!game.isSolid(this.pos.add_x(this.pos, 1)) && !game.isSolid(big_pos.add_x(big_pos, 1))) && !this.pos.add_x(this.pos, 1).outOfBounds()){
					this.move(Action.RIGHT);
					this.big_pos = this.pos.add_y(this.pos, -1);
					moving = true;
					setRight(true);
				}
			}
				
			game.doInteractionsFrom(this);
		}
		else {
			updated = false;
			game.doInteractionsFrom(this);
		}
	}
	
	public boolean receiveInteraction(Goomba goomba) {
		boolean interacted = false;
		
		if (goomba.isInPosition(this.pos) && goomba.isAlive()) {
			interacted = true;
			if(!this.getFalling()) {
				if (big) {
					this.makeSmall();
				}
				else {
					this.dead();
				}
			}
		}
		
		else if (goomba.isInPosition(big_pos) && big && goomba.isAlive()) {
			interacted = true;
			big = false;
		}
		
		return interacted;
	}
	
	public boolean receiveInteraction(Mushroom mushroom) {
		boolean interacted = false;
		
		if (mushroom.isInPosition(this.pos)) {
			interacted = true;
			this.makeBig();
		}
		
		return interacted;
	}
	
	public boolean receiveInteraction(Box box) {
		boolean interacted = false;
		
		if (big && box.isInPosition(big_pos.add_y(big_pos, -1))) {
			interacted = true;
		}
		
		else if(box.isInPosition(pos.add_y(pos, -1))) {
			interacted = true;
		}
		
		return interacted;
	}
	
	
	public boolean marioOutOfBounds() {
		return this.pos.outOfBounds();
	}
	
	public boolean isInPosition(Position pos) {
		return (this.pos.equals(pos) || (big_pos.equals(pos) && big));
	}
	
	public boolean receiveInteraction(ExitDoor door) {
		return door.receiveInteraction(this);
	}
	
	public boolean interactWith(GameItem other) {
		boolean interacted;
	  	
	  	interacted = other.receiveInteraction(this);
		
		return interacted;
	 }
}