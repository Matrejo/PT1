package tp1.control.commands;

import tp1.logic.Action;
import tp1.logic.GameModel;
import tp1.view.GameView;
import tp1.view.Messages;

import tp1.exceptions.CommandParseException;
import tp1.exceptions.CommandExecuteException;

import java.util.ArrayList;
import java.util.List;
import tp1.exceptions.ActionParseException;

public class ActionCommand extends AbstractCommand{

	// Forman parte de atributos de estado
	private static final String NAME = Messages.COMMAND_ACTION_NAME;
	private static final String SHORTCUT = Messages.COMMAND_ACTION_SHORTCUT;
	private static final String DETAILS = Messages.COMMAND_ACTION_DETAILS;
	private static final String HELP = Messages.COMMAND_ACTION_HELP;
	
	private Action[] actions;
	
	public ActionCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP); 
	}
	
	private ActionCommand(Action[] actions) {
	    this();
	    this.actions = actions;
	}

	protected boolean matchCommand(String name) {
		return this.matchCommandName(name);
	}
	

	
	@Override
    public Command parse(String[] commandWords) throws CommandParseException {
        // at least 2 words, the first one is action
		if (this.matchCommandName(commandWords[0])) {
			 if (commandWords.length < 2) {
				 throw new CommandParseException();
		        }
  
            List<Action> list = new ArrayList<>();

            // counters (max 4)
            int countLeft = 0;
            int countRight = 0;
            int countUp = 0;
            int countDown = 0;
            int countStop = 0;

            // to check the opposite actions rule
            Action horizontal_chosen = null; 
            Action vertical_chosen = null;   

            // after "action"
            for (int i = 1; i < commandWords.length; i++) {
            	Action a; 
                try{
                	a = Action.fromString(commandWords[i]);
                } catch (ActionParseException ape) {
                	throw new CommandParseException(Messages.UNKNOWN_ACTION.formatted(commandWords[i]), ape);
                }

                switch (a) {
                    case LEFT:
                        if (horizontal_chosen == null || horizontal_chosen == Action.LEFT) {
                            if (countLeft < 4) { // max 4 LEFT
                                list.add(Action.LEFT);
                                horizontal_chosen = Action.LEFT;
                                countLeft++;
                            }
                        }
                        break;

                    case RIGHT:
                        if (horizontal_chosen == null || horizontal_chosen == Action.RIGHT) {
                            if (countRight < 4) { // max 4 RIGHT
                                list.add(Action.RIGHT);
                                horizontal_chosen = Action.RIGHT;
                                countRight++;
                            }
                        }
                        break;

                    case UP:
                        if (vertical_chosen == null || vertical_chosen == Action.UP) {
                            if (countUp < 4) { // max 4 UP
                                list.add(Action.UP);
                                vertical_chosen = Action.UP;
                                countUp++;
                            }
                        }
                        break;

                    case DOWN:
                        if (vertical_chosen == null || vertical_chosen == Action.DOWN) {
                            if (countDown < 4) { // max 4 DOWN
                                list.add(Action.DOWN);
                                vertical_chosen = Action.DOWN;
                                countDown++;
                            }
                        }
                        break;

                    case STOP:
                        if (countStop < 4) {
                            list.add(Action.STOP);
                            countStop++;
                        }
                        break;
                }
            }

            Action[] array = list.toArray(new Action[0]);
            return new ActionCommand(array);
        }
        
        return null;
    }

	public void execute(GameModel game, GameView view) {
		game.doMarioActions(actions);
		view.showGame();
	}
	
}