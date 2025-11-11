package tp1.control.commands;

import tp1.logic.Game;
import tp1.view.GameView;
import tp1.view.Messages;

public class HelpCommand extends NoParamsCommand {

    private static final String NAME = Messages.COMMAND_HELP_NAME;
    private static final String SHORTCUT = Messages.COMMAND_HELP_SHORTCUT;
    private static final String DETAILS = Messages.COMMAND_HELP_DETAILS;
    private static final String HELP = Messages.COMMAND_HELP_HELP;

    public HelpCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);
	}
	
	@Override
	public void execute(Game game, GameView view) {
		view.showMessage(CommandGenerator.commandHelp());
	}
	
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
