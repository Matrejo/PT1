package tp1.exceptions;

public class GameModelParseException extends GameModelException{
	public GameModelParseException() { 
		super(); }
	public GameModelParseException(String message){ 
		super(message);}
	public GameModelParseException (String message, Throwable cause){
		super(message, cause);}
	public GameModelParseException(Throwable cause){ 
		super(cause); }
	protected GameModelParseException(String message, Throwable cause, boolean enableSuppression, boolean writeableStackTrace){
		super(message, cause, enableSuppression, writeableStackTrace);}
}
