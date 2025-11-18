package tp1.control.commands;

import tp1.logic.GameModel;
import tp1.view.GameView;
import tp1.view.Messages;

public class RestartCommand extends AbstractCommand{
	// Forman parte de atributos de estado
	private static final String NAME = Messages.COMMAND_RESTART_NAME;
	private static final String SHORTCUT = Messages.COMMAND_RESTART_SHORTCUT;
	private static final String DETAILS = Messages.COMMAND_RESTART_DETAILS;
	private static final String HELP = Messages.COMMAND_RESTART_HELP;
	
	private int newLevel;
	
	public RestartCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP); 
	}
	
	private RestartCommand(int newLevel) {
	    this();
	    this.newLevel = newLevel;
	}

	protected boolean matchCommand(String name) {
		return this.matchCommandName(name);
	}
	
	public Command parse(String[] newCommand) {
		Command returnCommand = null;
		
		if (this.matchCommandName(newCommand[0])) {
			if(newCommand.length == 2) {
				returnCommand = new RestartCommand(Integer.parseInt(newCommand[1]));
			}
			
			else {
				returnCommand = new RestartCommand(-2);
			}
		}
		
		return returnCommand;
	}
	
	public void execute(GameModel game, GameView view) {
		int requestedLevel = newLevel;
		
		if (requestedLevel != -2) {
			game.setLevel(requestedLevel);
		}
		
		game.restart();
		view.showGame();
	}
}