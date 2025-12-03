package tp1.control.commands;

import tp1.exceptions.ObjectParseException;
import tp1.exceptions.CommandParseException;
import tp1.exceptions.CommandExecuteException;
import tp1.logic.GameModel;
import tp1.view.GameView;
import tp1.view.Messages;
import tp1.exceptions.OffBoardException;
import tp1.exceptions.GameModelException;

public class AddObjectCommand extends AbstractCommand{
	// Forman parte de atributos de estado
	private static final String NAME = Messages.COMMAND_ADD_OBJECT_NAME;
	private static final String SHORTCUT = Messages.COMMAND_ADD_OBJECT_SHORTCUT;
	private static final String DETAILS = Messages.COMMAND_ADD_OBJECT_DETAILS;
	private static final String HELP = Messages.COMMAND_ADD_OBJECT_HELP;
	
	private String[] newObject;
	
	public AddObjectCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP); 
	}
	
	private AddObjectCommand(String[] newObject) {
	    this();
	    this.newObject = newObject;
	}
	
	public Command parse(String[] newCommand) throws CommandParseException {
	
	    if (!this.matchCommandName(newCommand[0])) {
	    	return null;}

	    if (newCommand.length < 3) {
	        throw new CommandParseException(Messages.COMMAND_INCORRECT_PARAMETER_NUMBER);
	    }
	    
	   // try {
	    	return new AddObjectCommand(newCommand);
	   /* } catch (ObjectParseException gope) { //da definire
	    	throw new CommandParseException("Error parsing object", gope);
	    }*/
	}
	
	public void execute(GameModel game, GameView view) throws CommandExecuteException{

		String[] noCommandObject = new String[newObject.length - 1];
		
		try {
			for (int i = 1; i < newObject.length; i++) {
				noCommandObject[i - 1] = newObject[i];
			}
			
			game.newObject(noCommandObject);
			view.showGame();
			
		} catch(Exception e) {
			throw new CommandExecuteException(Messages.ERROR_COMMAND_EXECUTE, e);
		}
	}
}