package tp1.logic.gameobjects;

import tp1.logic.Action;
import tp1.logic.GameWorld;
import tp1.logic.Position;
import tp1.view.Messages;
import tp1.exceptions.OffBoardException;
import tp1.exceptions.PositionParseException;

public class Box extends GameObject{
		private boolean empty = false;
		private static final String NAME = "Box";
	    private static final String SHORTCUT = "B";
	    		
		public Box(GameWorld game, Position new_pos) {
			super(game, new_pos, true);
		}
		
		public Box() {}
		
		public String getIcon() {
		    if (empty)
		        return Messages.EMPTY_BOX; 
		    else
		        return Messages.BOX;
		}

		
		public Box createInstance(String[] info, GameWorld game) throws OffBoardException, PositionParseException{
		    Box new_box = new Box(game, pos.coordsToPos(info[0]));

		    if (info.length >= 3) {
		        if (info[2].equalsIgnoreCase("empty") || info[2].equalsIgnoreCase("e")) {
		            empty = true;
		        }
		    }

		    return new_box;
		}

		
		public String getShortcut() {
			return SHORTCUT;
		}
		
		public String getName() {
			return NAME;
		}
		
		
		public void update() {
			game.doInteractionsFrom(this);
		}
		
		
		public boolean receiveInteraction(Mario mario) {
			boolean interacted = false;
			Position below = this.pos.add_y(this.pos, 1), above = this.pos.add_y(pos, -1);
			
			if (mario.isInPosition(below)) {
				if (!empty) {
					empty = true; 
					game.addPoints(50); //to add in game
					game.addMushroom(above);; //a mushroom will spawn in the next update
				}
				interacted = true; 
			}
			return interacted;
		}
		
		
		public boolean interactWith(GameItem other) {
			boolean interacted = false;
			if (other != null)
				interacted = other.receiveInteraction(this);
			return interacted;
		 }
		
	}