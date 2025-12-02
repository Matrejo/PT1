package tp1.control.commands;


import tp1.exceptions.CommandParseException;
import java.util.Arrays;
import java.util.List;

import tp1.view.Messages;
import java.io.*;

public class CommandGenerator {

	private static final List<Command> availableCommands = Arrays.asList(
			//TODO fill with your code
			new UpdateCommand(),
			new ActionCommand(),
			new HelpCommand(),
			new ExitCommand(),
			new AddObjectCommand(),
			new RestartCommand(),
			new SaveCommand(),
			new LoadCommand()
			
	);

	public static Command parse(String[] commandWords) throws CommandParseException {
		for (Command c: availableCommands) {
			Command parsed = c.parse(commandWords);
			
			if (parsed != null) {
	            return parsed;  
			}
		}
		throw new CommandParseException( Messages.UNKNOWN_COMMAND.formatted(commandWords[0]));
	}
		
	public static String commandHelp() {
		StringBuilder commands = new StringBuilder();
		String new_command;
		
		commands.append(Messages.HELP_AVAILABLE_COMMANDS).append(Messages.LINE_SEPARATOR);
		
		for (Command c: availableCommands) {
			new_command = c.helpText();
			commands.append(new_command);
		}
		
		return commands.toString();
	}

}