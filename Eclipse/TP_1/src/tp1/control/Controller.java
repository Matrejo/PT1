package tp1.control;

import tp1.logic.Game;
import tp1.view.GameView;
import tp1.view.Messages;
import tp1.logic.ActionList;
import tp1.logic.Action;
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
		
		while (continue_game) {
			command = view.getPrompt();
		
			command[0].toLowerCase();
		
			switch (command[0]) {
		
			case "a":
			case "action":
				actions = new ActionList(command.length - 1);
				
				for (int i = 1; i < command.length; i++) {
					switch(command[i].toLowerCase()) {
					
					case "u":
						game.mario.update = false;
						actions.addAction(Action.UP);
						break;
						
					case "d":
						game.mario.update = false;
						actions.addAction(Action.DOWN);
						break;
						
					case "l":
						game.mario.update = false;
						actions.addAction(Action.LEFT);
						break;
						
					case "r":
						game.mario.update = false;
						actions.addAction(Action.RIGHT);
						break;
						
					}
				}
				
				actions.doActions(game);
								
				if(game.mario.marioOutOfBounds() || game.won)
					continue_game = false;
				
				view.showGame();
				
				break;
			
			case "u":
			case "update":
				
				game.mario.update = true;
				
				game.mario.update(Action.DOWN);
				
				if(game.mario.marioOutOfBounds())
					continue_game = false;
				
				view.showGame();
				
				break;
			
			case "r":
			case "reset":
	            game.initLevel1();
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
