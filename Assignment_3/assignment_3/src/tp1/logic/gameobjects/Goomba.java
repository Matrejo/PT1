package tp1.logic.gameobjects;

import tp1.logic.GameWorld;
import tp1.logic.Position;
import tp1.view.Messages;
import tp1.logic.Action;
import tp1.exceptions.OffBoardException;
import tp1.exceptions.PositionParseException;

public class Goomba extends MovingObject{	
	private static final String NAME = "goomba";
    private static final String SHORTCUT = "g";
	
	public Goomba(GameWorld game, Position new_pos) {
		super(game, new_pos, false);
		setRight(false);
	}
	
	public Goomba() {}
	
	public String getIcon() {
		return Messages.GOOMBA;
	}
	
	public Goomba createInstance(String[] info, GameWorld game) throws OffBoardException, PositionParseException{
		Goomba new_goomba = new Goomba(game, pos.coordsToPos(info[0]));
		
		if(info.length == 3) {
			if("right".equalsIgnoreCase(info[2])) {
				new_goomba.faceRight();
			}
		}
		
		return new_goomba;
	}
	
	public void faceRight() {
		setRight(true);
	}
	
	public void faceLeft() {
		setRight(false);
	}
	
	public String getShortcut() {
		return SHORTCUT;
	}
	
	public String getName() {
		return NAME;
	}
	
	public void update() {
		if (!game.isSolid(this.pos.add_y(pos, 1)))
			this.move(Action.DOWN);
		
		else if (getRight() && !game.isSolid(this.pos.add_x(pos, 1)))
			this.move(Action.RIGHT);
		
		else {
			if(!this.pos.add_x(pos, -1).outOfBounds() && !game.isSolid(this.pos.add_x(pos, -1))) {
				this.move(Action.LEFT);;
				setRight(false);
			}
			else if (!this.pos.add_x(pos, 1).outOfBounds() && !game.isSolid(this.pos.add_x(pos, 1))){
				this.move(Action.RIGHT);
				setRight(true);
			}
		}
		
		game.doInteractionsFrom(this);
	}
	
	public boolean receiveInteraction(Mario mario) {
		boolean interacted = false;
		
		if (mario.isInPosition(this.pos)) {
			interacted = true;
			mario.receiveInteraction(this);
			this.dead();
			game.addPoints(100);
		}
		
		return interacted;
	}
	
	
	public boolean interactWith(GameItem other) {
		boolean interacted = false;
	  	
		if (other != null)
			interacted = other.receiveInteraction(this);
		
		if (interacted) {
			this.dead();
			game.addPoints(100);
		}
		
		return interacted;
	 }
}
