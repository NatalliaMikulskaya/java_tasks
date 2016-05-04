package exceptions;

public class NotEnoughParameters extends Exception{

	private static final long serialVersionUID = 5L;

	public NotEnoughParameters(String message){
		super(message);
	}
	
	public NotEnoughParameters(String message, Exception e){
		super(message, e);
	}
	
	public String getMessage(){
		return this.getMessage();
	}
}
