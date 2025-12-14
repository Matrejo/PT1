package tp1.control.commands;

import tp1.logic.GameModel;
import tp1.view.GameView;
import tp1.view.Messages;
import tp1.exceptions.*;
import java.io.*;

public class SaveCommand extends AbstractCommand {
	// Forman parte de atributos de estado
		private static final String NAME = Messages.COMMAND_SAVE_NAME;
		private static final String SHORTCUT = Messages.COMMAND_SAVE_SHORTCUT;
		private static final String DETAILS = Messages.COMMAND_SAVE_DETAILS;
		private static final String HELP = Messages.COMMAND_SAVE_HELP;
		
		private String fileName;
		
		public SaveCommand() {
			super(NAME, SHORTCUT, DETAILS, HELP); 
		}
		
		private SaveCommand(String fileName) {
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
					returnCommand = new SaveCommand(newCommand[1]);
				}
				else {
					throw new CommandParseException(Messages.COMMAND_INCORRECT_PARAMETER_NUMBER);
				}
			}
			
			return returnCommand;
		}
		
		public void execute(GameModel game, GameView view) throws CommandExecuteException{
			try{
				game.save(fileName);
				view.showMessage(Messages.GAME_SAVED);
			}catch (GameSaveException gse) {
				throw new CommandExecuteException(Messages.ERROR_COMMAND_EXECUTE, gse);
			}
		}
}
