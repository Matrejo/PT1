package tp1.control.commands;


import tp1.logic.Game;
import tp1.view.GameView;
import tp1.view.Messages;

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

	protected boolean matchCommand(String name) {
		return this.matchCommandName(name);
	}
	
	public Command parse(String[] newCommand) {
		Command returnCommand = null;
		
		if(newCommand.length >= 3) {
			if (this.matchCommandName(newCommand[0])) {
				returnCommand = new AddObjectCommand(newCommand);
			}
		}
		
		return returnCommand;
	}
	
	public void execute(Game game, GameView view) {
		String[] noCommandObject = new String[newObject.length - 1];
		
		for (int i = 1; i < newObject.length; i++) {
			noCommandObject[i - 1] = newObject[i];
		}
		
		game.newObject(noCommandObject);
		view.showGame();
	}
}
