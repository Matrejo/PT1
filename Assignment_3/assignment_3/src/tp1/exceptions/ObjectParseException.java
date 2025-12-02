
package tp1.exceptions;

public class ObjectParseException extends GameModelParseException { 
	
	//vedi parse GameObjectFactory 
	//vedi addObject method di GameModel interface
	
	public ObjectParseException() { 
		super(); }
	public ObjectParseException(String message){ 
		super(message);}
	public ObjectParseException (String message, Throwable cause){
		super(message, cause);}
	public ObjectParseException(Throwable cause){ 
		super(cause); }
	protected ObjectParseException(String message, Throwable cause, boolean enableSuppression, boolean writeableStackTrace){
		super(message, cause, enableSuppression, writeableStackTrace);}

}
