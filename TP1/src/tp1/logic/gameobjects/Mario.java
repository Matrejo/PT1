package tp1.logic.gameobjects;

import tp1.logic.GameWorld;
import tp1.logic.Position;
import tp1.view.Messages;
import tp1.logic.Action;

public class Mario extends GameObject{
	private Position big_pos;
	private Action[] mario_actions = null;
	private String mario;
	private static final String NAME = "mario";
    private static final String SHORTCUT = "m";
	private boolean right = true, moving = true, big = true, updated = false;
	private boolean falling = false;
	
	public Mario(GameWorld game, Position new_pos) { 
		super (game, new_pos, false);
		this.big_pos = this.pos.add_y(pos, -1);
	}
	
	public Mario() {}
	
	public void setActions(Action[] actions) {
		this.mario_actions = actions;
	}
	
	public String getShortcut() {
		return SHORTCUT;
	}
	
	public String getName() {
		return NAME;
	}
	
	public GameObject parse(String objWords[]) {
		GameObject object = null;
		if (objWords.length <= 4 && (5 < objWords[0].length() && objWords[0].length() < 8)) {
			if (this.matchObjectName(objWords[1])) {
				object = this.createInstance(objWords, game);
			}
		}
		
		return object;
	}
	
	public void makeBig() {
		this.big = true;
	}
	
	public void makeSmall() {
		this.big = false;
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
	
	public void faceRight() {
		this.right = true;
	}
	
	public void faceLeft() {
		this.right = false;
	}
	
	public Mario createInstance(String[] info, GameWorld game) {
		Mario new_mario = new Mario(game, pos.coordsToPos(info[0]));
		
		if (info.length >= 3) {
			if(info[2].equalsIgnoreCase("left")) {
				new_mario.faceLeft();
			}
			else if (info[2].equalsIgnoreCase("small")) {
				new_mario.makeSmall();
			}
			
			if(info.length == 4) {
				if(info[3].equalsIgnoreCase("left")) {
					new_mario.faceLeft();
				}
				else if (info[3].equalsIgnoreCase("small")) {
					new_mario.makeSmall();
				}
			}
		}
		
		return new_mario;
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
					falling = true;
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
							right = false;
						}
					}
					else {
						if(!game.isSolid(this.pos.add_y(this.pos, -1))) {
							this.move(Action.LEFT);
							this.big_pos = this.pos.add_y(this.pos, -1);
							moving = true;
							right = false;
						}
					}
					break;
				
				case RIGHT:
					if (big) {
						if(!game.isSolid(big_pos.add_x(big_pos, 1)) && !game.isSolid(this.pos.add_x(this.pos, 1))) {
							this.move(Action.RIGHT);
							this.big_pos = this.pos.add_y(this.pos, -1);
							moving = true;
							right = true;
						}
					}
					else {
						if(!game.isSolid(this.pos.add_x(this.pos, 1))) {
							this.move(Action.RIGHT);
							this.big_pos = this.pos.add_y(this.pos, -1);
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
			
			this.mario_actions = null;
		}
		
		updated = true;
		game.doInteractionsFrom(this);
		falling = false;
	}

	public void update() {
		if (!updated && moving) {
			if((!game.isSolid(this.pos.add_x(this.pos, 1)) && !game.isSolid(big_pos.add_x(big_pos, 1))) && right && !this.pos.add_x(this.pos, 1).outOfBounds()) {
				this.move(Action.RIGHT);
				this.big_pos = this.pos.add_y(this.pos, -1);
				moving = true;
			}
				
			else {
				if((!game.isSolid(this.pos.add_x(this.pos, -1)) && !game.isSolid(big_pos.add_x(big_pos, -1))) && !this.pos.add_x(this.pos, -1).outOfBounds()) {
					this.move(Action.LEFT);
					this.big_pos = this.pos.add_y(this.pos, -1);
					moving = true;
					right = false;
				}
				
				else if ((!game.isSolid(this.pos.add_x(this.pos, 1)) && !game.isSolid(big_pos.add_x(big_pos, 1))) && !this.pos.add_x(this.pos, 1).outOfBounds()){
					this.move(Action.RIGHT);
					this.big_pos = this.pos.add_y(this.pos, -1);
					moving = true;
					right = true;
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
			if(!this.falling) {
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