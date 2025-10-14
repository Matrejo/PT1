package tp1.logic;

import tp1.view.Messages;
import tp1.logic.Game;

public class Goomba {
	private Position pos;
	private boolean right = true;
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
}
