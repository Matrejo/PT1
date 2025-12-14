package tp1.control.commands;

import tp1.exceptions.CommandParseException;
import tp1.exceptions.CommandExecuteException;
import tp1.exceptions.GameLoadException;
import tp1.logic.GameModel;
import tp1.view.GameView;
import tp1.view.Messages;

public class ResetCommand extends AbstractCommand{
	// Forman parte de atributos de estado
	private static final String NAME = Messages.COMMAND_RESET_NAME;
	private static final String SHORTCUT = Messages.COMMAND_RESET_SHORTCUT;
	private static final String DETAILS = Messages.COMMAND_RESET_DETAILS;
	private static final String HELP = Messages.COMMAND_RESET_HELP;
	
	private int newLevel = 999;
	
	public ResetCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP); 
	}
	
	private ResetCommand(int newLevel) {
	    this();
	    this.newLevel = newLevel;
	}

	protected boolean matchCommand(String name) {
		return this.matchCommandName(name);
	}
	
	public Command parse(String[] newCommand) throws CommandParseException {
		Command returnCommand = null;
		
		if (this.matchCommandName(newCommand[0])) {
			if(newCommand.length > 2) {
				throw new CommandParseException (Messages.COMMAND_INCORRECT_PARAMETER_NUMBER);
			}
			
			if(newCommand.length == 1) {
				return new ResetCommand(999);
			}
			
			try {
				returnCommand = new ResetCommand(Integer.parseInt(newCommand[1]));
			} catch (NumberFormatException nfe) {
	            throw new CommandParseException(Messages.LEVEL_NOT_A_NUMBER_ERROR.formatted(newCommand[1]), nfe);
			}
		}
		return returnCommand;
	}
	
	public void execute(GameModel game, GameView view) throws CommandExecuteException {
		int requestedLevel = newLevel;
		
		try {
			if (requestedLevel >= -1 && requestedLevel < 2) {
				game.setLevel(requestedLevel);
			}
			
			game.restart();
			view.showGame();
		} catch(GameLoadException gle) {
			throw new CommandExecuteException(gle);
		}
	}
}