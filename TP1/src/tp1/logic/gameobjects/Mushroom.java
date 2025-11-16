package tp1.logic.gameobjects;

import tp1.logic.Action;
import tp1.logic.Game;
import tp1.logic.Position;
import tp1.view.Messages;

public class Mushroom extends GameObject{
	private boolean right = true;
	private static final String NAME = "Mushroom";
    private static final String SHORTCUT = "MU";
    		
	public Mushroom(Game game, Position new_pos) {
		super(game, new_pos, false);
	}	
	public String getIcon() {
		return Messages.MUSHROOM;
	}
	
	public Mushroom createInstance(String[] info, Game game) {
		Mushroom new_mushroom = new Mushroom(game, pos.coordsToPos(info[0]));
		
		if(info.length == 3) {
			if("right".equalsIgnoreCase(info[2])) {
				new_mushroom.faceRight();
			}
		}
		
		return new_mushroom;
	}
	
	public void faceRight() {
		this.right = true;
	}
	
	public void faceLeft() {
		this.right = false;
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
		if (!game.hasSolid(this.pos.add_y(pos, 1)))
			this.move(Action.DOWN);
		
		else if (right && !this.pos.add_x(pos, 1).outOfBounds() && !game.hasSolid(this.pos.add_x(pos, 1)))
			this.move(Action.RIGHT);
		
		else {
			if(!this.pos.add_x(pos, -1).outOfBounds() && !game.hasSolid(this.pos.add_x(pos, -1))) {
				this.move(Action.LEFT);;
				right = false;
			}
			else if (!this.pos.add_x(pos, 1).outOfBounds() && !game.hasSolid(this.pos.add_x(pos, 1))){
				this.move(Action.RIGHT);
				right = true;
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