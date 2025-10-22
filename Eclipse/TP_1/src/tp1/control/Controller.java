package tp1.control;

import tp1.logic.Game;
import java.util.Arrays;
import tp1.view.ConsoleColorsView;
import tp1.view.ConsoleView;
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
	
	public Controller(Game game, GameView view) {
		this.game = game;
		this.view = view;
	}


	/**
	 * Runs the game logic, coordinate Model(game) and View(view)
	 * 
	 */
	public void run() {
		Action action = Action.UP; //action = action.parseCommands("up")
		
		view.showWelcome();
		
		view.showGame();
		
		game.mario.mario_actions = new ActionList(100);
		
		while (continue_game) {
			command = view.getPrompt();
		
			command[0] = command[0].toLowerCase();
		
			if(command[0].equals("a") || command[0].equals("action")){
				
				if(command.length > 1) {
					game.mario.update = false;
					
					String[] tokens = Arrays.copyOfRange(command, 1, command.length);
					ActionList parsed = Action.parseCommands(tokens); //build the action list respecting the limit of 5 some actions
					game.mario.mario_actions = parsed;
					game.update(parsed);
					
					/*for (int i = 1; i < command.length; i++) {
						action = action.parseCommands(command[i]);
						game.addAction(action);
					}
					game.update(game.mario.mario_actions);*/
				}
				
				else {
					game.mario.update = true;
					game.update(game.mario.mario_actions);
				}
								
				if(game.won)
					continue_game = false;
				
				view.showGame();
			}
			
			else if(command[0].equals("u") || command[0].equals("update") || command[0].equals("") || command[0].equals(" ")) {
				
				game.mario.update = true;
				
				game.update(game.mario.mario_actions);
	
				view.showGame();
				
			}
			
			//fix reset 
			else if(command[0].equals("r") || command[0].equals("reset")) {
				Integer levelArg = game.nLevel;
				
				 game.reset(levelArg);               // ricarica il livello giusto o quello corrente
				 game.nLevel = levelArg;
				 game.mario.damaged = false;         // pulizia stato mario (se serve)
				 game.mario.mario_actions = new ActionList(100); // svuota azioni pendenti
				 view.showGame();                    // dopo un comando valido che cambia lo stato, si ristampa la board
				
	            //this.game = new Game(this.game.nLevel);
	            //GameView view = this.game.nLevel>1 ? new ConsoleView(game): new ConsoleColorsView(game);
	            //Controller controller = new Controller(game, view);
				//controller.run();
				//view.showGame();				
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
			
			
			if(game.mario.damaged || game.mario.marioOutOfBounds()) {
				game.subtractLife();
				
				if (game.numLives() > 0) {
					int nLevel = game.nLevel;
					// game.initLevel1();
					game.reset(game.nLevel); //fix
					game.remaining_time = 100;
					game.nLevel = nLevel;
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
