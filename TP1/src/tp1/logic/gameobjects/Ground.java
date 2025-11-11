package tp1.logic.gameobjects;

import tp1.logic.Position;
import tp1.logic.Game;
import tp1.view.Messages;

public class Ground extends GameObject{
	private Position pos = new Position(0, 0);
	private static final String NAME = "ground";
    private static final String SHORTCUT = "gr";
	
	public Ground(Game game, Position new_pos) {
		super (game, new_pos, true);
	}
	
	public String getIcon() {
		return Messages.LAND;
	}
	
	public Ground createInstance(String[] info, Game game) {
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
