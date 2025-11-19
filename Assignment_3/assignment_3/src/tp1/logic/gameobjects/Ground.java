package tp1.logic.gameobjects;

import tp1.logic.Position;
import tp1.logic.GameWorld;
import tp1.view.Messages;

public class Ground extends GameObject{
	private static final String NAME = "ground";
    private static final String SHORTCUT = "gr";
	
	public Ground(GameWorld game, Position new_pos) {
		super (game, new_pos, true);
	}
	
	public Ground() {
		
	}
	
	public String getIcon() {
		return Messages.LAND;
	}
	
	public Ground createInstance(String[] info, GameWorld game) {
		return new Ground(game, pos.coordsToPos(info[0]));
	}
	
	public void update() {
		
	}
	
	public String getShortcut() {
		return SHORTCUT;
	}
	
	public String getName() {
		return NAME;
	}
	
	public boolean interactWith(GameItem other) {
		boolean interacted;
	  	
	  	interacted = other.receiveInteraction(this);
		
		return interacted;
	}
}
