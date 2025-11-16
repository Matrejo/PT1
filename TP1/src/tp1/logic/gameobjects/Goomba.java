package tp1.logic.gameobjects;

import tp1.logic.Game;
import tp1.logic.Position;
import tp1.view.Messages;
import tp1.logic.Action;

public class Goomba extends GameObject{	
	private boolean right = false;
	private static final String NAME = "goomba";
    private static final String SHORTCUT = "g";
	
	public Goomba(Game game, Position new_pos) {
		super(game, new_pos, false);
	}
	
	public String getIcon() {
		return Messages.GOOMBA;
	}
	
	public Goomba createInstance(String[] info, Game game) {
		Goomba new_goomba = new Goomba(game, pos.coordsToPos(info[0]));
		
		if(info.length == 3) {
			if("right".equalsIgnoreCase(info[2])) {
				new_goomba.faceRight();
			}
		}
		
		return new_goomba;
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
