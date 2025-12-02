package tp1.exceptions;

public class ActionParseException extends GameModelParseException {
	//vedi action enum
	public ActionParseException() { 
		super(); }
	public ActionParseException(String message){ 
		super(message);}
	public ActionParseException (String message, Throwable cause){
		super(message, cause);}
	public ActionParseException(Throwable cause){ 
		super(cause); }
	protected ActionParseException(String message, Throwable cause, boolean enableSuppression, boolean writeableStackTrace){
		super(message, cause, enableSuppression, writeableStackTrace);}
}