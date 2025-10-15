package tp1.control;

import tp1.logic.Game;
import tp1.view.GameView;
import tp1.view.Messages;
import tp1.logic.ActionList;
import tp1.logic.Action;
import tp1.Main;
/**
 *  Accepts user input and coordinates the game execution logic
 */
public class Controller {

	private Game game;
	private GameView view;
	private String command[] = new String[100];
	private boolean continue_game = true;
	
	public Controller(Game game, GameView view) {
		this.game = game;
		this.view = view;
	}


	/**
	 * Runs the game logic, coordinate Model(game) and View(view)
	 * 
	 */
	public void run() {
		Action action = Action.UP;
		
		view.showWelcome();
		
		view.showGame();
		
		game.mario.mario_actions = new ActionList(100);
		
		while (continue_game) {
			command = view.getPrompt();
		
			command[0] = command[0].toLowerCase();
		
			if(command[0].equals("a") || command[0].equals("action")){
				
				if(command.length > 1) {
					game.mario.update = false;
					for (int i = 1; i < command.length; i++) {
						action = action.parseCommands(command[i]);
						game.addAction(action);
					}
					game.update(game.mario.mario_actions);
				}
				
				else {
					game.mario.update = true;
					game.update(game.mario.mario_actions);
				}
								
				if(game.mario.marioOutOfBounds() || game.won)
					continue_game = false;
				
				view.showGame();
			}
			
			else if(command[0].equals("u") || command[0].equals("update") || command[0].equals("") || command[0].equals(" ")) {
				
				game.mario.update = true;
				
				game.update(game.mario.mario_actions);
	
				view.showGame();
				
			}
			
			else if(command[0].equals("r") || command[0].equals("reset")) {
	            game = new Game(game.nLevel);
				view.showGame();
				
			}
			
			else if(command[0].equals("h") || command[0].equals("help")) {
				System.out.println(Messages.HELP);

			}
			
			else if(command[0].equals("e") || command[0].equals("exit")) {
				continue_game = false;
		
			}
			
			else {
				view.showError(Messages.UNKNOWN_COMMAND.formatted(command[0]));
			}
			
			if (game.remaining_time == 0)
				continue_game = false;
			
			if(game.mario.damaged) {
				game.subtractLife();
				
				if (game.numLives() > 0) {
					game.initLevel1();
					view.showGame();
					game.mario.damaged = false;
					game.mario.mario_actions = new ActionList(100);
				}
				
				else
					continue_game = false;
			}
		}
		
		view.showEndMessage();
	}

}
