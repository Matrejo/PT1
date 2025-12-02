package tp1.exceptions;

public class CommandException extends Exception {
	
	//constructors 
	public CommandException() { 
		super(); }
	public CommandException(String message){ 
		super(message);}
	public CommandException (String message, Throwable cause){
		super(message, cause);}
	public CommandException(Throwable cause){ 
		super(cause); }
	protected CommandException(String message, Throwable cause, boolean enableSuppression, boolean writeableStackTrace){
		super(message, cause, enableSuppression, writeableStackTrace);}
}
