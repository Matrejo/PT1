package tp1.logic;

import tp1.view.Messages;
import tp1.logic.Game;
import tp1.logic.gameobjects.Mario;

public class Goomba {
	private Position pos;
	public boolean right = true, eliminated = false;
	private Game game;
	
	public Goomba(Game game, Position new_pos) {
		this.pos = new_pos;
		this.game = game;
	}
	
	public boolean IsInPos(Position pos) {
		return this.pos.equals(pos);
	}
	
	public String getIcon() {
		return Messages.GOOMBA;
	}
	
	public void update() {
		if (!game.hasGround(this.pos.add_y(this.pos, 1)) && !game.hasGoomba(this.pos.add_y(this.pos, 1)))
			this.pos = this.pos.add_y(this.pos, 1);
		
		
		else if (!game.hasGround(this.pos.add_x(this.pos, 1)) && !game.hasGoomba(this.pos.add_x(this.pos, 1)) && right)
			this.pos = this.pos.add_x(this.pos, 1);
		
		else {
			if(!game.hasGround(this.pos.add_x(this.pos, -1)) && !game.hasGoomba(this.pos.add_x(this.pos, -1))) {
				this.pos = this.pos.add_x(this.pos, -1);
				right = false;
			}
			else if (!game.hasGround(this.pos.add_x(this.pos, 1))){
				this.pos = this.pos.add_x(this.pos, 1);
				right = true;
			}
		}
	}
	
	public boolean receiveInteraction(Mario other) {
		if (other.IsInPos(this.pos)) {
			this.eliminated = true;
			
		}
		
		return this.eliminated;
	}
}
