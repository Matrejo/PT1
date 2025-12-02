package tp1.exceptions;

public class OffBoardException extends GameModelException { 
	//vedi addObject
	
	public OffBoardException() { 
		super(); }
	public OffBoardException(String message){ 
		super(message);}
	public OffBoardException (String message, Throwable cause){
		super(message, cause);}
	public OffBoardException(Throwable cause){ 
		super(cause); }
	protected OffBoardException(String message, Throwable cause, boolean enableSuppression, boolean writeableStackTrace){
		super(message, cause, enableSuppression, writeableStackTrace);}

}