package tp1.control.commands;

public abstract class NoParamsCommand extends AbstractCommand {

	public NoParamsCommand(String name, String shortcut, String details, String help) {
		super(name, shortcut, details, help);
	}

	@Override
	/*public Command parse(String[] commandWords) {
		Command return_command = this.parse(commandWords);
		return return_command;
	}*/
	
	
	public Command parse(String[] commandWords) {
		Command return_command = null;
		
		if(commandWords.length == 1) {
			if(this.matchCommandName(commandWords[0])) {
				return_command = this;
			}
		}
		
		return return_command;
	}
}