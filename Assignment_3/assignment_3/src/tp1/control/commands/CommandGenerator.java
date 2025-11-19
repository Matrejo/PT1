package tp1.control.commands;

import java.util.Arrays;
import java.util.List;

import tp1.view.Messages;

public class CommandGenerator {

	private static final List<Command> availableCommands = Arrays.asList(
			//TODO fill with your code
			new UpdateCommand(),
			new ActionCommand(),
			new HelpCommand(),
			new ExitCommand(),
			new AddObjectCommand(),
			new RestartCommand()
			
	);

	public static Command parse(String[] commandWords) {
		Command final_command = null;
		for (Command c: availableCommands) {
			if (c.parse(commandWords) != null) {
				final_command = c.parse(commandWords);
			}
		}
		return final_command;
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
