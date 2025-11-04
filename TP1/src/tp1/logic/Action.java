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
	
	//fix
    private static final int MAX_REPEAT = 4;
	
	private Action(int x, int y) {
		this.x=x;
		this.y=y;
	}
	
	//parsing of one word
	 public static Action parseCommand(String command) {
	        if (command == null) {
	        	return STOP;
	        }
	        switch (command.toLowerCase()) {
	            case "u":
	            case "up":
	                return UP;
	            case "d":
	            case "down":
	                return DOWN;
	            case "l":
	            case "left":
	                return LEFT;
	            case "r":
	            case "right":
	                return RIGHT;
	            case "s":
	            case "stop":
	                return STOP;
	            default:
	                return STOP;
	        }
	    }
	 
	 //parsing of more then one word
	 public static ActionList parseCommands(String[] commands) {
	        if (commands == null) {
	        	return new ActionList(0);
	        }

	        int up = 0, down = 0, left = 0, right = 0, stop = 0;
	        ActionList list = new ActionList(commands.length);

	        for (int i = 0; i < commands.length; i++) {
	            Action a = parseCommand(commands[i]);

	            switch (a) {
	                case UP:
	                    if (up < MAX_REPEAT && down == 0) {
	                        list.addAction(UP);
	                        up++;}
	                    break;
	                case DOWN:
	                    if (down < MAX_REPEAT && up == 0) {
	                        list.addAction(DOWN);
	                        down++;}
	                    break;
	                case LEFT:
	                    if (left < MAX_REPEAT && right == 0) {
	                        list.addAction(LEFT);
	                        left++;}
	                    break;
	                case RIGHT:
	                    if (right < MAX_REPEAT && left == 0) {
	                        list.addAction(RIGHT);
	                        right++;}
	                    break;
	                case STOP:
	                    if (stop < MAX_REPEAT) {
	                        list.addAction(STOP);
	                        stop++;}
	                    break;
	            }
	        }

	        return list;
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