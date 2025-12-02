package tp1.logic.gameobjects;

import tp1.logic.Action;
import tp1.logic.GameWorld;
import tp1.logic.Position;
import tp1.view.Messages;
import tp1.exceptions.OffBoardException;
import tp1.exceptions.PositionParseException;

public class Mushroom extends MovingObject{
	private static final String NAME = "Mushroom";
    private static final String SHORTCUT = "MU";
    		
	public Mushroom(GameWorld game, Position new_pos) {
		super(game, new_pos, false);
	}	
	
	public Mushroom() {}
	
	public String getIcon() {
		return Messages.MUSHROOM;
	}
	
	public Mushroom createInstance(String[] info, GameWorld game) throws OffBoardException, PositionParseException{
		Mushroom new_mushroom = new Mushroom(game, pos.coordsToPos(info[0]));
		
		if(info.length == 3) {
			if("right".equalsIgnoreCase(info[2])) {
				new_mushroom.setRight(true);
			}
		}
		
		return new_mushroom;
	}
	
	public String getShortcut() {
		return SHORTCUT;
	}
	public String getName() {
		return NAME;
	}
	
	public boolean receiveInteraction(Mario mario) {
		boolean interacted = false;
		if (mario.isInPosition(this.pos)) {
			interacted = true;
			this.dead();
			if(!mario.IsBig())
				mario.makeBig();
		}
		
		return interacted;
	}
	
	public void update() {
		boolean right = getRight();
		
		if (!game.isSolid(this.pos.add_y(pos, 1)))
			this.move(Action.DOWN);
		
		else if (right && !this.pos.add_x(pos, 1).outOfBounds() && !game.isSolid(this.pos.add_x(pos, 1)))
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