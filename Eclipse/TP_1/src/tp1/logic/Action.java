package tp1.logic;

/**
 * Represents the allowed actions in the game
 *
 */
public enum Action {
	LEFT(-1,0), RIGHT(1,0), DOWN(0,1), UP(0,-1), STOP(0,0);
	private int up_counter = 0, down_counter = 0, right_counter = 0, left_counter = 0, stop_counter = 0;
	
	private int x;
	private int y;
	
	private Action(int x, int y) {
		this.x=x;
		this.y=y;
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public Position moveUp(Position pos) {
		pos = pos.add_y(pos, UP.y);
		
		return pos;
	}
	
	public Position moveDown(Position pos) {
		pos = pos.add_y(pos, DOWN.y);
		
		return pos;
	}
	
	public Position moveLeft(Position pos) {
		pos = pos.add_x(pos, LEFT.x);
		
		return pos;
	}
	
	public Position moveRight(Position pos) {
		pos = pos.add_x(pos, RIGHT.x);
		
		return pos;
	}
	
	public Action parseCommands(String command) {
		Action action = UP;
		
		switch(command.toLowerCase()) {
		
		case "u":
		case "up":
			if(down_counter == 0 && up_counter < 5) {
				action = UP;
				up_counter++;
			}
			break;
			
		case "d":
		case "down":
			if(up_counter == 0 && down_counter < 5) {
				action = DOWN;
				down_counter++;
			}
			break;
			
		case "l":
		case "left":
			if(right_counter == 0 && left_counter < 5) {
				action = LEFT;
				left_counter++;
			}
			break;
			
		case "r":
		case "right":
			if(left_counter == 0 && right_counter < 5) {
				action = RIGHT;
				right_counter++;
			}
			break;
				
		case "s":
		case "stop":
			if(stop_counter < 5) {
				action = STOP;
				stop_counter++;
			}
			break;
			
		}
		
		up_counter = 0;
		down_counter = 0;
		right_counter = 0;
		left_counter = 0;
		stop_counter = 0;
		
		return action;
	}
}
