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
	public ActionList actions;
	
	public Controller(Game game, GameView view) {
		this.game = game;
		this.view = view;
	}


	/**
	 * Runs the game logic, coordinate Model(game) and View(view)
	 * 
	 */
	public void run() {
		
		view.showWelcome();
		
		view.showGame();
		
		actions = new ActionList(100);
		
		while (continue_game) {
			command = view.getPrompt();
		
			command[0] = command[0].toLowerCase();
		
			switch (command[0]) {
		
			case "a":
			case "action":
				
				if(command.length > 1) {
					game.mario.update = false;
					actions.parseCommands(command);
					game.update(actions);
				}
				
				else {
					game.mario.update = true;
					game.update(actions);
				}
								
				if(game.mario.marioOutOfBounds() || game.won)
					continue_game = false;
				
				view.showGame();
				
				break;
			
			case "u":
			case "":
			case "update":
				
				game.mario.update = true;
				
				game.update(actions);
	
				view.showGame();
				
				break;
			
			case "r":
			case "reset":
	            game = new Game(game.nLevel);
				view.showGame();
				
				break;
			
			case "h":
			case "help":
				System.out.println(Messages.HELP);
				break;
			
			case "e":
			case "exit":
				continue_game = false;
				break;
			}
			
			if (game.remaining_time == 0)
				continue_game = false;
			
			if(game.mario.damaged) {
				game.subtractLife();
				
				if (game.numLives() > 0) {
					game.initLevel1();
					view.showGame();
					game.mario.damaged = false;
				}
				
				else
					continue_game = false;
			}
		}
		
		view.showEndMessage();
	}

}
