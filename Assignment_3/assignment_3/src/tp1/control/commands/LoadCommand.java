package tp1.control.commands;

import tp1.logic.GameModel;
import tp1.view.GameView;
import tp1.view.Messages;
import tp1.exceptions.*;
import java.io.*;

public class LoadCommand extends AbstractCommand {
	// Forman parte de atributos de estado
		private static final String NAME = Messages.COMMAND_LOAD_NAME;
		private static final String SHORTCUT = Messages.COMMAND_LOAD_SHORTCUT;
		private static final String DETAILS = Messages.COMMAND_LOAD_DETAILS;
		private static final String HELP = Messages.COMMAND_LOAD_HELP;
		
		private String fileName;
		
		public LoadCommand() {
			super(NAME, SHORTCUT, DETAILS, HELP); 
		}
		
		private LoadCommand(String fileName) {
		    this();
		    this.fileName = fileName;
		}

		protected boolean matchCommand(String name) {
			return this.matchCommandName(name);
		}
		
		public Command parse(String[] newCommand) throws CommandParseException {
			Command returnCommand = null;
			
			if(this.matchCommandName(newCommand[0])) {
				if (newCommand.length == 2) {
					returnCommand = new LoadCommand(newCommand[1]);
				}
				else {
					throw new CommandParseException(Messages.COMMAND_INCORRECT_PARAMETER_NUMBER);
				}
			}
			
			return returnCommand;
		}
		
		public void execute(GameModel game, GameView view) throws CommandExecuteException{
			try{
				game.load(fileName);
			}catch (GameLoadException gle) {
				throw new CommandExecuteException(Messages.ERROR_COMMAND_EXECUTE.formatted(fileName), gle);
			}
			
			view.showGame();
		}
}